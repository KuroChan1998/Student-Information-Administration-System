package com.jzy.dto.other.senior;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @ClassName StudentPercentBySex
 * @Description 封装学生男女比
 * @Date 2019/7/14 16:42
 * @Version 1.0
 **/
public class StudentPercentBySex extends PersonPercentBySex implements Serializable {
    private static final long serialVersionUID = -7509249119215167087L;

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
        return "StudentPercentBySex{" +
                "stuClassName='" + stuClassName + '\'' +
                ", stuMajorName='" + stuMajorName + '\'' +
                ", stuCollegeName='" + stuCollegeName + '\'' +
                "} " + super.toString();
    }
}
