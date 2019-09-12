package com.jzy.dto.major;

import com.jzy.entity.Major;

import java.io.Serializable;

/**
 * @ClassName MajorWithCollegeDto
 * @Author JinZhiyun
 * @Description 专业及其附带信息dto
 * @Date 2019/4/17 20:59
 * @Version 1.0
 **/
public class MajorWithCollegeDto extends Major implements Serializable {
    private static final long serialVersionUID = -8311103270747737382L;

    //专业所在学院名称
    private String majorCollegeName;

    //专业教师负责人名称
    private String majorTeaName;

    //专业性质，参考所属学院
    private String majorCollegeProperty;

    public String getMajorCollegeName() {
        return majorCollegeName;
    }

    public void setMajorCollegeName(String majorCollegeName) {
        this.majorCollegeName = majorCollegeName;
    }

    public String getMajorTeaName() {
        return majorTeaName;
    }

    public void setMajorTeaName(String majorTeaName) {
        this.majorTeaName = majorTeaName;
    }

    public String getMajorCollegeProperty() {
        return majorCollegeProperty;
    }

    public void setMajorCollegeProperty(String majorCollegeProperty) {
        this.majorCollegeProperty = majorCollegeProperty;
    }

    @Override
    public String toString() {
        return "MajorWithCollegeDto{" +
                "majorCollegeName='" + majorCollegeName + '\'' +
                ", majorTeaName='" + majorTeaName + '\'' +
                ", majorCollegeProperty='" + majorCollegeProperty + '\'' +
                "} " + super.toString();
    }
}
