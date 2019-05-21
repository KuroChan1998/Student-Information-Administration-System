package com.springmvc.dao; /**
 * @ClassName UnitTestBase
 * @Author JinZhiyun
 * @Description //TODO
 * @Date 2019/4/8 9:27
 * @Version 1.0
 **/
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resources")
@ContextConfiguration(locations={"classpath:spring-context.xml"})
public abstract class UnitTestBase extends AbstractTransactionalJUnit4SpringContextTests {

}
