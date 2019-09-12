package com.jzy.dao;

import com.jzy.util.major.MajorUtil;
import org.junit.Test;

public class MajorMapperTest extends BaseMapperTest {

    @Test
    public void selectMajorByCollegeName() {
        System.out.println(majorMapper.selectMajorByCollegeName("电子信息与电气工程学院"));
    }

    @Test
    public void selectMajorByName() {
        System.out.println(MajorUtil.isValidMajorAllInfo(majorMapper.selectMajorByName("电影与电视系")));
    }
}