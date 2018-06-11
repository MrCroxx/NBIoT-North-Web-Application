package com.croxx.nbiot.test.auth.v1;

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

import static com.croxx.nbiot.test.TestSharedObjects.authorization1;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JwtRefresh {

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
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", authorization1);
        mockMvc.perform(
                post("/v1/jwt/refresh").contentType(MediaType.APPLICATION_JSON).headers(httpHeaders)
        ).andExpect(status().isOk());
    }

    @Test
    public void case01() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        mockMvc.perform(
                post("/v1/jwt/refresh").contentType(MediaType.APPLICATION_JSON).headers(httpHeaders)
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case02() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer wrong_access_token");
        mockMvc.perform(
                post("/v1/jwt/refresh").contentType(MediaType.APPLICATION_JSON).headers(httpHeaders)
        ).andExpect(status().isForbidden());
    }

}

