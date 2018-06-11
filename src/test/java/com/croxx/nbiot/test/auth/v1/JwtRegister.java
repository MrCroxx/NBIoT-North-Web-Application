package com.croxx.nbiot.test.auth.v1;

import com.croxx.nbiot.request.ReqNewUser;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.croxx.nbiot.test.TestSharedObjects.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JwtRegister {

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
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(name1 + "@123.com");
        reqNewUser.setPassword(name1);
        reqNewUser.setName(name1);
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isOk());
    }

    @Test
    public void case01() throws Exception {
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(null);
        reqNewUser.setPassword(name1);
        reqNewUser.setName(name1);
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case02() throws Exception {
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(name1);
        reqNewUser.setPassword(name1);
        reqNewUser.setName(name1);
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case03() throws Exception {
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(RandomString(1) + "@1.x");
        reqNewUser.setPassword(name1);
        reqNewUser.setName(name1);
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case04() throws Exception {
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(RandomString(32) + "@123.com");
        reqNewUser.setPassword(name1);
        reqNewUser.setName(name1);
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case05() throws Exception {
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(name1 + "@123.com");
        reqNewUser.setPassword(name1);
        reqNewUser.setName(name1);
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case06() throws Exception {
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(name1 + "@123.com");
        reqNewUser.setPassword(name1);
        reqNewUser.setName(null);
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case07() throws Exception {
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(name1 + "@123.com");
        reqNewUser.setPassword(name1);
        reqNewUser.setName(RandomString(4));
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case08() throws Exception {
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(name1 + "@123.com");
        reqNewUser.setPassword(name1);
        reqNewUser.setName(RandomString(36));
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case09() throws Exception {
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(name1 + "@123.com");
        reqNewUser.setPassword(null);
        reqNewUser.setName(name1);
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case10() throws Exception {
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(name1 + "@123.com");
        reqNewUser.setPassword(RandomString(4));
        reqNewUser.setName(name1);
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case11() throws Exception {
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(name1 + "@123.com");
        reqNewUser.setPassword(RandomString(36));
        reqNewUser.setName(name1);
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case12() throws Exception {
        ReqNewUser reqNewUser = new ReqNewUser();
        reqNewUser.setEmail(name2 + "@123.com");
        reqNewUser.setPassword(name2);
        reqNewUser.setName(name2);
        mockMvc.perform(
                post("/v1/jwt/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqNewUser))
        ).andExpect(status().isOk());
    }


}
