package com.springmvc.entity;

/**
 * @ClassName Teacher
 * @Author JinZhiyun
 * @Description 老师实体类
 * @Date 2019/4/15 16:33
 * @Version 1.0
 **/
public class Teacher {
    private String teaId;

    private String teaMajorId;

    private String teaName;

    private String teaSex;

    private Integer teaAge;

    private String teaPhone;

    private String teaTitle;

    private String teaRemark;

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }

    public String getTeaMajorId() {
        return teaMajorId;
    }

    public void setTeaMajorId(String teaMajorId) {
        this.teaMajorId = teaMajorId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaSex() {
        return teaSex;
    }

    public void setTeaSex(String teaSex) {
        this.teaSex = teaSex;
    }

    public Integer getTeaAge() {
        return teaAge;
    }

    public void setTeaAge(Integer teaAge) {
        this.teaAge = teaAge;
    }

    public String getTeaPhone() {
        return teaPhone;
    }

    public void setTeaPhone(String teaPhone) {
        this.teaPhone = teaPhone;
    }

    public String getTeaTitle() {
        return teaTitle;
    }

    public void setTeaTitle(String teaTitle) {
        this.teaTitle = teaTitle;
    }

    public String getTeaRemark() {
        return teaRemark;
    }

    public void setTeaRemark(String teaRemark) {
        this.teaRemark = teaRemark;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teaId='" + teaId + '\'' +
                ", teaMajorId='" + teaMajorId + '\'' +
                ", teaName='" + teaName + '\'' +
                ", teaSex='" + teaSex + '\'' +
                ", teaAge=" + teaAge +
                ", teaPhone='" + teaPhone + '\'' +
                ", teaTitle='" + teaTitle + '\'' +
                ", teaRemark='" + teaRemark + '\'' +
                '}';
    }
}
