package com.springmvc.dto.other;

/**
 * @author JinZhiyun
 * @ClassName SearchCondition
 * @Description 查询的的条件
 * @Date 2019/7/25 9:12
 * @Version 1.0
 **/
public class SearchCondition {
    //第一条件：类别
    private String condition1;

    //第二条件：升降序
    private String condition2;

    public String getCondition1() {
        return condition1;
    }

    public void setCondition1(String condition1) {
        this.condition1 = condition1;
    }

    public String getCondition2() {
        return condition2;
    }

    public void setCondition2(String condition2) {
        this.condition2 = condition2;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "condition1='" + condition1 + '\'' +
                ", condition2='" + condition2 + '\'' +
                '}';
    }
}
