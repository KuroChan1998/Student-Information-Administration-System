package com.springmvc.dto;

import com.springmvc.entity.College;

/**
 * @ClassName CollegeDto
 * @Author JinZhiyun
 * @Description 学院及其附带信息dto
 * @Date 2019/4/17 23:09
 * @Version 1.0
 **/
public class CollegeDto extends College {

    private String collegeTeaName;

    public String getCollegeTeaName() {
        return collegeTeaName;
    }

    public void setCollegeTeaName(String collegeTeaName) {
        this.collegeTeaName = collegeTeaName;
    }

    @Override
    public String toString() {
        return super.toString()+"CollegeDto{" +
                "collegeTeaName='" + collegeTeaName + '\'' +
                '}';
    }
}
