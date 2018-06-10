package app.creditapp.TestProj.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/*
 * 测试继承AbstractTransactionalSpringContextTests这个类
 * 继承该类，可以测试进行事务控制，测试完成后自动回滚
 * */
@RunWith(SpringJUnit4ClassRunner.class)
//locations:参数值因配置文件地址来改变
@ContextConfiguration
public class TestTwo extends AbstractTransactionalJUnit4SpringContextTests{

}
