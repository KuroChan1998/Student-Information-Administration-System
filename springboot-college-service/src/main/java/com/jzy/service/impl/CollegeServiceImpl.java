package com.jzy.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jzy.dao.CollegeMapper;
import com.jzy.dto.college.CollegeDto;
import com.jzy.dto.college.CollegeSearchDto;
import com.jzy.dto.other.MyPage;
import com.jzy.entity.College;
import com.jzy.entity.User;
import com.jzy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CollegeServiceImpl
 * @Author JinZhiyun
 * @Description 学院业务实现
 * @Date 2019/4/16 19:00
 * @Version 1.0
 **/
@Service("collegeService")
@Transactional
@com.alibaba.dubbo.config.annotation.Service
public class CollegeServiceImpl implements CollegeService {
    @Reference
    private UserService userService;

    @Reference
    private StudentService studentService;

    @Reference
    private TeacherService teacherService;

    @Reference
    private ClassService classService;

    @Reference
    private MajorService majorService;

    @Reference
    private GradeService gradeService;

    @Reference
    private TitleService titleService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<College> selectAllCollege() {
        return collegeMapper.selectAllCollege();
    }

    @Override
    public PageInfo<CollegeDto> selectAllCollegeInfo(MyPage myPage, CollegeSearchDto collegeSearchDto) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<CollegeDto> collegeDtos = collegeMapper.selectAllCollegeInfo(collegeSearchDto);
        return new PageInfo<>(collegeDtos);
    }

    @Override
    public PageInfo<CollegeDto> selectCollegeOwnInfoByNum(MyPage myPage, User user) {
        List<CollegeDto> collegeDtos = new ArrayList<>();
        if (user.getUserIdentity().equals("学生")) {
            PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
            collegeDtos.add(collegeMapper.selectStuCollegeOwnInfoByNum(user.getUserName()));
        } else if (user.getUserIdentity().equals("教师")) {
            PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
            collegeDtos.add(collegeMapper.selectTeaCollegeOwnInfoByNum(user.getUserName()));
        }
        return new PageInfo<>(collegeDtos);
    }

    @Override
    public String updateCollegeInfo(String collegeOriId, String collegeOriName, CollegeDto collegeDto) {
        if (!collegeOriName.equals(collegeDto.getCollegeName())) { //如果学院名称修改过了
            if (collegeMapper.selectCollegeByName(collegeDto.getCollegeName()) != null) { //如果修改后的学院名称已存在
                return "collegeNameExist";
            }
        }

        if (!collegeDto.getCollegeTeaNum().equals("")) { //如果该学院有负责人
            if (teacherService.selectTeacherByNum(collegeDto.getCollegeTeaNum()) == null) { //如果负责人工号不存在
                return "collegeTeaNumNotExist";
            } else {
                College college = collegeMapper.selectCollegeByTeaNum(collegeDto.getCollegeTeaNum());
                if (!(college == null || college.getCollegeId().toString().equals(collegeOriId))) {
                    return "collegeTeaNumRepeat";
                }
            }
        }
        //通过验证则update
        collegeMapper.updateCollegeInfo(collegeOriId, collegeDto);
        return "updateSuccess";
    }

    @Override
    public void updateCollegeTeaNum(String teaOriNum, String teaNum) {
        collegeMapper.updateCollegeTeaNum(teaOriNum, teaNum);
    }

    @Override
    public String insertCollege(CollegeDto collegeDto) {
        if (collegeMapper.selectCollegeByName(collegeDto.getCollegeName()) != null) { //如果添加的学院名称已存在
            return "collegeNameExist";
        }
        if (!collegeDto.getCollegeTeaNum().equals("")) { //如果该学院有负责人
            if (teacherService.selectTeacherByNum(collegeDto.getCollegeTeaNum()) == null) { //如果负责人id不存在
                return "collegeTeaNumNotExist";
            } else {
                College college = collegeMapper.selectCollegeByTeaNum(collegeDto.getCollegeTeaNum());
                if (college != null) { //若(collegeId==null)可以执行更新该负责人
                    return "collegeTeaNumRepeat";
                }
            }
        }
        //通过验证则insert

        collegeMapper.insertCollege(collegeDto);
        return "insertSuccess";
    }

    @Override
    public void deleteOneCollege(String collegeName) {
        collegeMapper.deleteOneCollege(collegeName);
    }

    @Override
    public void deleteManyColleges(List<String> collegeNames) {
        for (String collegeName : collegeNames) {
            deleteOneCollege(collegeName);
        }
    }

}
