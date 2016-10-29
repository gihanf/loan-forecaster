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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gihan.model.Expense;
import com.gihan.model.ExpenseDTO;
import com.gihan.model.Frequency;
import com.gihan.model.PaymentScheduleOption;
import com.gihan.repository.ExpenseRepository;
import com.gihan.service.ExpenseCreatorService;

@Controller
@RequestMapping("/")
public class MessageController {
    private static final Log LOG = LogFactory.getLog(MessageController.class);

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseCreatorService expenseCreatorService;

    @RequestMapping
    public ModelAndView list() {
        List<Expense> expenses = this.expenseRepository.findAll();
        //TODO - This should really be converted to a DTO type before it's put into the model!!
//        expenseCreatorService.getAllExpenses();
        return new ModelAndView("expenses/list", "expenses", expenses);
    }

    @ModelAttribute("allFrequencies")
    public List<Frequency> populateTypes() {
        return Arrays.asList(Frequency.values());
    }

    @ModelAttribute("paymentScheduleOptions")
    public List<PaymentScheduleOption> populateIncurredOptions() {
        return Arrays.asList(PaymentScheduleOption.values());
    }

    @RequestMapping("{id}")
    public ModelAndView view(@PathVariable("id") Expense expense) {
        return new ModelAndView("expenses/view", "expense", expense);
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(@ModelAttribute ExpenseDTO expense) {
        return "expenses/form";
    }

    // TODO: Figure out how this should be transactional
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView create(@Valid ExpenseDTO expenseDTO, BindingResult result,
                               RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("expenses/form", "formErrors", result.getAllErrors());
        }
        long id = expenseCreatorService.createExpense(expenseDTO);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new expense");
        return new ModelAndView(String.format("redirect:/%s", id), "message.id", id);
    }

}
