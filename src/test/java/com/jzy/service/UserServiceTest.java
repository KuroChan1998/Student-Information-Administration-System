package com.jzy.service;

import com.jzy.entity.User;
import org.junit.Test;

public class UserServiceTest extends BaseServiceTest {
    @Test
    public void findUserByNameAndPassword() {
        System.out.println(userService.selectUserByNameAndPassword("admin1","admin1"));
    }

    @Test
    public void updateResetUserInfo() {
        System.out.println(userService.updateResetUserInfo(new User()));
    }
}