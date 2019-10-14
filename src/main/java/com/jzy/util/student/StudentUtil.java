package com.jzy.util.student;

import com.jzy.dto.student.StudentWithGradeClassMajorCollegeDto;
import com.jzy.entity.Student;
import com.jzy.util.MyDatabaseUtil;
import com.jzy.util.clazz.ClassUtil;
import com.jzy.util.college.CollegeUtil;
import com.jzy.util.grade.GradeUtil;
import com.jzy.util.major.MajorUtil;
import com.jzy.util.other.MySimpleUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName StudentUtil
 * @description 验证服务端接收到的学生dto类型是否合法，与数据库保持一致（必须），与前端输入要求保持一致
 * @date 2019/9/10 14:54
 **/
public class StudentUtil extends MyDatabaseUtil implements StudentModel, StudentRedis {
    private static final int STUDENT_NUM_LENGTH = 12;

    private static final int STUDENT_NAME_MAX_LENGTH = 50;

    private static final List<String> STUDENT_SEXES = Arrays.asList("男", "女");

    public static final List<String> STUDENT_DEGREES = Arrays.asList("本科", "博士", "硕士");

    private static final int STUDENT_PHONE_MAX_LENGTH = 11;

    private static final int STUDENT_REMARK_MAX_LENGTH = 500;

    private StudentUtil() {
    }

    /**
     * 保证Student的每个属性是否与数据库一直，符合规范
     **/
    public static boolean isValidStuId(String stuId) {
        return isValidUUID(stuId);
    }

    public static boolean isValidStuClass(String stuClass) {
        return isValidUUID(stuClass);
    }

    public static boolean isValidStuNum(String stuNum) {
        return stuNum != null && MySimpleUtil.isNumbersWithCertainLength(stuNum, STUDENT_NUM_LENGTH) && stuNum.charAt(0) == '5';
    }

    public static boolean isValidStuName(String stuName) {
        return !StringUtils.isEmpty(stuName) && stuName.length() <= STUDENT_NAME_MAX_LENGTH;
    }

    public static boolean isValidStuSex(String stuSex) {
        return STUDENT_SEXES.contains(stuSex);
    }

    public static boolean isValidStuBirthDay(Date stuBirthDay) {
        return true;
    }

    public static boolean isValidStuGrade(Integer stuGrade) {
        return isNotNullAndPositive(stuGrade);
    }

    public static boolean isValidStuDegree(String stuDegree) {
        return STUDENT_DEGREES.contains(stuDegree);
    }

    public static boolean isValidStuPhone(String stuPhone) {
        return stuPhone == null || stuPhone.length() <= STUDENT_PHONE_MAX_LENGTH;
    }

    public static boolean isValidStuRemark(String stuRemark) {
        return stuRemark == null || stuRemark.length() <= STUDENT_REMARK_MAX_LENGTH;
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 学生student对象的全部字段是否都合法
     * @date 16:06 2019/9/10
     * @Param [student]
     **/
    public static boolean isValidStudentAllInfo(Student student) {
        return !isNull(student) && isValidStuId(student.getStuId()) && isValidStuClass(student.getStuClass())
                && isValidStuNum(student.getStuNum()) && isValidStuName(student.getStuName()) && isValidStuSex(student.getStuSex())
                && isValidStuBirthDay(student.getStuBirthday()) && isValidStuGrade(student.getStuGrade()) && isValidStuDegree(student.getStuDegree())
                && isValidStuPhone(student.getStuPhone()) && isValidStuRemark(student.getStuRemark());
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 判断修改学生信息时服务端接收到的StudentWithGradeClassMajorCollegeDto参数是否合法
     * @date 13:11 2019/9/11
     * @Param [stuWGCMC]
     **/
    public static boolean isValidStudentUpdateInfo(StudentWithGradeClassMajorCollegeDto stuWGCMC) {
        return !isNull(stuWGCMC) && GradeUtil.isValidGradeName(stuWGCMC.getStuGradeName()) && ClassUtil.isValidClassName(stuWGCMC.getStuClassName())
                && MajorUtil.isValidMajorName(stuWGCMC.getStuMajorName()) && CollegeUtil.isValidCollegeName(stuWGCMC.getStuCollegeName())
                && StudentUtil.isValidStuNum(stuWGCMC.getStuNum()) && StudentUtil.isValidStuName(stuWGCMC.getStuName()) && StudentUtil.isValidStuSex(stuWGCMC.getStuSex())
                && StudentUtil.isValidStuDegree(stuWGCMC.getStuDegree()) && StudentUtil.isValidStuPhone(stuWGCMC.getStuPhone()) && StudentUtil.isValidStuRemark(stuWGCMC.getStuRemark());
    }

    /**
     * @author JinZhiyun
     * @description 判断添加学生信息时服务端接收到的StudentWithGradeClassMajorCollegeDto参数是否合法
     * @date 13:44 2019/9/11
     * @Param [stuWGCMC]
     * @return boolean
     **/
    public static boolean isValidStudentInsertInfo(StudentWithGradeClassMajorCollegeDto stuWGCMC) {
        return isValidStudentUpdateInfo(stuWGCMC);
    }

}
