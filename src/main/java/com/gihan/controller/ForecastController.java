package com.gihan.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ForecastController {

    @RequestMapping
    public ModelAndView home() {
        List<String> months = Arrays.asList("Jan 40", "Feb 40", "Mar 40");
        return new ModelAndView("home", "months", months);
    }
}
