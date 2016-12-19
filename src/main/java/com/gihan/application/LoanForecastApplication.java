/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gihan.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.gihan.convertor.StringToLocalDateConverter;
import com.gihan.model.*;
import com.gihan.repository.PaymentRepository;
import com.gihan.repository.LoanRepository;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.gihan")
public class LoanForecastApplication extends WebMvcConfigurerAdapter {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Bean
    public Converter<String, ExpenseDTO> messageConverter() {
        return new Converter<String, ExpenseDTO>() {
            @Override
            public ExpenseDTO convert(String id) {
                Payment payment = paymentRepository.findOne(Integer.valueOf(id));
                return new ExpenseDTO(
                        payment.getId(),
                        payment.getDescription(),
                        payment.getAmount(),
                        payment.getPaymentSchedule().getFrequency(),
                        payment.getPaymentSchedule().getFirstPaymentDate());
            }
        };
    }

    @Bean
    public Converter<String, LoanDTO> loanMessageConverter() {
        return new Converter<String, LoanDTO>() {
            @Override
            public LoanDTO convert(String id) {
                Loan loan = loanRepository.findOne(Integer.valueOf(id));
                return new LoanDTO(
                        loan.getId(),
                        loan.getDescription(),
                        loan.getPrincipalAmount(),
                        loan.getCurrentBalance(),
                        loan.getInterestRate(),
                        loan.getTerm());
            }
        };
    }

    @Bean
    public Converter<String, IncomeDTO> incomeConverter() {
        return new Converter<String, IncomeDTO>() {
            @Override
            public IncomeDTO convert(String id) {
                Payment payment = paymentRepository.findOne(Integer.valueOf(id));
                return new IncomeDTO(
                        payment.getId(),
                        payment.getDescription(),
                        payment.getAmount(),
                        payment.getPaymentSchedule().getFrequency(),
                        payment.getPaymentSchedule().getFirstPaymentDate());
            }
        };
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateConverter("dd/mm/yy"));
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LoanForecastApplication.class, args);
    }
}
