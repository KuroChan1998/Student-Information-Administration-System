package com.jzy.util.title;

import com.jzy.entity.Title;
import com.jzy.util.MyDatabaseUtil;
import org.apache.commons.lang.StringUtils;

/**
 * @author JinZhiyun
 * @version 1.0
 * @ClassName TitleUtil
 * @description 验证服务端接收到的职称dto类型是否合法，与数据库保持一致（必须），与前端输入要求保持一致
 * @date 2019/9/10 16:27
 **/
public class TitleUtil extends MyDatabaseUtil implements TitleModel,TitleRedis {
    private static final int TITLE_NAME_MAX_LENGTH = 50;

    private static final int TITLE_REMARK_MAX_LENGTH = 500;

    private TitleUtil() {
    }

    /**
     * 保证Title的每个属性是否与数据库一直，符合规范
     **/
    public static boolean isValidTitleId(Integer titleId) {
        return isNotNullAndPositive(titleId);
    }

    public static boolean isValidTitleName(String titleName) {
        return !StringUtils.isEmpty(titleName) && titleName.length() <= TITLE_NAME_MAX_LENGTH;
    }

    public static boolean isValidTitleRemark(String titleRemark) {
        return titleRemark == null || titleRemark.length() <= TITLE_REMARK_MAX_LENGTH;
    }

    /**
     * @return boolean
     * @author JinZhiyun
     * @description 职称title对象的全部字段是否都合法
     * @date 10:10 2019/9/11
     * @Param [title]
     **/
    public static boolean isValidTitleAllInfo(Title title) {
        return !isNull(title) && isValidTitleId(title.getTitleId()) && isValidTitleName(title.getTitleName())
               && isValidTitleRemark(title.getTitleRemark());
    }
}
