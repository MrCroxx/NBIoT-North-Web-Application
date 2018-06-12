package com.croxx.nbiot.test.alarm.v1;

import com.croxx.nbiot.response.ResAlarm;
import com.croxx.nbiot.response.ResMsg;
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

import static com.croxx.nbiot.test.TestSharedObjects.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlarmDetailGet {
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
                get("/v1/alarm/detail/" + alarmId2).contentType(MediaType.APPLICATION_JSON).headers(headers)
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
                get("/v1/alarm/detail/" + alarmId2).contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case02() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "wrong_authorization");
        mockMvc.perform(
                get("/v1/alarm/detail/" + alarmId2).contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case03() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        mockMvc.perform(
                get("/v1/alarm/detail/" + "ThisIsAString").contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case04() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        mockMvc.perform(
                get("/v1/alarm/detail/" + RandomLong(10000, 50000)).contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case05() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        mockMvc.perform(
                get("/v1/alarm/detail/" + alarmId3).contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isBadRequest());
    }
}
