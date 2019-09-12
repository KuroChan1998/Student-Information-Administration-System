package com.jzy.util.college;

import com.jzy.dto.college.CollegeDto;
import com.jzy.entity.College;
import com.jzy.util.MyDatabaseUtil;
import com.jzy.util.teacher.TeacherUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName CollegeUtil
 * @description 验证服务端接收到的学院dto类型是否合法，与数据库保持一致（必须），与前端输入要求保持一致
 * @date 2019/9/10 16:27
 **/
public class CollegeUtil extends MyDatabaseUtil implements CollegeModel, CollegeRedis {
    private static final int COLLEGE_NAME_MAX_LENGTH = 50;

    private static final List<String> COLLEGE_PROPERTIES = Arrays.asList("工科", "理科", "文科");

    private static final int COLLEGE_REMARK_MAX_LENGTH = 500;

    private CollegeUtil() {
    }


    /**
     * 保证College的每个属性是否与数据库一直，符合规范
     **/
    public static boolean isValidCollegeId(Integer collegeId) {
        return isNotNullAndPositive(collegeId);
    }

    public static boolean isValidCollegeName(String collegeName) {
        return !StringUtils.isEmpty(collegeName) && collegeName.length() <= COLLEGE_NAME_MAX_LENGTH;
    }

    public static boolean isValidCollegeProperty(String collegeProperty) {
        return StringUtils.isEmpty(collegeProperty) || COLLEGE_PROPERTIES.contains(collegeProperty);
    }

    public static boolean isValidCollegeTeaNum(String teaNum) {
        return StringUtils.isEmpty(teaNum) || TeacherUtil.isValidTeaNum(teaNum);
    }

    public static boolean isValidCollegeRemark(String collegeRemark) {
        return collegeRemark == null || collegeRemark.length() <= COLLEGE_REMARK_MAX_LENGTH;
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 学院college对象的全部字段是否都合法
     * @date 10:10 2019/9/11
     * @Param [college]
     **/
    public static boolean isValidCollegeAllInfo(College college) {
        return !isNull(college) && isValidCollegeId(college.getCollegeId()) && isValidCollegeName(college.getCollegeName())
                && isValidCollegeTeaNum(college.getCollegeTeaNum()) && isValidCollegeProperty(college.getCollegeProperty())
                && isValidCollegeRemark(college.getCollegeRemark());
    }

    /**
     * @author JinZhiyun
     * @description 判断修改学院信息时服务端接收到的MajorWithCollegeDto参数是否合法
     * @date 22:43 2019/9/11
     * @Param [collegeDto]
     * @return boolean
     **/
    public static boolean isValidCollegeUpdateInfo(CollegeDto collegeDto) {
        return !isNull(collegeDto) && CollegeUtil.isValidCollegeName(collegeDto.getCollegeName())
                && CollegeUtil.isValidCollegeProperty(collegeDto.getCollegeProperty()) && CollegeUtil.isValidCollegeTeaNum(collegeDto.getCollegeTeaNum())
                && CollegeUtil.isValidCollegeRemark(collegeDto.getCollegeRemark());
    }

    /**
     * @author JinZhiyun
     * @description 判断插入学院信息时服务端接收到的MajorWithCollegeDto参数是否合法
     * @date 22:43 2019/9/11
     * @Param [collegeDto]
     * @return boolean
     **/
    public static boolean isValidCollegeInsertInfo(CollegeDto collegeDto) {
        return isValidCollegeUpdateInfo(collegeDto);
    }
}
