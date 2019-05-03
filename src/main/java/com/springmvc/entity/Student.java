package com.springmvc.entity;

/**
 * @ClassName Student
 * @Author JinZhiyun
 * @Description 学生实体类
 * @Date 2019/4/15 16:28
 * @Version 1.0
 **/
public class Student {
    private String stuId;

    private String stuName;

    private String stuClassId;

    private String stuSex;

    private Integer stuAge;

    private String stuGrade;

    private String stuDegree;

    private String stuPhone;

    private String stuRemark;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuClassId() {
        return stuClassId;
    }

    public void setStuClassId(String stuClassId) {
        this.stuClassId = stuClassId;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(String stuGrade) {
        this.stuGrade = stuGrade;
    }

    public String getStuDegree() {
        return stuDegree;
    }

    public void setStuDegree(String stuDegree) {
        this.stuDegree = stuDegree;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuRemark() {
        return stuRemark;
    }

    public void setStuRemark(String stuRemark) {
        this.stuRemark = stuRemark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuClassId='" + stuClassId + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuAge=" + stuAge +
                ", stuGrade='" + stuGrade + '\'' +
                ", stuDegree='" + stuDegree + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuRemark='" + stuRemark + '\'' +
                '}';
    }
}
