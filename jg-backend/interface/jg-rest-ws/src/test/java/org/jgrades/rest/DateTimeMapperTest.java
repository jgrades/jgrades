/*
 * Copyright (C) 2016 the original author or authors.
 *
 * This file is part of jGrades Application Project.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.jgrades.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jgrades.common.CommonContext;
import org.jgrades.rest.lic.LicMockConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonContext.class, LicMockConfig.class, RestContext.class})
@WebAppConfiguration
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class DateTimeMapperTest {
    private static final LocalDateTime EXPECTED_DATE_TIME = LocalDateTime.now();

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(this)
                .setHandlerExceptionResolvers(new RestContext().restExceptionResolver())
                .build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    LocalDateTime returnDateTime() {
        return EXPECTED_DATE_TIME;
    }

    @Test
    public void shouldDeserializeDateTimeCorrectly_fromWebServiceResponse() throws Exception {
        // when
        MvcResult mvcResult = mockMvc.perform(get("/test")).andReturn();
        String timestamp = mvcResult.getResponse().getContentAsString();

        // then
        LocalDateTime dateTime = jacksonObjectMapper.readValue(timestamp, LocalDateTime.class);
        assertThat(dateTime).isEqualTo(EXPECTED_DATE_TIME);
    }
}
