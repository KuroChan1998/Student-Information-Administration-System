package com.springmvc.dto.other.senior;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @ClassName PersonTotalGroupBySex
 * @Description 封装根据性别查询人数的类
 * @Date 2019/7/15 11:14
 * @Version 1.0
 **/
public class PersonTotalGroupBySex extends ObjectTotal implements Serializable {
    private static final long serialVersionUID = -2901815156842821368L;

    //性别
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "PersonTotalGroupBySex{" +
                "sex='" + sex + '\'' +
                "} " + super.toString();
    }
}
