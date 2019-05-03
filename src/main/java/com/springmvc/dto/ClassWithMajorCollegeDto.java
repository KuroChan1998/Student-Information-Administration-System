package com.springmvc.dto;

import com.springmvc.entity.Class;

/**
 * @ClassName ClassWithMajorCollegeDto
 * @Author JinZhiyun
 * @Description 班级及其附带信息dto
 * @Date 2019/4/16 15:39
 * @Version 1.0
 **/
public class ClassWithMajorCollegeDto extends Class {
    private String classMoniName;

    private String classTeaName;

    private String classMajorName;

    private String classCollegeName;

    public String getClassMoniName() {
        return classMoniName;
    }

    public void setClassMoniName(String classMoniName) {
        this.classMoniName = classMoniName;
    }

    public String getClassTeaName() {
        return classTeaName;
    }

    public void setClassTeaName(String classTeaName) {
        this.classTeaName = classTeaName;
    }

    public String getClassMajorName() {
        return classMajorName;
    }

    public void setClassMajorName(String classMajorName) {
        this.classMajorName = classMajorName;
    }

    public String getClassCollegeName() {
        return classCollegeName;
    }

    public void setClassCollegeName(String classCollegeName) {
        this.classCollegeName = classCollegeName;
    }

    @Override
    public String toString() {
        return super.toString()+"ClassWithMajorCollegeDto{" +
                "classMoniName='" + classMoniName + '\'' +
                ", classTeaName='" + classTeaName + '\'' +
                ", classMajorName='" + classMajorName + '\'' +
                ", classCollegeName='" + classCollegeName + '\'' +
                '}';
    }
}
