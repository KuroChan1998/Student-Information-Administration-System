package com.springmvc.dto.other.senior;

/**
 * @author JinZhiyun
 * @ClassName ObjectTotalGroupByCommonName
 * @Description 封装类别对应名称及其下的人数传输对象
 * @Date 2019/7/16 8:42
 * @Version 1.0
 **/
public class ObjectTotalGroupByCommonName extends ObjectTotal {
    //该变量保存类别名称，对应该名称下的total人数
    private String commonName;

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    @Override
    public String toString() {
        return "ObjectTotalGroupByCommonName{" +
                "commonName='" + commonName + '\'' +
                "} " + super.toString();
    }
}
