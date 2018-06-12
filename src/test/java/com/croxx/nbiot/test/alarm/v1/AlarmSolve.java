package com.croxx.nbiot.test.alarm.v1;

import com.croxx.nbiot.request.ReqAlarmHandler;
import com.croxx.nbiot.test.model.ReqAlarmHandlerTest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlarmSolve {
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
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + alarmId2);
        request.setRisk("" + 3);
        request.setType("" + 1);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isOk()).andDo(
                (mvcResult) -> {
                    LogResContent(mvcResult, getClass());
                }
        );
    }

    @Test
    public void case01() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + alarmId2);
        request.setRisk("" + 3);
        request.setType("" + 1);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case02() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "wrong_authorization");
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + alarmId2);
        request.setRisk("" + 3);
        request.setType("" + 1);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case03() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId(null);
        request.setRisk("" + 3);
        request.setType("" + 1);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case04() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("ThisIsAString");
        request.setRisk("" + 3);
        request.setType("" + 1);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case05() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + RandomLong(10000, 50000));
        request.setRisk("" + 3);
        request.setType("" + 1);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case06() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + alarmId3);
        request.setRisk("" + 3);
        request.setType("" + 1);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case07() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + alarmId2);
        request.setRisk(null);
        request.setType("" + 1);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case08() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + alarmId2);
        request.setRisk("ThisIsAString");
        request.setType("" + 1);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case09() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + alarmId2);
        request.setRisk("" + 0);
        request.setType("" + 1);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case10() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + alarmId2);
        request.setRisk("" + 6);
        request.setType("" + 1);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case11() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + alarmId2);
        request.setRisk("" + 3);
        request.setType(null);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isOk());
    }

    @Test
    public void case12() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + alarmId2);
        request.setRisk("" + 3);
        request.setType("ThisIsAString");
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case13() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + alarmId2);
        request.setRisk("" + 3);
        request.setType("" + (-2));
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case14() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqAlarmHandlerTest request = new ReqAlarmHandlerTest();
        request.setId("" + alarmId2);
        request.setRisk("" + 3);
        request.setType("" + 2);
        mockMvc.perform(
                post("/v1/alarm/solve").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(request))
        ).andExpect(status().isBadRequest());
    }
}
