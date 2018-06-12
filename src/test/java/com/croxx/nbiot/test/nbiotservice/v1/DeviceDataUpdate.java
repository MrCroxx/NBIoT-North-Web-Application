package com.croxx.nbiot.test.nbiotservice.v1;

import com.croxx.nbiot.request.nbiotservice.ReqNBIoTService;
import com.croxx.nbiot.request.nbiotservice.ReqNBIoTServiceNotify;
import com.croxx.nbiot.service.NBIoTService;
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

import java.util.Date;
import java.util.HashMap;

import static com.croxx.nbiot.test.TestSharedObjects.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeviceDataUpdate {
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

        ReqNBIoTServiceNotify request = new ReqNBIoTServiceNotify();
        ReqNBIoTService service = new ReqNBIoTService();
        request.setDeviceId(deviceId2);
        request.setGatewayId(deviceId2);
        request.setNotifyType("deviceDataChanged");
        request.setService(service);
        service.setServiceType(ReqNBIoTService.SERVICE_TYPE_BATTERY);
        service.setServiceId(ReqNBIoTService.SERVICE_TYPE_BATTERY);
        service.setEventTime(null);
        service.setData(new HashMap<String, String>() {{
            put("level", "3");
        }});

        mockMvc.perform(
                post("/v1/nbiot/deviceDataUpdate").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))
        ).andExpect(status().isOk()).andDo(
                (mvcResult) -> {
                    LogResContent(mvcResult, getClass());
                }
        );
    }

    @Test
    public void case01() throws Exception {

        ReqNBIoTServiceNotify request = new ReqNBIoTServiceNotify();
        ReqNBIoTService service = new ReqNBIoTService();
        request.setDeviceId(deviceId2);
        request.setGatewayId(deviceId2);
        request.setNotifyType("deviceDataChanged");
        request.setService(service);
        service.setServiceType(ReqNBIoTService.SERVICE_TYPE_NETWORK);
        service.setServiceId(ReqNBIoTService.SERVICE_TYPE_NETWORK);
        service.setEventTime(null);
        service.setData(new HashMap<String, String>() {{
            put("quality", "3");
        }});

        mockMvc.perform(
                post("/v1/nbiot/deviceDataUpdate").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))
        ).andExpect(status().isOk()).andDo(
                (mvcResult) -> {
                    LogResContent(mvcResult, getClass());
                }
        );
    }

    @Test
    public void case02() throws Exception {

        ReqNBIoTServiceNotify request = new ReqNBIoTServiceNotify();
        ReqNBIoTService service = new ReqNBIoTService();
        request.setDeviceId(deviceId2);
        request.setGatewayId(deviceId2);
        request.setNotifyType("deviceDataChanged");
        request.setService(service);
        service.setServiceType(ReqNBIoTService.SERVICE_TYPE_LOCATION);
        service.setServiceId(ReqNBIoTService.SERVICE_TYPE_LOCATION);
        service.setEventTime(null);
        service.setData(new HashMap<String, String>() {{
            put("longitude", "1122560901");
            put("latitude", "1109372436");
        }});

        mockMvc.perform(
                post("/v1/nbiot/deviceDataUpdate").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))
        ).andExpect(status().isOk()).andDo(
                (mvcResult) -> {
                    LogResContent(mvcResult, getClass());
                }
        );
    }

    @Test
    public void case03() throws Exception {

        ReqNBIoTServiceNotify request = new ReqNBIoTServiceNotify();
        ReqNBIoTService service = new ReqNBIoTService();
        request.setDeviceId(deviceId2);
        request.setGatewayId(deviceId2);
        request.setNotifyType("deviceDataChanged");
        request.setService(service);
        service.setServiceType(ReqNBIoTService.SERVICE_TYPE_CLICK);
        service.setServiceId(ReqNBIoTService.SERVICE_TYPE_CLICK);
        service.setEventTime(null);
        service.setData(new HashMap<String, String>() {{
            put("holdtime", "1075838976");
        }});

        mockMvc.perform(
                post("/v1/nbiot/deviceDataUpdate").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))
        ).andExpect(status().isOk()).andDo(
                (mvcResult) -> {
                    LogResContent(mvcResult, getClass());
                }
        );
    }

    @Test
    public void case04() throws Exception {
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
    public void case05() throws Exception {

        ReqNBIoTServiceNotify request = new ReqNBIoTServiceNotify();
        ReqNBIoTService service = new ReqNBIoTService();
        request.setDeviceId(deviceId3);
        request.setGatewayId(deviceId3);
        request.setNotifyType("deviceDataChanged");
        request.setService(service);
        service.setServiceType(ReqNBIoTService.SERVICE_TYPE_CLICK);
        service.setServiceId(ReqNBIoTService.SERVICE_TYPE_CLICK);
        service.setEventTime(null);
        service.setData(new HashMap<String, String>() {{
            put("holdtime", "1075838976");
        }});

        mockMvc.perform(
                post("/v1/nbiot/deviceDataUpdate").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(request))
        ).andExpect(status().isOk()).andDo(
                (mvcResult) -> {
                    LogResContent(mvcResult, getClass());
                }
        );
    }
}
