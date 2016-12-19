package com.gihan.testHelper;

import org.springframework.beans.factory.annotation.Autowired;

import com.gihan.service.PaymentService;

public class TestHelper {

    //TODO: Create helper methods for random Frequency values
    //

    @Autowired
    private static PaymentService expenseCreatorService;

    /*public static ExpenseDTO createRandomExpense() {
        ExpenseDTO expenseDTO = new ExpenseDTO(RandomStringUtils.randomAlphanumeric(30), new BigDecimal(2.2), Frequency.MONTHLY);
        expenseCreatorService.createExpense(expenseDTO);
    }*/
}
