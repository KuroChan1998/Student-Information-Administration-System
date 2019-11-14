package com.jzy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jzy.dao.MajorMapper;
import com.jzy.dto.major.MajorSearchDto;
import com.jzy.dto.major.MajorWithCollegeDto;
import com.jzy.dto.other.MyPage;
import com.jzy.entity.Major;
import com.jzy.entity.User;
import com.jzy.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MajorServiceImpl
 * @Author JinZhiyun
 * @Description 专业业务实现
 * @Date 2019/4/16 18:59
 * @Version 1.0
 **/
@Service("majorService")
public class MajorServiceImpl extends BaseServiceImpl implements MajorService {
    @Autowired
    protected MajorMapper majorMapper;

    @Override
    public List<Major> selectMajorByCollegeName(String collegeName) {
        return majorMapper.selectMajorByCollegeName(collegeName);
    }

    @Override
    public PageInfo<MajorWithCollegeDto> selectAllMajorInfo(MyPage myPage, MajorSearchDto majorSearch) {
        PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
        List<MajorWithCollegeDto> majorWCs = majorMapper.selectAllMajorInfo(majorSearch);
        return new PageInfo<>(majorWCs);
    }

    @Override
    public PageInfo<MajorWithCollegeDto> selectMajorOwnInfoByNum(MyPage myPage, User user) {
        List<MajorWithCollegeDto> majorWCs = new ArrayList<>();
        if (user.getUserIdentity().equals("学生")) {
            PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
            majorWCs.add(majorMapper.selectStuMajorOwnInfoByNum(user.getUserName()));
        } else if (user.getUserIdentity().equals("教师")) {
            PageHelper.startPage(myPage.getPageNum(), myPage.getPageSize());//第一个参数的意思为：当前页数，第二个参数的意思为：每页显示多少条记录
            majorWCs.add(majorMapper.selectTeaMajorOwnInfoByNum(user.getUserName()));
        }
        return new PageInfo<>(majorWCs);
    }

    @Override
    public String updateMajorInfo(String majorOriId, String majorOriName, MajorWithCollegeDto majorWC) {
        if (!majorOriName.equals(majorWC.getMajorName())) { //如果专业名称修改过了
            if (majorMapper.selectMajorByName(majorWC.getMajorName()) != null) { //如果修改后的专业名称已存在
                return "majorNameExist";
            }
        }

        if (!majorWC.getMajorTeaNum().equals("")) { //如果该专业有负责人
            if (teacherService.selectTeacherByNum(majorWC.getMajorTeaNum()) == null) { //如果负责人id不存在
                return "majorTeaNumNotExist";
            } else {
                Major major = majorMapper.selectMajorByTeaNum(majorWC.getMajorTeaNum());
                if (!(major == null || major.getMajorId().toString().equals(majorOriId))) {
                    return "majorTeaNumRepeat";
                }
            }
        }

        majorMapper.updateMajorInfo(majorOriId, majorWC);
        return "updateSuccess";
    }

    @Override
    public void updateMajorTeaNum(String teaOriNum, String teaNum) {
        majorMapper.updateMajorTeaNum(teaOriNum, teaNum);
    }

    @Override
    public String insertMajor(MajorWithCollegeDto majorWC) {
        if (majorMapper.selectMajorByName(majorWC.getMajorName()) != null) { //如果添加的专业名称已存在
            return "majorNameExist";
        }
        if (!majorWC.getMajorTeaNum().equals("")) { //如果该专业有负责人
            if (teacherService.selectTeacherByNum(majorWC.getMajorTeaNum()) == null) { //如果负责人工号不存在
                return "majorTeaNumNotExist";
            } else {
                Major major = majorMapper.selectMajorByTeaNum(majorWC.getMajorTeaNum());
                if (major != null) { //若(major==null)可以执行添加该负责人
                    return "majorTeaNumRepeat";
                }
            }
        }
        majorMapper.insertMajor(majorWC);
        return "insertSuccess";
    }

    @Override
    public void deleteOneMajor(String majorName) {
        majorMapper.deleteOneMajor(majorName);
    }

    @Override
    public void deleteManyMajors(List<String> majorNames) {
        for (String majorName : majorNames) {
            majorMapper.deleteOneMajor(majorName);
        }
    }
}
