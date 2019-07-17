package com.springmvc.service;

import org.junit.Test;

public class UserServiceTest extends BaseServiceTest {
    @Test
    public void findUserByNameAndPassword() {
        System.out.println(userService.selectUserByNameAndPassword("admin1","admin1"));
    }
}