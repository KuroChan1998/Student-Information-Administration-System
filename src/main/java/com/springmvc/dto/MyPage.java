package com.springmvc.dto;

/**
 * @ClassName MyPage
 * @Author JinZhiyun
 * @Description 自定义分页类的封装
 * @Date 2019/4/16 11:22
 * @Version 1.0
 **/
public class MyPage {
    //当前页号
    private Integer pageNum;

    //当前页记录数量
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public MyPage(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public MyPage(){

    }
}
