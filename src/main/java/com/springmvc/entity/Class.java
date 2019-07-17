package com.springmvc.entity;

import java.io.Serializable;

/**
 * @ClassName Class
 * @Author JinZhiyun
 * @Description 班级实体类
 * @Date 2019/4/15 16:45
 * @Version 1.0
 **/
public class Class implements Serializable {
    private static final long serialVersionUID = 5262671908815902069L;

    //班级表代理主键，uuid
    private String classId;

    //班级所属专业id，外键，参照major.major_id
    private Integer classMajor;

    //班级名称，唯一
    private String className;

    //班级所属年级id，外键，参照grade.grade_id
    private Integer classGrade;

    //班级班长学号
    private String classStuNum;

    //班级班主任工号
    private String classTeaNum;

    //班级备注
    private String classRemark;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer getClassMajor() {
        return classMajor;
    }

    public void setClassMajor(Integer classMajor) {
        this.classMajor = classMajor;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(Integer classGrade) {
        this.classGrade = classGrade;
    }

    public String getClassStuNum() {
        return classStuNum;
    }

    public void setClassStuNum(String classStuNum) {
        this.classStuNum = classStuNum;
    }

    public String getClassTeaNum() {
        return classTeaNum;
    }

    public void setClassTeaNum(String classTeaNum) {
        this.classTeaNum = classTeaNum;
    }

    public String getClassRemark() {
        return classRemark;
    }

    public void setClassRemark(String classRemark) {
        this.classRemark = classRemark;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId='" + classId + '\'' +
                ", classMajor=" + classMajor +
                ", className='" + className + '\'' +
                ", classGrade=" + classGrade +
                ", classStuNum='" + classStuNum + '\'' +
                ", classTeaNum='" + classTeaNum + '\'' +
                ", classRemark='" + classRemark + '\'' +
                '}';
    }
}
