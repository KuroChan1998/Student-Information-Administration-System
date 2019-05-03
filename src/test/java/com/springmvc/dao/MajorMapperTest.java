package com.springmvc.dao;

import com.springmvc.dto.MajorWithCollegeDto;
import com.springmvc.entity.Major;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class MajorMapperTest extends UnitTestBase {
    @Autowired
    private MajorMapper majorMapper;

    @Test
    public void findMajorNameByCollege() {
        List<Major> majors=majorMapper.findMajorNameByCollege("");
        for (Major major:majors){
            System.out.println(major);
        }
    }

    @Test
    public void queryAllMajorInfo() {
        List<MajorWithCollegeDto> majorWithCollegeDtos=majorMapper.queryAllMajorInfo("电子","");
        for (MajorWithCollegeDto majorWithCollegeDto:majorWithCollegeDtos){
            System.out.println(majorWithCollegeDto);
        }
    }

    @Test
    public void findStuMajorOwnInfoById() {
        List<MajorWithCollegeDto> majorWithCollegeDtos=majorMapper.findStuMajorOwnInfoById("admin");
        for (MajorWithCollegeDto majorWithCollegeDto:majorWithCollegeDtos){
            System.out.println(majorWithCollegeDto);
        }
    }

    @Test
    public void findMajorById() {
        System.out.println(majorMapper.findMajorById("1"));
    }

    @Test
    public void findMajorByCollegeId() {
        List<Major> majors=majorMapper.findMajorByCollegeId("1");
        for (Major major:majors){
            System.out.println(major);
        }
    }
}