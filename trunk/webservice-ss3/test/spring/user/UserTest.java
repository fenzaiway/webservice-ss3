package spring.user;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.way.blog.user.service.impl.UserLoginServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UserTest extends AbstractJUnit4SpringContextTests {

	@Resource
	protected UserLoginServiceImpl userLoginServiceImpl;
	
	public UserLoginServiceImpl getUserLoginServiceImpl() {
		return userLoginServiceImpl;
	}

	public void setUserLoginServiceImpl(UserLoginServiceImpl userLoginServiceImpl) {
		this.userLoginServiceImpl = userLoginServiceImpl;
	}

	@Test
	 public void loadTest(){
		 System.out.println(userLoginServiceImpl.findById(1).getUsername());
	 }
}
