package com.gihan.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

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

import com.gihan.model.CandidateExpenseDTO;
import com.gihan.model.ExpenseDTO;
import com.gihan.model.Frequency;
import com.gihan.service.PaymentService;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private PaymentService paymentService;

    @ModelAttribute("allFrequencies")
    public List<Frequency> populateTypes() {
        return Arrays.asList(Frequency.values());
    }

    @RequestMapping
    public ModelAndView list() {
        List<ExpenseDTO> expenses = this.paymentService.getAllExpenses();
        return new ModelAndView("expenses/list", "expenses", expenses);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String create(@ModelAttribute CandidateExpenseDTO expense) {
        return "expenses/form";
    }

    @RequestMapping(method = RequestMethod.POST, params = "action=Create")
    public String create(@Valid CandidateExpenseDTO candidateExpenseDTO,
                         BindingResult result,
                         RedirectAttributes redirect,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formErrors", result.getAllErrors());
            return "/expenses/form";
        }
        paymentService.createExpense(candidateExpenseDTO);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new expense");
        return "redirect:/expense/";
    }

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") @ModelAttribute ExpenseDTO expenseDTO, Model model) {
        model.addAttribute("expense", expenseDTO);
        return "expenses/view";
    }

    @RequestMapping(method = RequestMethod.POST, params = "action=Cancel")
    public String cancel() {
        return "redirect:/expense/";
    }

    @RequestMapping(method = RequestMethod.POST, params = "action=Edit")
    public String edit(ExpenseDTO expenseDTO,
                       BindingResult result,
                       RedirectAttributes redirect,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formErrors", result.getAllErrors());
            redirect.addFlashAttribute("globalMessage", "Successfully created a new expense");
            return "redirect:/expense/";
        }
        paymentService.edit(expenseDTO);
        return "redirect:/expense/";
    }

    @RequestMapping(value = "/{id}/delete")
    public ModelAndView deleteExpense(@PathVariable("id") int expenseId, RedirectAttributes redirect) {
        paymentService.delete(expenseId);
        redirect.addFlashAttribute("globalMessage", "Successfully deleted expense");
        return new ModelAndView("redirect:/expense/");
    }
}
