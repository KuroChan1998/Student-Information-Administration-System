package com.springmvc.dto.student;

import java.io.Serializable;

/**
 * @ClassName StudentSearchDto
 * @Author JinZhiyun
 * @Description 封装学生信息查询搜索表单传来的JSON数据
 * @Date 2019/4/16 10:44
 * @Version 1.0
 **/
public class StudentSearchDto extends StudentWithGradeClassMajorCollegeDto implements Serializable {
    private static final long serialVersionUID = -157550844675390599L;

    @Override
    public String toString() {
        return "StudentSearchDto{} " + super.toString();
    }
}
