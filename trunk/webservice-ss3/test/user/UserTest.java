package user;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.way.blog.service.MyUserLoginServiceImpl;
import com.way.blog.user.entity.MyUserDetial;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.entity.UserRegister;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.user.service.impl.UserRegisterServiceImpl;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.BlogZone;

import base.BaseTest;

public class UserTest extends BaseTest {
	
	private UserRegisterServiceImpl userRegisterService;
	private UserLoginServiceImpl userLoginService;
	
	private MyUserLoginServiceImpl myUserLoginServiceImpl;
	
	
	public void init(){
		userRegisterService = (UserRegisterServiceImpl)this.context.getBean("userRegisterServiceImpl");
		userLoginService = (UserLoginServiceImpl)this.context.getBean("userLoginServiceImpl");
		myUserLoginServiceImpl = (MyUserLoginServiceImpl)this.context.getBean("myUserLoginServiceImpl");
	}
	
	@Test
	public void testUserRegister(){
		this.init();
		////实例化注册信息
		UserRegister ur = new UserRegister();
		ur.setEmail(email);
		ur.setPassword(password);
		ur.setUsername(username);
		ur.setIp(ip);
		ur.setRegistrationTime(MyFormatDate.getFullDate(new Date()));
		ur.setEnabled(0);
		ur.setSex(1);
		UserLogin ul = new UserLogin();
		ul.setAccount(email);
		ul.setUsername(username);
		ul.setPassword(password);
		ul.setEnabled(1);
		ul.setCreateTime(MyFormatDate.getFullDate(new Date()));
		
		ur.setUserLogin(ul);
		ul.setUserRegister(ur);
		//userRegisterService.save(ur);

		////实例化用户详细信息
		MyUserDetial mud = new MyUserDetial();
		mud.setUsername(username);
		mud.setUser(ul);
		mud.setInterests("篮球、足球");
		ul.setMyUserDetial(mud);
		//userLoginService.save(ul);
		userRegisterService.save(ur);
		//userLoginService.save(ul);
	}
	
	@Test
	public void addUserLogin(){
		this.init();
		//实例化用户登录表
		UserLogin ul = new UserLogin();
		ul.setAccount(email);
		ul.setUsername(username);
		ul.setPassword(password);
		ul.setEnabled(1);
		ul.setCreateTime(MyFormatDate.getFullDate(new Date()));
		
		//ur.setUserLogin(ul);
		//ul.setUserRegister(ur);
		//userRegisterService.save(ur);

		////实例化用户详细信息
		MyUserDetial mud = new MyUserDetial();
		mud.setUsername(username);
		mud.setUser(ul);
		mud.setInterests("篮球、足球");
		ul.setMyUserDetial(mud);
		userLoginService.save(ul);
	}
	
	@Test
	public void testLoadUserLogin(){
		this.init();
		List<UserLogin> userLoginList = null;
		userLoginList = userLoginService.loadAll();
		if(null != userLoginList){
			for(UserLogin ul : userLoginList){
				System.out.println(ul.getAccount());
				System.out.println(ul.getBlogZone());
			}
		}else{
			System.out.println("没有相关的数据");
		}
	}
	
	@Test
	public void wsTest(){
		this.init();
		System.out.println(myUserLoginServiceImpl.userlogin("fenzaiway@qq.com", "way890727"));
	}
	
}