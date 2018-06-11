package com.croxx.nbiot.test;

import com.croxx.nbiot.model.Device;
import com.croxx.nbiot.test.auth.v1.JwtRegister;
import com.croxx.nbiot.test.auth.v1.JwtAuth;
import com.croxx.nbiot.test.auth.v1.JwtRefresh;
import com.croxx.nbiot.test.device.v1.DeviceDelete;
import com.croxx.nbiot.test.device.v1.DeviceDetail;
import com.croxx.nbiot.test.device.v1.DeviceGet;
import com.croxx.nbiot.test.device.v1.DevicePut;
import com.croxx.nbiot.test.nbiotservice.v1.DeviceDataUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static com.croxx.nbiot.test.TestSharedObjects.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JwtRegister.class,
        JwtAuth.class,
        JwtRefresh.class,
        DevicePut.class,
        DeviceGet.class,
        DeviceDelete.class,
        DeviceDetail.class,
        DeviceDataUpdate.class
})
public class NbiotApplicationTests {

    @BeforeClass
    public static void SetUpOnce() {
        om = new ObjectMapper();
        name1 = RandomString(8);
        name2 = RandomString(8);
        nodeId1 = RandomString(8);
        nodeId2 = RandomString(8);
        nodeId3 = RandomString(8);
    }

    @Test
    public void test() {

    }

}
