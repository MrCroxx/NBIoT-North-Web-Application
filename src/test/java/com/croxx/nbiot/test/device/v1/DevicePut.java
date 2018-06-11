package com.croxx.nbiot.test.device.v1;

import com.croxx.nbiot.request.ReqNewDevice;
import com.croxx.nbiot.response.ResDevice;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DevicePut {
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
        ReqNewDevice device = new ReqNewDevice();
        device.setNodeId(nodeId1);
        device.setName(nodeId1);
        mockMvc.perform(
                put("/v1/device/").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(device))
        ).andExpect(status().isOk()).andDo(
                mvcResult -> {
                    String json_str = mvcResult.getResponse().getContentAsString();
                    ResMsg resMsg = om.readValue(json_str, ResMsg.class);
                    ResDevice resDevice = om.convertValue(resMsg.getContent(), ResDevice.class);
                    deviceId1 = resDevice.getDeviceId();
                }
        );
    }

    @Test
    public void case01() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        ReqNewDevice device = new ReqNewDevice();
        device.setNodeId(nodeId1);
        device.setName(nodeId1);
        mockMvc.perform(
                put("/v1/device/").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(device))
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case02() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "wrong_authorization");
        ReqNewDevice device = new ReqNewDevice();
        device.setNodeId(nodeId1);
        device.setName(nodeId1);
        mockMvc.perform(
                put("/v1/device/").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(device))
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case03() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqNewDevice device = new ReqNewDevice();
        device.setNodeId(nodeId1);
        mockMvc.perform(
                put("/v1/device/").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(device))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case04() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqNewDevice device = new ReqNewDevice();
        device.setNodeId(nodeId1);
        device.setName(RandomString(4));
        mockMvc.perform(
                put("/v1/device/").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(device))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case05() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqNewDevice device = new ReqNewDevice();
        device.setNodeId(nodeId1);
        device.setName(RandomString(36));
        mockMvc.perform(
                put("/v1/device/").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(device))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case06() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqNewDevice device = new ReqNewDevice();
        device.setNodeId(null);
        device.setName(nodeId1);
        mockMvc.perform(
                put("/v1/device/").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(device))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case07() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqNewDevice device = new ReqNewDevice();
        device.setNodeId(RandomString(4));
        device.setName(nodeId1);
        mockMvc.perform(
                put("/v1/device/").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(device))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case08() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqNewDevice device = new ReqNewDevice();
        device.setNodeId(RandomString(36));
        device.setName(nodeId1);
        mockMvc.perform(
                put("/v1/device/").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(device))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case09() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqNewDevice device = new ReqNewDevice();
        device.setNodeId(nodeId1);
        device.setName(nodeId1);
        mockMvc.perform(
                put("/v1/device/").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(device))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case10() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization1);
        ReqNewDevice device = new ReqNewDevice();
        device.setNodeId(nodeId2);
        device.setName(nodeId2);
        mockMvc.perform(
                put("/v1/device/").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(device))
        ).andExpect(status().isOk()).andDo(
                mvcResult -> {
                    String json_str = mvcResult.getResponse().getContentAsString();
                    ResMsg resMsg = om.readValue(json_str, ResMsg.class);
                    ResDevice resDevice = om.convertValue(resMsg.getContent(), ResDevice.class);
                    deviceId2 = resDevice.getDeviceId();
                }
        );
    }

    @Test
    public void case11() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization2);
        ReqNewDevice device = new ReqNewDevice();
        device.setNodeId(nodeId3);
        device.setName(nodeId3);
        mockMvc.perform(
                put("/v1/device/").contentType(MediaType.APPLICATION_JSON)
                        .headers(headers).content(om.writeValueAsString(device))
        ).andExpect(status().isOk()).andDo(
                mvcResult -> {
                    String json_str = mvcResult.getResponse().getContentAsString();
                    ResMsg resMsg = om.readValue(json_str, ResMsg.class);
                    ResDevice resDevice = om.convertValue(resMsg.getContent(), ResDevice.class);
                    deviceId3 = resDevice.getDeviceId();
                }
        );
    }
}
