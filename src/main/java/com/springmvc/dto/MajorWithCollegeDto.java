package com.springmvc.dto;

import com.springmvc.entity.Major;
import com.springmvc.service.MajorService;

/**
 * @ClassName MajorWithCollegeDto
 * @Author JinZhiyun
 * @Description 专业及其附带信息dto
 * @Date 2019/4/17 20:59
 * @Version 1.0
 **/
public class MajorWithCollegeDto extends Major {

    private String majorCollegeName;


    private String majorTeaName;

    public String getMajorCollegeName() {
        return majorCollegeName;
    }

    public void setMajorCollegeName(String majorCollegeName) {
        this.majorCollegeName = majorCollegeName;
    }

    public String getMajorTeaName() {
        return majorTeaName;
    }

    public void setMajorTeaName(String majorTeaName) {
        this.majorTeaName = majorTeaName;
    }

    @Override
    public String toString() {
        return super.toString()+"MajorWithCollegeDto{" +
                "majorCollegeName='" + majorCollegeName + '\'' +
                ", majorTeaName='" + majorTeaName + '\'' +
                '}';
    }
}
