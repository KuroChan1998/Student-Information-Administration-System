package com.jzy.dto.clazz;

import com.jzy.entity.Class;

import java.io.Serializable;

/**
 * @ClassName ClassWithGradeMajorCollegeDto
 * @Author JinZhiyun
 * @Description 班级及其附带信息dto
 * @Date 2019/4/16 15:39
 * @Version 1.0
 **/
public class ClassWithGradeMajorCollegeDto extends Class implements Serializable {
    private static final long serialVersionUID = 4383459007795026659L;

    //班级所属年级名称
    private String classGradeName;

    //班级班长名字
    private String classStuName;

    //班主任名字
    private String classTeaName;

    //班级所属专业名字
    private String classMajorName;

    //班级所属学院名字
    private String classCollegeName;

    public String getClassGradeName() {
        return classGradeName;
    }

    public void setClassGradeName(String classGradeName) {
        this.classGradeName = classGradeName;
    }

    public String getClassStuName() {
        return classStuName;
    }

    public void setClassStuName(String classStuName) {
        this.classStuName = classStuName;
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
        return "ClassWithGradeMajorCollegeDto{" +
                "classGradeName='" + classGradeName + '\'' +
                ", classStuName='" + classStuName + '\'' +
                ", classTeaName='" + classTeaName + '\'' +
                ", classMajorName='" + classMajorName + '\'' +
                ", classCollegeName='" + classCollegeName + '\'' +
                "} " + super.toString();
    }
}
