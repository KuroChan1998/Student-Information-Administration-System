package com.jzy.entity;

import java.io.Serializable;

/**
 * @ClassName Major
 * @Author JinZhiyun
 * @Description 专业实体类
 * @Date 2019/4/15 16:40
 * @Version 1.0
 **/
public class Major implements Serializable {
    private static final long serialVersionUID = -4708169816587928752L;

    //专业表代理主键，自增
    private Integer majorId;

    //专业所属学院，外键，参照college.college_id
    private Integer majorCollege;

    //专业名，唯一
    private String majorName;

    //专业负责人工号
    private String majorTeaNum;

    //专业备注
    private String majorRemark;

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getMajorCollege() {
        return majorCollege;
    }

    public void setMajorCollege(Integer majorCollege) {
        this.majorCollege = majorCollege;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorTeaNum() {
        return majorTeaNum;
    }

    public void setMajorTeaNum(String majorTeaNum) {
        this.majorTeaNum = majorTeaNum;
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
                "majorId=" + majorId +
                ", majorCollege=" + majorCollege +
                ", majorName='" + majorName + '\'' +
                ", majorTeaNum='" + majorTeaNum + '\'' +
                ", majorRemark='" + majorRemark + '\'' +
                '}';
    }
}
