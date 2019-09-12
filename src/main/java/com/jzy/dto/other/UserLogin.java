package com.jzy.dto.other;

import com.jzy.entity.User;
import com.jzy.util.user.UserUtil;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName UserLogin
 * @description 封装用户登录错误次数的信息
 * @date 2019/9/7 9:19
 **/
public class UserLogin implements Serializable {
    private static final long serialVersionUID = 3347183837879977277L;

    //默认连续输错多少次，触发
    public static int DEFAULT_WRONG_TIMES=3;

    //默认连续输错达到阈值次数后，触发的下次可尝试登录的操作时间间隔，单位：分钟
    public static int DEFAULT_BASE_DELAY_TIME=1;

    //是否登录成功标志
    private boolean loginFlag;

    //用户是否冻结标志
    private boolean lockedFlag;

    //还剩多少时间可重新尝试登录
    private long timeRemaining;

    //单次登录失败的次数，上限默认为5次
    private int wrongTimes;

    //如果成功，设置当前在User表中查询到的user信息
    private User user;

    public boolean getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public boolean getLockedFlag() {
        return lockedFlag;
    }

    public void setLockedFlag(boolean lockedFlag) {
        this.lockedFlag = lockedFlag;
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public int getWrongTimes() {
        return wrongTimes;
    }

    public void setWrongTimes(int wrongTimes) {
        this.wrongTimes = wrongTimes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "loginFlag=" + loginFlag +
                ", lockedFlag=" + lockedFlag +
                ", timeRemaining=" + timeRemaining +
                ", wrongTimes=" + wrongTimes +
                ", user=" + user +
                '}';
    }

    public static String getUserLoginFailKey(String userName){
        return UserUtil.KEY_USER_LOGIN_FAIL+":"+userName;
    }
}
