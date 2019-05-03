package com.springmvc.dto;

/**
 * @ClassName ResultMap
 * @Author JinZhiyun
 * @Description layui数据表格返回数据处理类
 * @Date 2019/4/15 16:53
 * @Version 1.0
 **/
public class ResultMap<T> {
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