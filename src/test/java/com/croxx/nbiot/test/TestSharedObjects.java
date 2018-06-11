package com.croxx.nbiot.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Random;

public class TestSharedObjects {

    public static ObjectMapper om;
    public static String name1;
    public static String name2;
    public static String authorization1;
    public static String authorization2;
    public static String nodeId1;
    public static String nodeId2;
    public static String nodeId3;
    public static String deviceId1;
    public static String deviceId2;
    public static String deviceId3;


    public static String RandomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    public static void LogResContent(MvcResult mvcResult, Type type) throws UnsupportedEncodingException {
        Logger logger = LoggerFactory.getLogger(type.getClass());
        logger.info("**************************************");
        logger.info(mvcResult.getResponse().getContentAsString());
        logger.info("**************************************");
    }
}
