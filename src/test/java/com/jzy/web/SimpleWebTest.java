package com.jzy.web;

import com.jzy.App;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName SimpleWebTest
 * @description //TODO
 * @date 2019/9/29 16:29
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleWebTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void getUserTest() throws Exception {
        Assert.assertEquals(this.testRestTemplate.getForObject("http://localhost:" + port + "/user/find?username=111&password=aaa", String.class), "JSON_DATA");

    }
}
