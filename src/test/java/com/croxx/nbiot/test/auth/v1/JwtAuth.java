package com.croxx.nbiot.test.auth.v1;

import com.croxx.nbiot.request.ReqJwtUser;
import com.croxx.nbiot.response.ResJwtAccessToken;
import com.croxx.nbiot.response.ResMsg;
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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static com.croxx.nbiot.test.TestSharedObjects.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JwtAuth {
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
        ReqJwtUser reqJwtUser = new ReqJwtUser();
        reqJwtUser.setUsername(name1 + "@123.com");
        reqJwtUser.setPassword(name1);
        mockMvc.perform(
                post("/v1/jwt/auth").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqJwtUser))
        ).andExpect(status().isOk()).andDo(
                (mvcResult) -> {
                    String json_str = mvcResult.getResponse().getContentAsString();
                    ResMsg resMsg = om.readValue(json_str, ResMsg.class);
                    ResJwtAccessToken resJwtAccessToken = om.convertValue(resMsg.getContent(), ResJwtAccessToken.class);
                    authorization1 = "Bearer " + resJwtAccessToken.getAccess_token();
                }
        );
    }

    @Test
    public void case01() throws Exception {
        ReqJwtUser reqJwtUser = new ReqJwtUser();
        reqJwtUser.setUsername(null);
        reqJwtUser.setPassword(name1);
        mockMvc.perform(
                post("/v1/jwt/auth").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqJwtUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case02() throws Exception {
        ReqJwtUser reqJwtUser = new ReqJwtUser();
        reqJwtUser.setUsername(name1);
        reqJwtUser.setPassword(name1);
        mockMvc.perform(
                post("/v1/jwt/auth").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqJwtUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case03() throws Exception {
        ReqJwtUser reqJwtUser = new ReqJwtUser();
        reqJwtUser.setUsername(RandomString(1) + "@1.x");
        reqJwtUser.setPassword(name1);
        mockMvc.perform(
                post("/v1/jwt/auth").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqJwtUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case04() throws Exception {
        ReqJwtUser reqJwtUser = new ReqJwtUser();
        reqJwtUser.setUsername(RandomString(32) + "@123.com");
        reqJwtUser.setPassword(name1);
        mockMvc.perform(
                post("/v1/jwt/auth").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqJwtUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case05() throws Exception {
        ReqJwtUser reqJwtUser = new ReqJwtUser();
        reqJwtUser.setUsername(RandomString(8) + "@123.com");
        reqJwtUser.setPassword(name1);
        mockMvc.perform(
                post("/v1/jwt/auth").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqJwtUser))
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case06() throws Exception {
        ReqJwtUser reqJwtUser = new ReqJwtUser();
        reqJwtUser.setUsername(name1 + "@123.com");
        reqJwtUser.setPassword(null);
        mockMvc.perform(
                post("/v1/jwt/auth").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqJwtUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case07() throws Exception {
        ReqJwtUser reqJwtUser = new ReqJwtUser();
        reqJwtUser.setUsername(name1 + "@123.com");
        reqJwtUser.setPassword("wrong_password");
        mockMvc.perform(
                post("/v1/jwt/auth").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqJwtUser))
        ).andExpect(status().isForbidden());
    }

    @Test
    public void case08() throws Exception {
        ReqJwtUser reqJwtUser = new ReqJwtUser();
        reqJwtUser.setUsername(name1 + "@123.com");
        reqJwtUser.setPassword(RandomString(4));
        mockMvc.perform(
                post("/v1/jwt/auth").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqJwtUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case09() throws Exception {
        ReqJwtUser reqJwtUser = new ReqJwtUser();
        reqJwtUser.setUsername(name1 + "@123.com");
        reqJwtUser.setPassword(RandomString(36));
        mockMvc.perform(
                post("/v1/jwt/auth").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqJwtUser))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void case10() throws Exception {
        ReqJwtUser reqJwtUser = new ReqJwtUser();
        reqJwtUser.setUsername(name2 + "@123.com");
        reqJwtUser.setPassword(name2);
        mockMvc.perform(
                post("/v1/jwt/auth").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(reqJwtUser))
        ).andExpect(status().isOk()).andDo(
                (mvcResult) -> {
                    String json_str = mvcResult.getResponse().getContentAsString();
                    ResMsg resMsg = om.readValue(json_str, ResMsg.class);
                    ResJwtAccessToken resJwtAccessToken = om.convertValue(resMsg.getContent(), ResJwtAccessToken.class);
                    authorization2 = "Bearer " + resJwtAccessToken.getAccess_token();
                }
        );
    }

}
