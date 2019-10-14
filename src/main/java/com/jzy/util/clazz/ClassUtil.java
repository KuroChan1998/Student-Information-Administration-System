package com.jzy.util.clazz;

import com.jzy.dto.clazz.ClassWithGradeMajorCollegeDto;
import com.jzy.entity.Class;
import com.jzy.util.MyDatabaseUtil;
import com.jzy.util.college.CollegeUtil;
import com.jzy.util.grade.GradeUtil;
import com.jzy.util.major.MajorUtil;
import com.jzy.util.student.StudentUtil;
import com.jzy.util.teacher.TeacherUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName ClassUtil
 * @description 验证服务端接收到的班级dto类型是否合法，与数据库保持一致（必须），与前端输入要求保持一致
 * @date 2019/9/10 16:27
 **/
public class ClassUtil extends MyDatabaseUtil implements ClassModel, ClassRedis {
    private static final int CLASS_NAME_MAX_LENGTH = 50;

    private static final int CLASS_REMARK_MAX_LENGTH = 500;

    private ClassUtil() {
    }

    /**
     * 保证Class的每个属性是否与数据库一直，符合规范
     **/
    public static boolean isValidClassId(String classId) {
        return isValidUUID(classId);
    }

    public static boolean isValidClassMajor(Integer classMajor) {
        return isNotNullAndPositive(classMajor);
    }

    public static boolean isValidClassName(String className) {
        return !StringUtils.isEmpty(className) && className.length() <= CLASS_NAME_MAX_LENGTH;
    }

    public static boolean isValidClassGrade(Integer classGrade) {
        return isNotNullAndPositive(classGrade);
    }

    public static boolean isValidClassStuNum(String stuNum) {
        return StringUtils.isEmpty(stuNum) || StudentUtil.isValidStuNum(stuNum);
    }

    public static boolean isValidClassTeaNum(String teaNum) {
        return StringUtils.isEmpty(teaNum) || TeacherUtil.isValidTeaNum(teaNum);
    }

    public static boolean isValidClassRemark(String classRemark) {
        return classRemark == null || classRemark.length() <= CLASS_REMARK_MAX_LENGTH;
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 班级class对象的全部字段是否都合法
     * @date 10:10 2019/9/11
     * @Param [clazz]
     **/
    public static boolean isValidClassAllInfo(Class clazz) {
        return !isNull(clazz) && isValidClassId(clazz.getClassId()) && isValidClassMajor(clazz.getClassMajor())
                && isValidClassName(clazz.getClassName()) && isValidClassGrade(clazz.getClassGrade())
                && isValidClassStuNum(clazz.getClassStuNum()) && isValidClassTeaNum(clazz.getClassTeaNum()) && isValidClassRemark(clazz.getClassRemark());
    }

    /**
     * @author JinZhiyun
     * @description 判断修改班级信息时服务端接收到的ClassWithGradeMajorCollegeDto参数是否合法
     * @date 22:00 2019/9/11
     * @Param [classWGMC]
     * @return boolean
     **/
    public static boolean isValidClassUpdateInfo(ClassWithGradeMajorCollegeDto classWGMC) {
        return !isNull(classWGMC) && GradeUtil.isValidGradeName(classWGMC.getClassGradeName()) && MajorUtil.isValidMajorName(classWGMC.getClassMajorName())
                && CollegeUtil.isValidCollegeName(classWGMC.getClassCollegeName()) && ClassUtil.isValidClassName(classWGMC.getClassName())
                && ClassUtil.isValidClassStuNum(classWGMC.getClassStuNum()) && ClassUtil.isValidClassTeaNum(classWGMC.getClassTeaNum())
                && ClassUtil.isValidClassRemark(classWGMC.getClassRemark());
    }

    /**
     * @author JinZhiyun
     * @description 判断插入班级信息时服务端接收到的ClassWithGradeMajorCollegeDto参数是否合法
     * @date 22:08 2019/9/11
     * @Param [classWGMC]
     * @return boolean
     **/
    public static boolean isValidClassInsertInfo(ClassWithGradeMajorCollegeDto classWGMC) {
        return isValidClassUpdateInfo(classWGMC);
    }
}
