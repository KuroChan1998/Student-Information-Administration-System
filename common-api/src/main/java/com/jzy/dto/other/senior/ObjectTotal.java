package com.jzy.dto.other.senior;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @ClassName ObjectTotal
 * @Description 封装人数的基类
 * @Date 2019/7/15 11:09
 * @Version 1.0
 **/
public class ObjectTotal implements Serializable,Value {
    private static final long serialVersionUID = 7165493447907360695L;
    
    //当前条件下该的学生人数
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "ObjectTotal{" +
                "total=" + total +
                '}';
    }
}
