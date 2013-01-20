package ss3;

import org.junit.Test;

import com.way.blog.ss3.entity.MyAuthority;
import com.way.blog.ss3.entity.MyResources;
import com.way.blog.ss3.entity.MyRoles;
import com.way.blog.ss3.service.impl.MyAuthorityServiceImpl;
import com.way.blog.ss3.service.impl.MyResourcesServiceImpl;
import com.way.blog.ss3.service.impl.MyRolesServiceImpl;
import com.way.blog.user.service.impl.UserLoginServiceImpl;

import base.BaseTest;

/**
 * 
 * @author fenzaiway
 * 用户角色测试
 */
public class SS3Test extends BaseTest {
	
	private MyRolesServiceImpl myRolesServiceImpl;
	private UserLoginServiceImpl userLoginServiceImpl;
	private MyResourcesServiceImpl myResourcesServiceImpl;
	private MyAuthorityServiceImpl myAuthorityServiceImpl;
	
	
	public void init(){
		myRolesServiceImpl = (MyRolesServiceImpl) this.context.getBean("myRolesServiceImpl");
		userLoginServiceImpl = (UserLoginServiceImpl) this.context.getBean("userLoginServiceImpl");
		myResourcesServiceImpl = (MyResourcesServiceImpl) this.context.getBean("myResourcesServiceImpl");
		myAuthorityServiceImpl = (MyAuthorityServiceImpl) this.context.getBean("myAuthorityServiceImpl");
	}
	
	/**
	 * 添加用户
	 */
	@Test
	public void addUser(){
		this.init();
		
	}
	
	/**
	 * 添加角色
	 */
	@Test
	public void addRole(){
		this.init();
		MyRoles myRoles = new MyRoles();
		myRoles.setRoleName("ROLE_ADMIN");
		myRoles.setRoleDesc("系统管理员");
		myRolesServiceImpl.save(myRoles);
	}
	
	/**
	 * 添加权限
	 */
	@Test
	public void addAuthority(){
		this.init();
		MyAuthority ma = new MyAuthority();
		ma.setEnable(1);
		ma.setAuthorityName("delete");
		myAuthorityServiceImpl.save(ma);
	}
	
	/**
	 * 添加资源
	 */
	@Test
	public void addResources(){
		this.init();
		MyResources mr= new MyResources();
		mr.setUrl("/admin*/**");
		mr.setDescString("系统管理员访问路径");
		mr.setType(".do,*.jsp");
		mr.setResourceName("系统管理员访问路径");
		myResourcesServiceImpl.save(mr);
	}
}
