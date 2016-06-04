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

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Basic integration tests for demo application.
 *
 * @author Dave Syer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LoanForecastApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:9999")
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@DirtiesContext
@Transactional
//@TestExecutionListeners(listeners = TransactionalTestExecutionListener.class)
public class LoanForecastApplicationTests {

    @Value("${server.port}")
    private int port;

    @Test
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
    @Test
    public void testCreate() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.set("description", "FOO");
        map.set("amount", "3.00");
        URI location = new TestRestTemplate().postForLocation("http://localhost:"+ this.port, map);
        assertTrue("Wrong location:\n" + location, location.toString().contains("localhost:" + this.port));
    }

    @Test
    public void testBootStrapCss() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.port + "/css/bootstrap.min.css", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("body"));
    }

    @Test
    public void testCustomCss() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.port + "/css/loan.css", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("incurredTime"));
    }

}
