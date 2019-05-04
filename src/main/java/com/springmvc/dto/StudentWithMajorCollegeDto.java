package com.springmvc.dto;

import com.springmvc.entity.Student;

/**
 * @ClassName StudentWithMajorCollegeDto
 * @Author JinZhiyun
 * @Description 学生及其附带信息dto
 * @Date 2019/4/15 18:33
 * @Version 1.0
 **/
public class StudentWithMajorCollegeDto extends Student {

    private String stuClassName;

    private String stuMajorName;

    private String stuCollegeName;

    public String getStuClassName() {
        return stuClassName;
    }

    public void setStuClassName(String stuClassName) {
        this.stuClassName = stuClassName;
    }

    public String getStuMajorName() {
        return stuMajorName;
    }

    public void setStuMajorName(String stuMajorName) {
        this.stuMajorName = stuMajorName;
    }

    public String getStuCollegeName() {
        return stuCollegeName;
    }

    public void setStuCollegeName(String stuCollegeName) {
        this.stuCollegeName = stuCollegeName;
    }

    @Override
    public String toString() {
        return super.toString()+"StudentWithMajorCollegeDto{" +
                "stuClassName='" + stuClassName + '\'' +
                ", stuMajorName='" + stuMajorName + '\'' +
                ", stuCollegeName='" + stuCollegeName + '\'' +
                '}';
    }
}
