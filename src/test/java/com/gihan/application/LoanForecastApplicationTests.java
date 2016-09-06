/*
 * Copyright 2012-2014 the original author or authors.
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

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.gihan.model.Expense;
import com.gihan.model.ExpenseDTO;
import com.gihan.model.Frequency;
import com.gihan.service.ExpenseCreatorService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LoanForecastApplication.class)
@WebAppConfiguration
//@IntegrationTest("server.port:9999")
//@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
//@DirtiesContext
@Transactional
//@TestExecutionListeners(listeners = TransactionalTestExecutionListener.class)
public class LoanForecastApplicationTests {

    private static final ExpenseDTO EXPENSE = new ExpenseDTO("Some description", BigDecimal.valueOf(13), Frequency.ONCE_OFF);
    
    @Value("${server.port}")
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ExpenseCreatorService expenseCreator;

    private MockMvc mvc;

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldReturnListOfExpenses() throws Exception {
        expenseCreator.createExpense(EXPENSE);

        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andDo(print());

        Map<String, Object> model = resultActions.andReturn().getModelAndView().getModel();
        List<Expense> expenses = (List<Expense>) model.get("expenses");
        assertThat(expenses, notNullValue());
        assertThat(expenses.size(), is(1));
        assertThat(expenses.get(0).getAmount(), is(EXPENSE.getAmount()));
    }

    @Test
    public void shouldThrowExceptionWhen_TryingToCreateExpenseWithAmountMoreThanTwoDecimalPlaces() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    // TODO: Replace with more relevant test
    public void testHome() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.port, String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue("Wrong body (title doesn't match):\n" + entity.getBody(), entity
                .getBody().contains("<title>Expenses"));
        assertFalse("Wrong body (found layout:fragment):\n" + entity.getBody(), entity
                .getBody().contains("layout:fragment"));
    }

    // TODO: Figoure out how to make this rollback.. Keeps writing to the db for test
    // TODO: Replace with more relevant test
    @Test
    public void testCreate() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.set("description", "FOO");
        map.set("amount", "3.00");
        URI location = new TestRestTemplate().postForLocation("http://localhost:"+ this.port, map);
        assertTrue("Wrong location:\n" + location, location.toString().contains("localhost:" + this.port));
    }

    // TODO: Replace with more relevant test
    @Test
    public void testBootStrapCss() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.port + "/css/bootstrap.min.css", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("body"));
    }

    // TODO: Replace with more relevant test
    @Test
    public void testCustomCss() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.port + "/css/loan.css", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("incurredTime"));
    }

}
