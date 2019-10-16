package com.jzy.util.grade;

import com.jzy.entity.Grade;
import com.jzy.util.MyDatabaseUtil;
import com.jzy.util.student.StudentUtil;
import com.jzy.util.teacher.TeacherUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName GradeUtil
 * @description 验证服务端接收到的年级dto类型是否合法，与数据库保持一致（必须），与前端输入要求保持一致
 * @date 2019/9/10 16:27
 **/
public class GradeUtil extends MyDatabaseUtil implements GradeModel,GradeRedis {
    private static final int GRADE_NAME_MAX_LENGTH = 50;

    private static final int GRADE_REMARK_MAX_LENGTH = 500;

    private GradeUtil() {
    }

    /**
     * 保证Grade的每个属性是否与数据库一直，符合规范
     **/
    public static boolean isValidGradeId(Integer gradeId) {
        return isNotNullAndPositive(gradeId);
    }

    public static boolean isValidGradeName(String gradeName) {
        return !StringUtils.isEmpty(gradeName) && gradeName.length() <= GRADE_NAME_MAX_LENGTH;
    }

    public static boolean isValidGradeStuNum(String stuNum) {
        return StringUtils.isEmpty(stuNum) || StudentUtil.isValidStuNum(stuNum);
    }

    public static boolean isValidGradeTeaNum(String teaNum) {
        return StringUtils.isEmpty(teaNum) || TeacherUtil.isValidTeaNum(teaNum);
    }

    public static boolean isValidGradeRemark(String gradeRemark) {
        return gradeRemark == null || gradeRemark.length() <= GRADE_REMARK_MAX_LENGTH;
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 年级grade对象的全部字段是否都合法
     * @date 10:10 2019/9/11
     * @Param [grade]
     **/
    public static boolean isValidGradeAllInfo(Grade grade) {
        return !isNull(grade) && isValidGradeId(grade.getGradeId()) && isValidGradeName(grade.getGradeName())
                && isValidGradeTeaNum(grade.getGradeTeaNum()) && isValidGradeStuNum(grade.getGradeStuNum()) && isValidGradeRemark(grade.getGradeRemark());
    }
}
