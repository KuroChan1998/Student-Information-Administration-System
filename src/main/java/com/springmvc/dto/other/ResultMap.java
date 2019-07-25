package com.springmvc.dto.other;

import java.io.Serializable;

/**
 * @ClassName ResultMap
 * @Author JinZhiyun
 * @Description 符合layui数据表格要求的返回JSON数据格式的传输对象
 * @Date 2019/4/15 16:53
 * @Version 1.0
 **/
public class ResultMap<T> implements Serializable {
    private String msg;
    private  T data;
    private  int code;
    private  int count;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ResultMap(int code,String msg, int count, T data) {
        this.msg = msg;
        this.data = data;
        this.code = code;
        this.count = count;
    }

    public ResultMap() {
    }
}