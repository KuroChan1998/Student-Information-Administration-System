package com.jzy.springbootuserservice;

import com.jzy.service.StudentService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootUserServiceApplicationTests {
    @Reference
    private StudentService studentService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test(){
        System.out.println(studentService.selectStudentByNum("516030910429"));
    }
}
