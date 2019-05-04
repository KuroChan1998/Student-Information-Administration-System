package com.springmvc.entity;

/**
 * @ClassName Major
 * @Author JinZhiyun
 * @Description 专业实体类
 * @Date 2019/4/15 16:40
 * @Version 1.0
 **/
public class Major {
    private String majorId;

    private String majorCollegeId;

    private String majorName;

    private Integer majorStuNum;

    private Integer majorClassNum;

    private String majorTeaId;

    private String majorRemark;

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorCollegeId() {
        return majorCollegeId;
    }

    public void setMajorCollegeId(String majorCollegeId) {
        this.majorCollegeId = majorCollegeId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getMajorStuNum() {
        return majorStuNum;
    }

    public void setMajorStuNum(Integer majorStuNum) {
        this.majorStuNum = majorStuNum;
    }

    public Integer getMajorClassNum() {
        return majorClassNum;
    }

    public void setMajorClassNum(Integer majorClassNum) {
        this.majorClassNum = majorClassNum;
    }

    public String getMajorTeaId() {
        return majorTeaId;
    }

    public void setMajorTeaId(String majorTeaId) {
        this.majorTeaId = majorTeaId;
    }

    public String getMajorRemark() {
        return majorRemark;
    }

    public void setMajorRemark(String majorRemark) {
        this.majorRemark = majorRemark;
    }

    @Override
    public String toString() {
        return "Major{" +
                "majorId='" + majorId + '\'' +
                ", majorCollegeId='" + majorCollegeId + '\'' +
                ", majorName='" + majorName + '\'' +
                ", majorStuNum=" + majorStuNum +
                ", majorClassNum=" + majorClassNum +
                ", majorTeaId='" + majorTeaId + '\'' +
                ", majorRemark='" + majorRemark + '\'' +
                '}';
    }
}
