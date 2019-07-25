package com.springmvc.dto.classP;

import com.springmvc.dto.other.SearchCondition;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @ClassName ClassSearchDto
 * @Description 封装班级信息查询搜索表单传来的JSON数据
 * @Date 2019/7/1 8:57
 * @Version 1.0
 **/
public class ClassSearchDto extends SearchCondition implements Serializable {
    private static final long serialVersionUID = 6030239122329594290L;

    //班级所属年级名称
    private String classGradeName;

    //班级名字
    private String className;

    //班级所属专业名字
    private String classMajorName;

    //班级所属学院名字
    private String classCollegeName;

    public String getClassGradeName() {
        return classGradeName;
    }

    public void setClassGradeName(String classGradeName) {
        this.classGradeName = classGradeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassMajorName() {
        return classMajorName;
    }

    public void setClassMajorName(String classMajorName) {
        this.classMajorName = classMajorName;
    }

    public String getClassCollegeName() {
        return classCollegeName;
    }

    public void setClassCollegeName(String classCollegeName) {
        this.classCollegeName = classCollegeName;
    }

    @Override
    public String toString() {
        return "ClassSearchDto{" +
                "classGradeName='" + classGradeName + '\'' +
                ", className='" + className + '\'' +
                ", classMajorName='" + classMajorName + '\'' +
                ", classCollegeName='" + classCollegeName + '\'' +
                "} " + super.toString();
    }
}
