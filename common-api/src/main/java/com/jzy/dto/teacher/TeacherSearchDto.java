package com.jzy.dto.teacher;

import com.jzy.dto.other.SearchCondition;

import java.io.Serializable;

/**
 * @ClassName TeacherSearchDto
 * @Author JinZhiyun
 * @Description 封装教师信息查询搜索表单传来的JSON数据
 * @Date 2019/4/16 14:16
 * @Version 1.0
 **/
public class TeacherSearchDto extends SearchCondition implements Serializable {
    private static final long serialVersionUID = -7530063230476406727L;

    //教师学号，唯一
    private String teaNum;

    //教师姓名
    private String teaName;

    //教师性别
    private String teaSex;

    //教师电话
    private String teaPhone;

    //教师职称名称
    private String teaTitleName;

    //教师专业名称
    private String teaMajorName;

    //教师学院名称
    private String teaCollegeName;

    public String getTeaNum() {
        return teaNum;
    }

    public void setTeaNum(String teaNum) {
        this.teaNum = teaNum;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaSex() {
        return teaSex;
    }

    public void setTeaSex(String teaSex) {
        this.teaSex = teaSex;
    }

    public String getTeaPhone() {
        return teaPhone;
    }

    public void setTeaPhone(String teaPhone) {
        this.teaPhone = teaPhone;
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
        return "TeacherSearchDto{" +
                "teaNum='" + teaNum + '\'' +
                ", teaName='" + teaName + '\'' +
                ", teaSex='" + teaSex + '\'' +
                ", teaPhone='" + teaPhone + '\'' +
                ", teaTitleName='" + teaTitleName + '\'' +
                ", teaMajorName='" + teaMajorName + '\'' +
                ", teaCollegeName='" + teaCollegeName + '\'' +
                "} " + super.toString();
    }
}
