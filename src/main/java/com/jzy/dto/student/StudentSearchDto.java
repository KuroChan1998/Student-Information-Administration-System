package com.jzy.dto.student;

import com.jzy.dto.other.SearchCondition;

import java.io.Serializable;

/**
 * @ClassName StudentSearchDto
 * @Author JinZhiyun
 * @Description 封装学生信息查询搜索表单传来的JSON数据
 * @Date 2019/4/16 10:44
 * @Version 1.0
 **/
public class StudentSearchDto extends SearchCondition implements Serializable {
    private static final long serialVersionUID = -157550844675390599L;

    //学生学号，唯一
    private String stuNum;

    //学生姓名
    private String stuName;

    //学生性别
    private String stuSex;

    //学生学位
    private String stuDegree;

    //学生年级名称
    private String stuGradeName;

    //学生班级名称
    private String stuClassName;

    //学生专业名称
    private String stuMajorName;

    //学生学院名称
    private String stuCollegeName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuDegree() {
        return stuDegree;
    }

    public void setStuDegree(String stuDegree) {
        this.stuDegree = stuDegree;
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
        return "StudentSearchDto{" +
                "stuNum='" + stuNum + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuDegree='" + stuDegree + '\'' +
                ", stuGradeName='" + stuGradeName + '\'' +
                ", stuClassName='" + stuClassName + '\'' +
                ", stuMajorName='" + stuMajorName + '\'' +
                ", stuCollegeName='" + stuCollegeName + '\'' +
                "} " + super.toString();
    }
}
