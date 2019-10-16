package com.jzy.log;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName GradeServiceLogger
 * @description //TODO 对年级业务的日志管理
 * @date 2019/9/11 16:36
 **/
@Aspect
@Component
public class GradeServiceLogger {
    private final static Logger logger = Logger.getLogger(GradeServiceLogger.class);

}
