package com.jzy.dto.other.senior;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @ClassName PersonPercentBySex
 * @Description 封装男女比
 * @Date 2019/7/14 16:43
 * @Version 1.0
 **/
public class PersonPercentBySex implements Serializable {
    private static final long serialVersionUID = -4620138449527273405L;

    //男性人数
    private Integer male;

    //女性人数
    private Integer female;

    //总人数
    private Integer total;

    public Integer getMale() {
        return male;
    }

    public void setMale(Integer male) {
        this.male = male;
    }

    public Integer getFemale() {
        return female;
    }

    public void setFemale(Integer female) {
        this.female = female;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setTotal() {
        this.total = male+female;
    }

    @Override
    public String toString() {
        return "PersonPercentBySex{" +
                "male=" + male +
                ", female=" + female +
                ", total=" + total +
                '}';
    }
}
