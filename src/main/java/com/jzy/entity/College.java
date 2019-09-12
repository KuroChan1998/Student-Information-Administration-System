package com.jzy.entity;

import java.io.Serializable;

/**
 * @ClassName College
 * @Author JinZhiyun
 * @Description 学院实体类
 * @Date 2019/4/15 16:36
 * @Version 1.0
 **/
public class College implements Serializable {
    private static final long serialVersionUID = -8146386727582977319L;

    //学院表代理主键
    private Integer collegeId;

    //学院名，唯一
    private String collegeName;

    //学院性质
    private String collegeProperty;

    //学院负责人工号
    private String collegeTeaNum;

    //学院备注
    private String collegeRemark;

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeProperty() {
        return collegeProperty;
    }

    public void setCollegeProperty(String collegeProperty) {
        this.collegeProperty = collegeProperty;
    }

    public String getCollegeTeaNum() {
        return collegeTeaNum;
    }

    public void setCollegeTeaNum(String collegeTeaNum) {
        this.collegeTeaNum = collegeTeaNum;
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
                "collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                ", collegeProperty='" + collegeProperty + '\'' +
                ", collegeTeaNum='" + collegeTeaNum + '\'' +
                ", collegeRemark='" + collegeRemark + '\'' +
                '}';
    }
}
