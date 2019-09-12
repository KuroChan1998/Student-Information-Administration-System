package com.jzy.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName TitleServiceLogger
 * @description //TODO 对职称业务的日志管理
 * @date 2019/9/11 16:35
 **/
@Aspect
@Component
public class TitleServiceLogger {
    private final static Logger logger = Logger.getLogger(TitleServiceLogger.class);
}
