package com.springmvc.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Student
 * @Author JinZhiyun
 * @Description 学生实体类
 * @Date 2019/4/15 16:28
 * @Version 1.0
 **/
public class Student implements Serializable {
    private static final long serialVersionUID = 998349295174271972L;

    //学生表代理主键uuid
    private String stuId;

    //学生所属班级id，外键，参照class.class_id
    private String stuClass;

    //学生学号，唯一
    private String stuNum;

    //学生姓名
    private String stuName;

    //学生性别
    private String stuSex;

    //学生生日
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date stuBirthday;

    //学生所属年级id，外键，参照grade.grade_id
    private Integer stuGrade;

    //学生学位
    private String stuDegree;

    //学生手机
    private String stuPhone;

    //学生备注
    private String stuRemark;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public Date getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(Date stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public Integer getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(Integer stuGrade) {
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
                ", stuClass='" + stuClass + '\'' +
                ", stuNum='" + stuNum + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuBirthday=" + stuBirthday +
                ", stuGrade=" + stuGrade +
                ", stuDegree='" + stuDegree + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuRemark='" + stuRemark + '\'' +
                '}';
    }
}
