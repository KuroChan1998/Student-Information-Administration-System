package com.jzy.entity;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @ClassName Title
 * @Description 教师职称实体类
 * @Date 2019/6/14 13:10
 * @Version 1.0
 **/
public class Title implements Serializable {
    private static final long serialVersionUID = 4630108146940941566L;

    //职称表代理主键，自增
    private Integer titleId;

    //职称名，唯一
    private String titleName;

    //职称备注
    private String titleRemark;

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleRemark() {
        return titleRemark;
    }

    public void setTitleRemark(String titleRemark) {
        this.titleRemark = titleRemark;
    }

    @Override
    public String toString() {
        return "Title{" +
                "titleId=" + titleId +
                ", titleName='" + titleName + '\'' +
                ", titleRemark='" + titleRemark + '\'' +
                '}';
    }
}
