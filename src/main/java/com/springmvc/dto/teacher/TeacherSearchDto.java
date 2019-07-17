package com.springmvc.dto.teacher;

import java.io.Serializable;

/**
 * @ClassName TeacherSearchDto
 * @Author JinZhiyun
 * @Description 封装教师信息查询搜索表单传来的JSON数据
 * @Date 2019/4/16 14:16
 * @Version 1.0
 **/
public class TeacherSearchDto extends TeacherWithTitleMajorCollegeDto implements Serializable {
    private static final long serialVersionUID = -7530063230476406727L;

    @Override
    public String toString() {
        return "TeacherSearchDto{} " + super.toString();
    }
}
