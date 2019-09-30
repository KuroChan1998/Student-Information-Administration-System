package com.jzy.dto.other;

import java.io.Serializable;

/**
 * @ClassName MyPage
 * @Author JinZhiyun
 * @Description 自定义分页类的封装
 * @Date 2019/4/16 11:22
 * @Version 1.0
 **/
public class MyPage implements Serializable {
    private static final long serialVersionUID = 9072736654996915998L;

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

    public MyPage() {

    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 判断服务端接收到的mypage对象是否合法
     * @date 15:05 2019/9/10
     * @Param [myPage]
     **/
    public static boolean isValidMyPage(MyPage myPage) {
        return myPage != null && myPage.getPageNum() != null && myPage.getPageSize() != null
                && myPage.getPageNum() > 0 && myPage.getPageSize() > 0;
    }

    @Override
    public String toString() {
        return "MyPage{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
