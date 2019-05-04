package com.springmvc.dao;

import com.springmvc.dto.ClassWithMajorCollegeDto;
import com.springmvc.entity.Class;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClassMapper {
    /**
     * @return java.util.List<java.lang.Class>
     * @Author JinZhiyun
     * @Description 查询符合班级条件的信息
     * @Date 22:33 2019/4/23
     * @Param [majorName]
     **/
    List<Class> findClassNameByMajor(@Param("majorName") String majorName);

    /**
     * @return java.util.List<com.springmvc.dto.ClassWithMajorCollegeDto>
     * @Author JinZhiyun
     * @Description 查询符合条件的所有班级信息
     * @Date 22:52 2019/4/18
     * @Param [className, classMajorName, classCollegeName]
     **/
    List<ClassWithMajorCollegeDto> queryAllClassInfo(@Param("className") String className, @Param("classMajorName") String classMajorName, @Param("classCollegeName") String classCollegeName);

    /**
     * @return java.util.List<com.springmvc.dto.ClassWithMajorCollegeDto>
     * @Author JinZhiyun
     * @Description 查询学生用户Id对应的班级及其附带信息
     * @Date 15:20 2019/4/19
     * @Param [stuId]
     **/
    List<ClassWithMajorCollegeDto> findStuClassOwnInfoById(@Param("stuId") String stuId);

    /**
     * @return java.util.List<com.springmvc.dto.ClassWithMajorCollegeDto>
     * @Author JinZhiyun
     * @Description 查询教师用户Id对应的班级及其附带信息
     * @Date 15:42 2019/4/19
     * @Param [teaId]
     **/
    List<ClassWithMajorCollegeDto> findTeaClassOwnInfoById(@Param("teaId") String teaId);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 由班级名称寻找班级id
     * @Date 19:23 2019/4/26
     * @Param [className]
     **/
    String findIdByClassName(@Param("className") String className);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 指定班级学生人数+1
     * @Date 19:23 2019/4/26
     * @Param [classId]
     **/
    void updateStuNumAddOne(@Param("classId") String classId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 指定班级学生人数-1
     * @Date 21:58 2019/4/26
     * @Param [classId]
     **/
    void updateStuNumMinusOne(@Param("classId") String classId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 指定班级学生人数+n
     * @Date 9:10 2019/5/1
     * @Param [n, classId]
     **/
    void updateStuNumAddN(@Param("n") Integer n, @Param("classId") String classId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 指定班级学生人数-n
     * @Date 9:10 2019/5/1
     * @Param [n, classId]
     **/
    void updateStuNumMinusN(@Param("n") Integer n, @Param("classId") String classId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更新班长id
     * @Date 21:23 2019/4/27
     * @Param [classMoniId]
     **/
    void updateMoniId(@Param("classOriMoniId") String classOriMoniId, @Param("classNewMoniId") String classNewMoniId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除学生id时若该学生是班长将此班级班长id置null？
     * @Date 21:43 2019/4/27
     * @Param [classMoniId]
     **/
    void updateDeleteMoni(@Param("classMoniId") String classMoniId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 更新班主任id
     * @Date 22:10 2019/4/27
     * @Param [classOriTeaId, classNewTeaId]
     **/
    void updateTeaId(@Param("classOriTeaId") String classOriTeaId, @Param("classNewTeaId") String classNewTeaId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除教师id时若该教师是班主任将此班级班主任id置null？
     * @Date 23:02 2019/4/27
     * @Param [classTeaId]
     **/
    void updateDeleteTea(@Param("classTeaId") String classTeaId);

    /**
     * @return com.springmvc.entity.Class
     * @Author JinZhiyun
     * @Description 由班级编号查询班级
     * @Date 20:30 2019/4/28
     * @Param [classId]
     **/
    Class findClassById(@Param("classId") String classId);

    /**
     * @return com.springmvc.entity.Class
     * @Author JinZhiyun
     * @Description 由班级名称查询班级
     * @Date 20:56 2019/4/28
     * @Param [className]
     **/
    Class findClassByName(@Param("className") String className);

    /**
     * @return com.springmvc.entity.Class
     * @Author JinZhiyun
     * @Description 查询班长所在的班级信息
     * @Date 21:22 2019/4/28
     * @Param [classMoniId]
     **/
    Class findClassByMoniId(@Param("classMoniId") String classMoniId);

    /**
     * @return com.springmvc.entity.Class
     * @Author JinZhiyun
     * @Description 查询班主任所在的班级信息
     * @Date 21:44 2019/4/28
     * @Param [classTeaId]
     **/
    Class findClassByTeaId(@Param("classTeaId") String classTeaId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 修改班级信息
     * @Date 22:18 2019/4/28
     * @Param [classOriId, classWMCD]
     **/
    void updateInfo(@Param("classOriId") String classOriId, @Param("classWMCD") ClassWithMajorCollegeDto classWMCD);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 向班级表插入一个班级
     * @Date 22:07 2019/4/28
     * @Param [classWMCD]
     **/
    void insertClass(ClassWithMajorCollegeDto classWMCD);

    /**
     * @return java.lang.String
     * @Author JinZhiyun
     * @Description 找到最大的班级编号
     * @Date 10:12 2019/4/29
     * @Param []
     **/
    String findMaxClassId();

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 删除一个班级
     * @Date 8:26 2019/5/1
     * @Param [classId]
     **/
    void deleteOneClass(@Param("classId") String classId);

    /**
     * @return java.util.List<com.springmvc.entity.Class>
     * @Author JinZhiyun
     * @Descriptio 找到指定专业id下的所有班级
     * @Date 14:15 2019/5/2
     * @Param [majorId]
     **/
    List<Class> findClassByMajorId(@Param("majorId") String majorId);

    /**
     * @return void
     * @Author JinZhiyun
     * @Description 根据专业编号删除其下的班级
     * @Date 14:20 2019/5/2
     * @Param [majorId]
     **/
    void deleteClassByMajorId(@Param("majorId") String majorId);

}
