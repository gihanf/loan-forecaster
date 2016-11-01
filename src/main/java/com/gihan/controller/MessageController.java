/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.gihan.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gihan.model.*;
import com.gihan.repository.ExpenseRepository;
import com.gihan.service.ExpenseService;

@Controller
@RequestMapping("/")
public class MessageController {
    private static final Log LOG = LogFactory.getLog(MessageController.class);

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseService expenseService;

    @ModelAttribute("allFrequencies")
    public List<Frequency> populateTypes() {
        return Arrays.asList(Frequency.values());
    }

    @ModelAttribute("paymentScheduleOptions")
    public List<PaymentScheduleOption> populateIncurredOptions() {
        return Arrays.asList(PaymentScheduleOption.values());
    }

    @RequestMapping
    public ModelAndView list() {
        List<ExpenseDTO> expenses = this.expenseService.getAllExpenses();
        return new ModelAndView("expenses/list", "expenses", expenses);
    }

    @RequestMapping("{id}")
    public ModelAndView view(@PathVariable("id") Expense expense) {
        return new ModelAndView("expenses/view", "expense", expense);
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(@ModelAttribute CandidateExpenseDTO expense) {
        return "expenses/form";
    }

    // TODO: Figure out how this should be transactional
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@Valid CandidateExpenseDTO candidateExpenseDTO, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("expenses/form", "formErrors", result.getAllErrors());
        }
        long id = expenseService.createExpense(candidateExpenseDTO);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new expense");
        return new ModelAndView(String.format("redirect:/%s", id), "message.id", id);
    }

}
