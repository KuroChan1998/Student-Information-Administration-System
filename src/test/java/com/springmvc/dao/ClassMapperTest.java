package com.springmvc.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClassMapperTest extends BaseMapperTest {

    @Test
    public void selectClassByMajorName() {
        System.out.println(classMapper.selectClassByMajorName(""));
    }
}