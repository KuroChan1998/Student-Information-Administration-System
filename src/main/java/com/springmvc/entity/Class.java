package com.springmvc.entity;

/**
 * @ClassName Class
 * @Author JinZhiyun
 * @Description 班级实体类
 * @Date 2019/4/15 16:45
 * @Version 1.0
 **/
public class Class {
    private String classId;

    private String classMajorId;

    private String className;

    private Integer classStuNum;

    private String classMoniId;

    private String classTeaId;

    private String classRemark;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassMajorId() {
        return classMajorId;
    }

    public void setClassMajorId(String classMajorId) {
        this.classMajorId = classMajorId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassStuNum() {
        return classStuNum;
    }

    public void setClassStuNum(Integer classStuNum) {
        this.classStuNum = classStuNum;
    }

    public String getClassMoniId() {
        return classMoniId;
    }

    public void setClassMoniId(String classMoniId) {
        this.classMoniId = classMoniId;
    }

    public String getClassTeaId() {
        return classTeaId;
    }

    public void setClassTeaId(String classTeaId) {
        this.classTeaId = classTeaId;
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
                ", classMajorId='" + classMajorId + '\'' +
                ", className='" + className + '\'' +
                ", classStuNum=" + classStuNum +
                ", classMoniId='" + classMoniId + '\'' +
                ", classTeaId='" + classTeaId + '\'' +
                ", classRemark='" + classRemark + '\'' +
                '}';
    }
}
