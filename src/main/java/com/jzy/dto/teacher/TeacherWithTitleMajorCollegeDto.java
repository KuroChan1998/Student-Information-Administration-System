package com.jzy.dto.teacher;

import com.jzy.entity.Teacher;

import java.io.Serializable;

/**
 * @ClassName TeacherWithTitleMajorCollegeDto
 * @Author JinZhiyun
 * @Description 教师及其附带信息dto
 * @Date 2019/4/16 13:05
 * @Version 1.0
 **/
public class TeacherWithTitleMajorCollegeDto extends Teacher implements Serializable {
    private static final long serialVersionUID = -2990123913943231497L;

    //教师生日Date类型的字符串形式
    private String teaBirthdayToString;

    //教师的年龄，计算得到
    private Integer teaAge;

    //教师职称名称
    private String teaTitleName;

    //教师专业名称
    private String teaMajorName;

    //教师学院名称
    private String teaCollegeName;

    public String getTeaBirthdayToString() {
        return teaBirthdayToString;
    }

    public void setTeaBirthdayToString(String teaBirthdayToString) {
        this.teaBirthdayToString = teaBirthdayToString;
    }

    public Integer getTeaAge() {
        return teaAge;
    }

    public void setTeaAge(Integer teaAge) {
        this.teaAge = teaAge;
    }

    public String getTeaTitleName() {
        return teaTitleName;
    }

    public void setTeaTitleName(String teaTitleName) {
        this.teaTitleName = teaTitleName;
    }

    public String getTeaMajorName() {
        return teaMajorName;
    }

    public void setTeaMajorName(String teaMajorName) {
        this.teaMajorName = teaMajorName;
    }

    public String getTeaCollegeName() {
        return teaCollegeName;
    }

    public void setTeaCollegeName(String teaCollegeName) {
        this.teaCollegeName = teaCollegeName;
    }

    @Override
    public String toString() {
        return "TeacherWithTitleMajorCollegeDto{" +
                "teaBirthdayToString='" + teaBirthdayToString + '\'' +
                ", teaAge=" + teaAge +
                ", teaTitleName='" + teaTitleName + '\'' +
                ", teaMajorName='" + teaMajorName + '\'' +
                ", teaCollegeName='" + teaCollegeName + '\'' +
                "} " + super.toString();
    }
}
