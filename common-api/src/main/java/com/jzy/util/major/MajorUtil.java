package com.jzy.util.major;

import com.jzy.dto.major.MajorWithCollegeDto;
import com.jzy.entity.Major;
import com.jzy.util.MyDatabaseUtil;
import com.jzy.util.college.CollegeUtil;
import com.jzy.util.teacher.TeacherUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName MajorUtil
 * @description 验证服务端接收到的专业dto类型是否合法，与数据库保持一致（必须），与前端输入要求保持一致
 * @date 2019/9/10 16:27
 **/
public class MajorUtil extends MyDatabaseUtil implements MajorModel, MajorRedis {
    private static final int MAJOR_NAME_MAX_LENGTH = 50;

    private static final int MAJOR_REMARK_MAX_LENGTH = 500;

    private MajorUtil() {
    }

    /**
     * 保证Major的每个属性是否与数据库一直，符合规范
     **/
    public static boolean isValidMajorId(Integer majorId) {
        return isNotNullAndPositive(majorId);
    }

    public static boolean isValidMajorCollege(Integer majorCollege) {
        return isNotNullAndPositive(majorCollege);
    }

    public static boolean isValidMajorName(String majorName) {
        return !StringUtils.isEmpty(majorName) && majorName.length() <= MAJOR_NAME_MAX_LENGTH;
    }

    public static boolean isValidMajorTeaNum(String teaNum) {
        return StringUtils.isEmpty(teaNum) || TeacherUtil.isValidTeaNum(teaNum);
    }

    public static boolean isValidMajorRemark(String majorRemark) {
        return majorRemark == null || majorRemark.length() <= MAJOR_REMARK_MAX_LENGTH;
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 专业major对象的全部字段是否都合法
     * @date 10:10 2019/9/11
     * @Param [major]
     **/
    public static boolean isValidMajorAllInfo(Major major) {
        return !isNull(major) && isValidMajorId(major.getMajorId()) && isValidMajorCollege(major.getMajorCollege())
                && isValidMajorName(major.getMajorName()) && isValidMajorTeaNum(major.getMajorTeaNum()) && isValidMajorRemark(major.getMajorRemark());
    }

    /**
     * @author JinZhiyun
     * @description 判断修改专业信息时服务端接收到的MajorWithCollegeDto参数是否合法
     * @date 22:21 2019/9/11
     * @Param [majorWC]
     * @return boolean
     **/
    public static boolean isValidMajorUpdateInfo(MajorWithCollegeDto majorWC) {
        return !isNull(majorWC) && CollegeUtil.isValidCollegeName(majorWC.getMajorCollegeName()) && MajorUtil.isValidMajorName(majorWC.getMajorName())
                && MajorUtil.isValidMajorTeaNum(majorWC.getMajorTeaNum()) && MajorUtil.isValidMajorRemark(majorWC.getMajorRemark());
    }

    /**
     * @author JinZhiyun
     * @description 判断添加专业信息时服务端接收到的MajorWithCollegeDto参数是否合法
     * @date 22:26 2019/9/11
     * @Param [majorWC]
     * @return boolean
     **/
    public static boolean isValidMajorInsertInfo(MajorWithCollegeDto majorWC) {
        return isValidMajorUpdateInfo(majorWC);
    }
}
