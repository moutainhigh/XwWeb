package app.creditapp.TestProj.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.creditapp.sys.bo.SysUserBo;
import app.creditapp.sys.entity.SysUser;
/*
 * 测试继承AbstractTransactionalSpringContextTests这个类
 * 继承该类，可以测试进行事务控制，测试完成后自动回滚
 * */
@RunWith(SpringJUnit4ClassRunner.class)
//locations:参数值因配置文件地址来改变
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class LoginBoImplTest extends AbstractTransactionalJUnit4SpringContextTests{

	
	//注入service对象
    @Autowired
    private SysUserBo sysUserBo;
    @Test
    //还可以加入@RollBack注解 @Transaction注解来对方法进行事务注解
    public void testFindItemsList() throws Exception {
    	SysUser op = new SysUser();
    	op.setUser_no("su1573");
		op.setPass_word("000000");
    	
    	SysUser sysUser = sysUserBo.getById(op);
    	
        System.out.println(sysUser.getId_no());
    }   
}
