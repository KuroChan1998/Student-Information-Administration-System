package com.springmvc.dto;

/**
 * @ClassName TeacherInfoSearchDto
 * @Author JinZhiyun
 * @Description 封装教师信息查询搜索表单传来的JSON数据
 * @Date 2019/4/16 14:16
 * @Version 1.0
 **/
public class TeacherInfoSearchDto {
    private String teaId;

    private String teaName;

    private String teaTitle;

    private String teaCollege;

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaTitle() {
        return teaTitle;
    }

    public void setTeaTitle(String teaTitle) {
        this.teaTitle = teaTitle;
    }

    public String getTeaCollege() {
        return teaCollege;
    }

    public void setTeaCollege(String teaCollege) {
        this.teaCollege = teaCollege;
    }

    @Override
    public String toString() {
        return "TeacherInfoSearchDto{" +
                "teaId='" + teaId + '\'' +
                ", teaName='" + teaName + '\'' +
                ", teaTitle='" + teaTitle + '\'' +
                ", teaCollege='" + teaCollege + '\'' +
                '}';
    }
}
