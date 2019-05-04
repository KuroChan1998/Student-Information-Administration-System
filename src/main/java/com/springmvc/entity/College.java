package com.springmvc.entity;

/**
 * @ClassName College
 * @Author JinZhiyun
 * @Description 学院实体类
 * @Date 2019/4/15 16:36
 * @Version 1.0
 **/
public class College {
    private String collegeId;

    private String collegeName;

    private Integer collegeStuNum;

    private Integer collegeMajorNum;

    private String collegeProperty;

    private String collegeTeaId;

    private String collegeRemark;

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Integer getCollegeStuNum() {
        return collegeStuNum;
    }

    public void setCollegeStuNum(Integer collegeStuNum) {
        this.collegeStuNum = collegeStuNum;
    }

    public Integer getCollegeMajorNum() {
        return collegeMajorNum;
    }

    public void setCollegeMajorNum(Integer collegeMajorNum) {
        this.collegeMajorNum = collegeMajorNum;
    }

    public String getCollegeProperty() {
        return collegeProperty;
    }

    public void setCollegeProperty(String collegeProperty) {
        this.collegeProperty = collegeProperty;
    }

    public String getCollegeTeaId() {
        return collegeTeaId;
    }

    public void setCollegeTeaId(String collegeTeaId) {
        this.collegeTeaId = collegeTeaId;
    }

    public String getCollegeRemark() {
        return collegeRemark;
    }

    public void setCollegeRemark(String collegeRemark) {
        this.collegeRemark = collegeRemark;
    }

    @Override
    public String toString() {
        return "College{" +
                "collegeId='" + collegeId + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", collegeStuNum=" + collegeStuNum +
                ", collegeMajorNum=" + collegeMajorNum +
                ", collegeProperty='" + collegeProperty + '\'' +
                ", collegeTeaId='" + collegeTeaId + '\'' +
                ", collegeRemark='" + collegeRemark + '\'' +
                '}';
    }
}
