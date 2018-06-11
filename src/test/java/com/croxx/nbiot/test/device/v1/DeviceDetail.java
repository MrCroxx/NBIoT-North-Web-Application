package com.croxx.nbiot.test.device.v1;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

import static com.croxx.nbiot.test.TestSharedObjects.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeviceDetail {
    @Autowired
    private WebApplicationContext webApplicationContext;
    public MockMvc mockMvc;

    @Before
    public void SetUpTest() throws Exception {
        mockMvc = MockMvcBuilders.
                webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void case00() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        mockMvc.perform(
                get("/v1/device/" + deviceId2).contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isOk()).andDo(
                (mvcResult) -> {
                    LogResContent(mvcResult, getClass());
                }
        );
    }

    @Test
    public void case01() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        mockMvc.perform(
                get("/v1/device/" + deviceId1).contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case02() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "wrong_authorization");
        mockMvc.perform(
                get("/v1/device/" + deviceId1).contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case03() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        mockMvc.perform(
                get("/v1/device/" + RandomString(4)).contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case04() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        mockMvc.perform(
                get("/v1/device/" + RandomString(64)).contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isBadRequest()).andDo(
                (mvcResult) -> {
                    LogResContent(mvcResult, getClass());
                }
        );
    }

    @Test
    public void case05() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        mockMvc.perform(
                get("/v1/device/" + RandomString(36)).contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case06() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        mockMvc.perform(
                get("/v1/device/" + deviceId3).contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isBadRequest());
    }
}

