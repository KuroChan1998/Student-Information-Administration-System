package com.jzy.dto.other.senior;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @ClassName StudentTotalGroupBySex
 * @Description 封装根据性别查询人数的类
 * @Date 2019/7/14 17:12
 * @Version 1.0
 **/
public class StudentTotalGroupBySex  extends PersonTotalGroupBySex implements Serializable {
    private static final long serialVersionUID = 5125344342721681633L;

    @Override
    public String toString() {
        return "StudentTotalGroupBySex{} " + super.toString();
    }
}
