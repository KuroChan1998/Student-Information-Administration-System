package com.springmvc.service;

import org.junit.Test;

public class OtherServiceTest extends BaseServiceTest {

    @Test
    public void selectStuTotalByCollegeOrMajorName() {
    }

    @Test
    public void transferStuTotalToValidJSON() {
        System.out.println(otherService.transferStuTotalToValidJSON("majorUnderCollegeByStuDegree", "电子信息与电气工程学院"));
    }

    @Test
    public void transTeaTotalToValidJSON() {
        System.out.println(otherService.transTeaTotalToValidJSON("", "", ""));
    }
}