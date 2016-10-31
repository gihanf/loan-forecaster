package com.gihan.testHelper;

import java.math.BigDecimal;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.gihan.model.ExpenseDTO;
import com.gihan.model.Frequency;
import com.gihan.service.ExpenseService;

public class TestHelper {

    //TODO: Create helper methods for random Frequency values
    //

    @Autowired
    private static ExpenseService expenseCreatorService;

    /*public static ExpenseDTO createRandomExpense() {
        ExpenseDTO expenseDTO = new ExpenseDTO(RandomStringUtils.randomAlphanumeric(30), new BigDecimal(2.2), Frequency.MONTHLY);
        expenseCreatorService.createExpense(expenseDTO);
    }*/
}
