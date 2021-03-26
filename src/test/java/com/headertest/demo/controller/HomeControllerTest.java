package com.headertest.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import com.jayway.jsonpath.JsonPath;

import java.util.HashMap;
import java.util.Map;

@AutoConfigureMockMvc
@SpringBootTest
class HomeControllerTest {

    @Autowired
    private HomeController homeController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("测试读取header值")
    void send() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("h1","h1aa");
        httpHeaders.add("h2","h2bb");
        httpHeaders.add("h3","h3cc");

        MvcResult mvcResult = mockMvc.perform(get("/home/send")
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("返回:"+content);
        assertThat(content, equalTo("res:h1aa_h2bb_h3cc"));
    }

    @Test
    @DisplayName("测试读取header值:map形式")
    void send_map() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("h1", "h1a");
        map.put("h2", "h2b");
        map.put("h3", "h3c");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(map);

        MvcResult mvcResult = mockMvc.perform(get("/home/send")
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("返回:"+content);
        assertThat(content, equalTo("res:h1a_h2b_h3c"));
    }
}