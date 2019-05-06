package com.springmvc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmvc.dao.*;
import com.springmvc.dto.CollegeDto;
import com.springmvc.dto.MyPage;
import com.springmvc.entity.Class;
import com.springmvc.entity.College;
import com.springmvc.entity.Major;
import com.springmvc.entity.User;
import com.springmvc.service.CollegeService;
import com.springmvc.service.impl.util.DoWithString;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service
@Transactional
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<College> findAllCollege() {
        return collegeMapper.findAllCollege();
    }

    @Override
    public PageInfo<CollegeDto> queryAllCollegeInfo(MyPage myPage, String collegeName) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<CollegeDto> collegeDtos = collegeMapper.queryAllCollegeInfo(collegeName);
        return new PageInfo<>(collegeDtos);
    }

    @Override
    public PageInfo<CollegeDto> findCollegeOwnInfoById(MyPage myPage, User user) {
        List<CollegeDto> collegeDtos;
        if (user.getUserIdentity().equals("学生")) {
            PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
            collegeDtos = collegeMapper.findStuCollegeOwnInfoById(user.getUserId());
        } else {
            PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
            collegeDtos = collegeMapper.findTeaCollegeOwnInfoById(user.getUserId());
        }
        return new PageInfo<>(collegeDtos);
    }

    @Override
    public String updateMapDataResult(String collegeOriId, String collegeOriName, CollegeDto collegeDto) {
        if (!collegeOriId.equals(collegeDto.getCollegeId())) { //如果学院业编号修改过了
            if (findCollegeById(collegeDto.getCollegeId()) != null) { //如果修改后的学院编号已存在
                return "collegeIdExist";
            }
        }
        if (!collegeOriName.equals(collegeDto.getCollegeName())) { //如果学院名称修改过了
            if (findCollegeByName(collegeDto.getCollegeName()) != null) { //如果修改后的学院名称已存在
                return "collegeNameExist";
            }
        }

        if (!collegeDto.getCollegeTeaId().equals("")) { //如果该学院有负责人
            if (teacherMapper.findTeacherById(collegeDto.getCollegeTeaId()) == null) { //如果负责人id不存在
                return "collegeTeaIdNotExist";
            } else {
                String collegeId = findCollegeIdByTeaId(collegeDto.getCollegeTeaId());
                if (!(collegeId == null || collegeId.equals(collegeOriId))) { //若(collegeId==null|| collegeId.equals(collegeOriId))可以执行更新该负责人
                    return "collegeTeaIdRepeat";
                }
            }
        }

        //通过验证则update
        updateInfo(collegeOriId, collegeDto);
        return "updateSuccess";
    }

    @Override
    public College findCollegeById(String collegeId) {
        return collegeMapper.findCollegeById(collegeId);
    }

    @Override
    public College findCollegeByName(String collegeName) {
        return collegeMapper.findCollegeByName(collegeName);
    }

    @Override
    public String findCollegeIdByTeaId(String collegeTeaId) {
        College college = collegeMapper.findCollegeByTeaId(collegeTeaId);
        if (college == null) {
            return null;
        } else {
            return college.getCollegeId();
        }
    }

    @Override
    public void updateInfo(String collegeOriId, CollegeDto collegeDto) {
        collegeMapper.updateInfo(collegeOriId, collegeDto);
    }

    @Override
    public String findRecommendedMajorId() {
        String reId = collegeMapper.findMaxCollegeId();
        return DoWithString.doWithReid(reId);
    }

    @Override
    public String insertMapDataResult(CollegeDto collegeDto) {
        if (findCollegeById(collegeDto.getCollegeId()) != null) { //如果添加的学院编号已存在
            return "collegeIdExist";
        }
        if (findCollegeByName(collegeDto.getCollegeName()) != null) { //如果添加的学院名称已存在
            return "collegeNameExist";
        }
        if (!collegeDto.getCollegeTeaId().equals("")) { //如果该学院有负责人
            if (teacherMapper.findTeacherById(collegeDto.getCollegeTeaId()) == null) { //如果负责人id不存在
                return "collegeTeaIdNotExist";
            } else {
                String collegeId = findCollegeIdByTeaId(collegeDto.getCollegeTeaId());
                if (collegeId != null) { //若(collegeId==null)可以执行更新该负责人
                    return "collegeTeaIdRepeat";
                }
            }
        }
        //通过验证则insert

        insertCollege(collegeDto);
        return "insertSuccess";
    }

    @Override
    public void insertCollege(CollegeDto collegeDto) {
        collegeMapper.insertCollege(collegeDto);
    }

    @Override
    public void deleteOneCollege(CollegeDto collegeDto) {
        //先找出该学院下的所有专业
        List<Major> majors = majorMapper.findMajorByCollegeId(collegeDto.getCollegeId());
        for (Major major : majors) {
            //删除该专业下的所有教师
            teacherMapper.deleteTeachersByMajorId(major.getMajorId());
            //找出该专业下的所有班级
            List<Class> classes = classMapper.findClassByMajorId(major.getMajorId());
            for (Class myClass : classes) {
                //删除该班级下的所有学生
                studentMapper.deleteStudentsByClassId(myClass.getClassId());
            }
            //删除该专业下的所有班级
            classMapper.deleteClassByMajorId(major.getMajorId());
            //删除该专业
            majorMapper.deleteOneMajor(major.getMajorId());
        }

        collegeMapper.deleteOneCollege(collegeDto.getCollegeId());
    }

    @Override
    public void deleteManyColleges(List<CollegeDto> collegeDtos) {
        for (CollegeDto collegeDto : collegeDtos) {
            deleteOneCollege(collegeDto);
        }
    }

    @Override
    public List<Object> findCollegeStuNumPercent() {
        List<College> colleges=collegeMapper.findAllCollege();
        List<String> collegeNames=new ArrayList<>();
        List<Integer> collegeStuNums=new ArrayList<>();
        for (College college:colleges){
            collegeNames.add(college.getCollegeName());
            collegeStuNums.add(college.getCollegeStuNum());
        }
        List<Object> re=new ArrayList<>();
        re.add(collegeNames);
        re.add(collegeStuNums);
        return re;
    }
}
