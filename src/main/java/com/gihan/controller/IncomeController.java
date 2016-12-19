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
import com.gihan.model.Frequency;
import com.gihan.model.IncomeDTO;
import com.gihan.service.PaymentService;

@Controller
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private PaymentService paymentService;

    @ModelAttribute("allFrequencies")
    public List<Frequency> populateTypes() {
        return Arrays.asList(Frequency.values());
    }

    @RequestMapping
    public ModelAndView list() {
        List<IncomeDTO> incomes = this.paymentService.getAllIncomes();
        return new ModelAndView("incomes/list", "incomes", incomes);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String create(@ModelAttribute CandidateExpenseDTO expense) {
        return "incomes/form";
    }

    @RequestMapping(method = RequestMethod.POST, params = "action=Create")
    public String create(@Valid CandidateExpenseDTO candidateExpenseDTO,
                         BindingResult result,
                         RedirectAttributes redirect,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formErrors", result.getAllErrors());
            return "/incomes/form";
        }
        paymentService.createIncome(candidateExpenseDTO);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new income");
        return "redirect:/income/";
    }

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") @ModelAttribute IncomeDTO incomeDTO, Model model) {
        model.addAttribute("income", incomeDTO);
        return "incomes/view";
    }

    @RequestMapping(method = RequestMethod.POST, params = "action=Edit")
    public String edit(IncomeDTO incomeDTO,
                       BindingResult result,
                       RedirectAttributes redirect,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formErrors", result.getAllErrors());
            redirect.addFlashAttribute("globalMessage", "Successfully created a new income source");
            return "redirect:/income/";
        }
        paymentService.edit(incomeDTO);
        return "redirect:/income/";
    }

    @RequestMapping(method = RequestMethod.POST, params = "action=Cancel")
    public String cancel() {
        return "redirect:/income/";
    }

    @RequestMapping(value = "/{id}/delete")
    public ModelAndView deleteIncome(@PathVariable("id") int incomeId, RedirectAttributes redirect) {
        paymentService.deleteIncome(incomeId);
        redirect.addFlashAttribute("globalMessage", "Successfully deleted income");
        return new ModelAndView("redirect:/income/");
    }
}
