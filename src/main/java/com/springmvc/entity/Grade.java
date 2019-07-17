package com.springmvc.entity;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @ClassName Grade
 * @Description 年级实体类
 * @Date 2019/6/14 13:07
 * @Version 1.0
 **/
public class Grade implements Serializable {
    private static final long serialVersionUID = -7352584735630060484L;

    //年级表代理主键，自增
    private Integer gradeId;

    //年级名
    private String gradeName;

    //年级学生负责人学号
    private String gradeStuNum;

    //年级教师负责人工号
    private String gradeTeaNum;

    //年级备注
    private String gradeRemark;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getGradeStuNum() {
        return gradeStuNum;
    }

    public void setGradeStuNum(String gradeStuNum) {
        this.gradeStuNum = gradeStuNum;
    }

    public String getGradeTeaNum() {
        return gradeTeaNum;
    }

    public void setGradeTeaNum(String gradeTeaNum) {
        this.gradeTeaNum = gradeTeaNum;
    }

    public String getGradeRemark() {
        return gradeRemark;
    }

    public void setGradeRemark(String gradeRemark) {
        this.gradeRemark = gradeRemark;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                ", gradeStuNum='" + gradeStuNum + '\'' +
                ", gradeTeaNum='" + gradeTeaNum + '\'' +
                ", gradeRemark='" + gradeRemark + '\'' +
                '}';
    }
}
