package com.springmvc.dto.student;

import com.springmvc.entity.Student;

import java.io.Serializable;

/**
 * @ClassName StudentWithGradeClassMajorCollegeDto
 * @Author JinZhiyun
 * @Description 学生及其附带信息dto
 * @Date 2019/4/15 18:33
 * @Version 1.0
 **/
public class StudentWithGradeClassMajorCollegeDto extends Student implements Serializable {
    private static final long serialVersionUID = -556836096234631842L;

    //学生生日Date类型的字符串形式
    private String stuBirthdayToString;

    //学生的年龄，计算得到
    private Integer stuAge;

    //学生年级名称
    private String stuGradeName;

    //学生班级名称
    private String stuClassName;

    //学生专业名称
    private String stuMajorName;

    //学生学院名称
    private String stuCollegeName;

    public String getStuBirthdayToString() {
        return stuBirthdayToString;
    }

    public void setStuBirthdayToString(String stuBirthdayToString) {
        this.stuBirthdayToString = stuBirthdayToString;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuGradeName() {
        return stuGradeName;
    }

    public void setStuGradeName(String stuGradeName) {
        this.stuGradeName = stuGradeName;
    }

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
        return "StudentWithGradeClassMajorCollegeDto{" +
                "stuBirthdayToString='" + stuBirthdayToString + '\'' +
                ", stuAge=" + stuAge +
                ", stuGradeName='" + stuGradeName + '\'' +
                ", stuClassName='" + stuClassName + '\'' +
                ", stuMajorName='" + stuMajorName + '\'' +
                ", stuCollegeName='" + stuCollegeName + '\'' +
                "} " + super.toString();
    }
}
