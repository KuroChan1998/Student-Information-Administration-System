package com.springmvc.dto.classP;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @ClassName ClassSearchDto
 * @Description 封装班级信息查询搜索表单传来的JSON数据
 * @Date 2019/7/1 8:57
 * @Version 1.0
 **/
public class ClassSearchDto extends ClassWithGradeMajorCollegeDto implements Serializable {
    private static final long serialVersionUID = 6030239122329594290L;

    @Override
    public String toString() {
        return "ClassSearchDto{} " + super.toString();
    }
}
