package com.springmvc.dto;

/**
 * @ClassName StudentInfoSearchDto
 * @Author JinZhiyun
 * @Description 封装学生信息查询搜索表单传来的JSON数据
 * @Date 2019/4/16 10:44
 * @Version 1.0
 **/
public class StudentInfoSearchDto {
    private String stuId;

    private String stuName;

    private String stuGrade;

    private String stuCollege;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(String stuGrade) {
        this.stuGrade = stuGrade;
    }

    public String getStuCollege() {
        return stuCollege;
    }

    public void setStuCollege(String stuCollege) {
        this.stuCollege = stuCollege;
    }

    @Override
    public String toString() {
        return "StudentInfoSearchDto{" +
                "stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuGrade='" + stuGrade + '\'' +
                ", stuCollege='" + stuCollege + '\'' +
                '}';
    }
}
