package com.gihan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gihan.model.ExpenseDTO;
import com.gihan.model.IncomeDTO;
import com.gihan.service.PaymentService;

@Controller
@RequestMapping("/income")
public class IncomeController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping
    public ModelAndView list() {
        List<IncomeDTO> incomes = this.paymentService.getAllIncomes();
        return new ModelAndView("incomes/list", "incomes", incomes);
    }


}
