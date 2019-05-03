package com.springmvc.dto;

import com.springmvc.entity.Teacher;

/**
 * @ClassName TeacherWithMajorCollegeDto
 * @Author JinZhiyun
 * @Description 教师及其附带信息dto
 * @Date 2019/4/16 13:05
 * @Version 1.0
 **/
public class TeacherWithMajorCollegeDto extends Teacher{

    private String teaMajorName;

    private String teaCollegeName;

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
        return super.toString()+"TeacherWithMajorCollegeDto{" +
                "teaMajorName='" + teaMajorName + '\'' +
                ", teaCollegeName='" + teaCollegeName + '\'' +
                '}';
    }
}
