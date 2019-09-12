package com.jzy.dto.college;

import com.jzy.dto.other.SearchCondition;

import java.io.Serializable;

/**
 * @author JinZhiyun
 * @ClassName CollegeSearchDto
 * @Description 封装班级信息查询搜索表单传来的JSON数据
 * @Date 2019/7/25 13:19
 * @Version 1.0
 **/
public class CollegeSearchDto extends SearchCondition implements Serializable {
    private static final long serialVersionUID = -2689114832745781479L;

    //学院名，唯一
    private String collegeName;

    //学院性质
    private String collegeProperty;

    @Override
    public String toString() {
        return "CollegeSearchDto{" +
                "collegeName='" + collegeName + '\'' +
                ", collegeProperty='" + collegeProperty + '\'' +
                "} " + super.toString();
    }
}
