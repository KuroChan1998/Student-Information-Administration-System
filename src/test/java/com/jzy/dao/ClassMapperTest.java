package com.jzy.dao;

import com.jzy.util.clazz.ClassUtil;
import org.junit.Test;

public class ClassMapperTest extends BaseMapperTest {

    @Test
    public void selectClassByMajorName() {
        System.out.println(classMapper.selectClassByMajorName(""));
    }

    @Test
    public void selectClassByName() {
        System.out.println(ClassUtil.isValidClassAllInfo(classMapper.selectClassByName("2016级电影电视1班")));
    }
}