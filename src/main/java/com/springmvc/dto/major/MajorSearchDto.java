package com.springmvc.dto.major;

import com.springmvc.dto.other.SearchCondition;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @ClassName MajorSearchDto
 * @Description 封装专业信息查询搜索表单传来的JSON数据
 * @Date 2019/7/8 16:36
 * @Version 1.0
 **/
public class MajorSearchDto extends SearchCondition implements Serializable {
    private static final long serialVersionUID = -1266399725590136345L;

    //专业所在学院名称
    private String majorCollegeName;

    //专业教师负责人名称
    private String majorName;

    //专业性质，参考所属学院
    private String majorCollegeProperty;

    public String getMajorCollegeName() {
        return majorCollegeName;
    }

    public void setMajorCollegeName(String majorCollegeName) {
        this.majorCollegeName = majorCollegeName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorCollegeProperty() {
        return majorCollegeProperty;
    }

    public void setMajorCollegeProperty(String majorCollegeProperty) {
        this.majorCollegeProperty = majorCollegeProperty;
    }

    @Override
    public String toString() {
        return "MajorSearchDto{" +
                "majorCollegeName='" + majorCollegeName + '\'' +
                ", majorName='" + majorName + '\'' +
                ", majorCollegeProperty='" + majorCollegeProperty + '\'' +
                "} " + super.toString();
    }
}
