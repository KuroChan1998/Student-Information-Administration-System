package com.springmvc.controller;

import com.springmvc.dao.BaseMapperTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class OtherControllerTest {

    @Test
    public void findCollegeStuNumPercent() {
        OtherController otherController=new OtherController();
        otherController.findSexPercent(null,null,null);
    }
}