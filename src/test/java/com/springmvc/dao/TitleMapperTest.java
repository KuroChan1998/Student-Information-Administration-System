package com.springmvc.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class TitleMapperTest extends BaseMapperTest {

    @Test
    public void selectAllTitle() {
        System.out.println(titleMapper.selectAllTitle());
    }

    @Test
    public void selectAllTitleName() {
        System.out.println(titleMapper.selectAllTitleName());
    }
}