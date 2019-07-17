package com.springmvc.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class MajorMapperTest extends BaseMapperTest {

    @Test
    public void selectMajorByCollegeName() {
        System.out.println(majorMapper.selectMajorByCollegeName("电子信息与电气工程学院"));
    }
}