package com.jzy.dao;

import org.junit.Test;

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