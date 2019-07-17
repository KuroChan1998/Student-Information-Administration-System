package com.springmvc.dao;

import org.junit.Test;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void findByNameAndPassword() {
        System.out.println(userMapper.selectUserByNameAndPassword("admin1", "admin1"));
    }
}