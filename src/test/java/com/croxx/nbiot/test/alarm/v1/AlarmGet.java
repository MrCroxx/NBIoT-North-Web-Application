package com.croxx.nbiot.test.alarm.v1;

import com.croxx.nbiot.request.ReqJwtUser;
import com.croxx.nbiot.response.ResAlarm;
import com.croxx.nbiot.response.ResDevice;
import com.croxx.nbiot.response.ResJwtAccessToken;
import com.croxx.nbiot.response.ResMsg;
import org.apache.http.client.methods.HttpHead;
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

import java.util.List;

import static com.croxx.nbiot.test.TestSharedObjects.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlarmGet {
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
                get("/v1/alarm/").contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isOk()).andDo(
                (mvcResult) -> {
                    LogResContent(mvcResult, getClass());
                    String json_str = mvcResult.getResponse().getContentAsString();
                    ResMsg resMsg = om.readValue(json_str, ResMsg.class);
                    ResAlarm alarms[] = om.readValue(om.writeValueAsString(resMsg.getContent()), ResAlarm[].class);
                    alarmId2 = alarms[0].getId();
                }
        );
    }

    @Test
    public void case01() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        mockMvc.perform(
                get("/v1/alarm/").contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case02() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "wrong_authorization");
        mockMvc.perform(
                get("/v1/alarm/").contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case03() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization2);
        mockMvc.perform(
                get("/v1/alarm/").contentType(MediaType.APPLICATION_JSON).headers(headers)
        ).andExpect(status().isOk()).andDo(
                (mvcResult) -> {
                    LogResContent(mvcResult, getClass());
                    String json_str = mvcResult.getResponse().getContentAsString();
                    ResMsg resMsg = om.readValue(json_str, ResMsg.class);
                    ResAlarm alarms[] = om.readValue(om.writeValueAsString(resMsg.getContent()), ResAlarm[].class);
                    alarmId3 = alarms[0].getId();
                }
        );
    }
}
