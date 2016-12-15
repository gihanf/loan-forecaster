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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gihan.model.CandidateLoanDTO;
import com.gihan.model.Frequency;
import com.gihan.model.LoanDTO;
import com.gihan.model.PaymentScheduleOption;
import com.gihan.service.LoanService;

@Controller
@RequestMapping("/loan")
public class LoanController {
    private static final Log LOG = LogFactory.getLog(LoanController.class);

    @Autowired
    private LoanService loanService;

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
        List<LoanDTO> loans = loanService.getAllLoans();
        return new ModelAndView("loans/list", "loans", loans);
    }

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") @ModelAttribute LoanDTO loanDTO, Model model) {
        model.addAttribute("loan", loanDTO);
        return "loans/view";
    }

//    @RequestMapping(method = RequestMethod.POST, params = "action=Edit")
//    public String edit(ExpenseDTO expenseDTO,
//                       BindingResult result,
//                       RedirectAttributes redirect,
//                       Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("formErrors", result.getAllErrors());
//            redirect.addFlashAttribute("globalMessage", "Successfully created a new expense");
//            return "redirect:/expense/";
//        }
//        expenseService.edit(expenseDTO);
//        return "redirect:/expense/";
//    }
//
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String create(@ModelAttribute CandidateLoanDTO expense) {
        return "loans/form";
    }

    @RequestMapping(method = RequestMethod.POST, params = "action=Create")
    public String create(@Valid CandidateLoanDTO candidateLoanDTO,
                         BindingResult result,
                         RedirectAttributes redirect,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formErrors", result.getAllErrors());
            return "/loans/form";
        }
        loanService.createLoan(candidateLoanDTO);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new loan");
        return "redirect:/loan/";
    }

    @RequestMapping(method = RequestMethod.POST, params = "action=Cancel")
    public String cancel() {
        return "redirect:/loan/";
    }

    @RequestMapping(value = "/{id}/delete")
    public ModelAndView deleteLoan(@PathVariable("id") int loanId, RedirectAttributes redirect) {
        loanService.delete(loanId);
        redirect.addFlashAttribute("globalMessage", "Successfully deleted loan");
        return new ModelAndView("redirect:/loan/");
    }

}
