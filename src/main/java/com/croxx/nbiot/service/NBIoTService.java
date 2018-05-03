package com.croxx.nbiot.service;

import com.huawei.iotplatform.client.NorthApiClient;
import com.huawei.iotplatform.client.NorthApiException;
import com.huawei.iotplatform.client.dto.AuthOutDTO;
import com.huawei.iotplatform.client.dto.ClientInfo;
import com.huawei.iotplatform.client.invokeapi.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NBIoTService {
    @Value("${nbiot.appId}")
    private String appId;
    @Value("${nbiot.secret}")
    private String secret;
    @Value("${nbiot.platform.host}")
    private String platformHost;
    @Value("${nbiot.platform.port}")
    private String platformPort;


    public NorthApiClient getNorthApiClient() throws NorthApiException{
            NorthApiClient northApiClient = new NorthApiClient();
            ClientInfo clientInfo = new ClientInfo();
            clientInfo.setAppId(appId);
            clientInfo.setPlatformIp(platformHost);
            clientInfo.setPlatformPort(platformPort);
            clientInfo.setSecret(secret);
            northApiClient.setClientInfo(clientInfo);
            northApiClient.initSSLConfig();
            return northApiClient;
    }

    /*    Setters     */

    public String getAppId() {
        return appId;
    }

    public String getSecret() {
        return secret;
    }
}
