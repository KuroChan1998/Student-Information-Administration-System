package com.jzy.util.teacher;

import com.jzy.dto.teacher.TeacherWithTitleMajorCollegeDto;
import com.jzy.entity.Teacher;
import com.jzy.util.MyDatabaseUtil;
import com.jzy.util.college.CollegeUtil;
import com.jzy.util.major.MajorUtil;
import com.jzy.util.other.MySimpleUtil;
import com.jzy.util.title.TitleUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName TeacherUtil
 * @description 验证服务端接收到的教师dto类型是否合法，与数据库保持一致（必须），与前端输入要求保持一致
 * @date 2019/9/10 16:27
 **/
public class TeacherUtil extends MyDatabaseUtil implements TeacherModel, TeacherRedis {
    private static final int TEACHER_NUM_LENGTH = 12;

    private static final int TEACHER_NAME_MAX_LENGTH = 50;

    private static final List<String> TEACHER_SEXES = Arrays.asList("男", "女");

    private static final int TEACHER_PHONE_MAX_LENGTH = 11;

    private static final int TEACHER_REMARK_MAX_LENGTH = 500;

    private TeacherUtil() {
    }

    /**
     * 保证Teacher的每个属性是否与数据库一直，符合规范
     **/
    public static boolean isValidTeaId(String teaId) {
        return isValidUUID(teaId);
    }

    public static boolean isValidTeaMajor(Integer teaMajor) {
        return isNotNullAndPositive(teaMajor);
    }

    public static boolean isValidTeaNum(String teaNum) {
        return teaNum != null && MySimpleUtil.isNumbersWithCertainLength(teaNum, TEACHER_NUM_LENGTH) && teaNum.charAt(0) == '1';
    }

    public static boolean isValidTeaName(String teaName) {
        return !StringUtils.isEmpty(teaName) && teaName.length() <= TEACHER_NAME_MAX_LENGTH;
    }

    public static boolean isValidTeaSex(String teaSex) {
        return TEACHER_SEXES.contains(teaSex);
    }

    public static boolean isValidTeaBirthDay(Date teaBirthDay) {
        return true;
    }

    public static boolean isValidTeaTitle(Integer teaTitle) {
        return isNotNullAndPositive(teaTitle);
    }

    public static boolean isValidTeaPhone(String teaPhone) {
        return teaPhone == null || teaPhone.length() <= TEACHER_PHONE_MAX_LENGTH;
    }

    public static boolean isValidTeaRemark(String teaRemark) {
        return teaRemark == null || teaRemark.length() <= TEACHER_REMARK_MAX_LENGTH;
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 教师teacher对象的全部字段是否都合法
     * @date 10:10 2019/9/11
     * @Param [teacher]
     **/
    public static boolean isValidTeacherAllInfo(Teacher teacher) {
        return !isNull(teacher) && isValidTeaId(teacher.getTeaId()) && isValidTeaMajor(teacher.getTeaMajor())
                && isValidTeaNum(teacher.getTeaNum()) && isValidTeaName(teacher.getTeaName()) && isValidTeaSex(teacher.getTeaSex())
                && isValidTeaBirthDay(teacher.getTeaBirthday()) && isValidTeaTitle(teacher.getTeaTitle())
                && isValidTeaPhone(teacher.getTeaPhone()) && isValidTeaRemark(teacher.getTeaRemark());
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 判断修改教师信息时服务端接收到的TeacherWithTitleMajorCollegeDto参数是否合法
     * @date 19:05 2019/9/11
     * @Param [teaWTMC]
     **/
    public static boolean isValidTeacherUpdateInfo(TeacherWithTitleMajorCollegeDto teaWTMC) {
        return !isNull(teaWTMC) && TitleUtil.isValidTitleName(teaWTMC.getTeaTitleName()) && MajorUtil.isValidMajorName(teaWTMC.getTeaMajorName())
                && CollegeUtil.isValidCollegeName(teaWTMC.getTeaCollegeName()) && TeacherUtil.isValidTeaNum(teaWTMC.getTeaNum())
                && TeacherUtil.isValidTeaName(teaWTMC.getTeaName()) && TeacherUtil.isValidTeaSex(teaWTMC.getTeaSex())
                && TeacherUtil.isValidTeaPhone(teaWTMC.getTeaPhone()) && TeacherUtil.isValidTeaRemark(teaWTMC.getTeaRemark());
    }

    /**
     * @author JinZhiyun
     * @description 判断添加教师信息时服务端接收到的TeacherWithTitleMajorCollegeDto参数是否合法
     * @date 21:46 2019/9/11
     * @Param [teaWTMC]
     * @return boolean
     **/
    public static boolean isValidTeacherInsertInfo(TeacherWithTitleMajorCollegeDto teaWTMC) {
        return isValidTeacherUpdateInfo(teaWTMC);
    }
}
