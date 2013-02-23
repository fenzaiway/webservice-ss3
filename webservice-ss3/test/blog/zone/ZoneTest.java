package blog.zone;

import java.util.Date;

import org.junit.Test;

import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.blog.service.IBlogZoneService;
import com.way.blog.zone.blog.service.impl.BlogZoneServiceImpl;
import com.way.blog.zone.entity.BlogZone;

import base.BaseTest;

public class ZoneTest extends BaseTest {
	private BlogZoneServiceImpl blogZoneService;
	private UserLoginServiceImpl userLoginService;
	
	public void init(){
		blogZoneService = (BlogZoneServiceImpl)this.context.getBean("blogZoneServiceImpl");
		userLoginService = (UserLoginServiceImpl)this.context.getBean("userLoginServiceImpl");
	}
	
	@Test
	public void findZoneByUrl(){
		this.init();
		//BlogZone bz = blogZoneService.findByProperty("zoneUrl", "127.0.0.1/fenzaiway2233").get(0);
		BlogZone bz = blogZoneService.findByProperty("username", "fenzaiway").get(0);
		System.out.println(bz.getBlogZoneCreateTime());
		System.out.println(bz.getBlogZoneDesc());
		System.out.println(bz.getBlogZoneName());
		System.out.println(bz.getUserLogin().getAccount());
		System.out.println(bz.getUserLogin().getMyUserDetial().getUsername());
	}
	
	@Test
	public void testAddBlogZone(){
		this.init();
		BlogZone bz = new BlogZone();
		bz.setSignName("哈哈111");
		bz.setZoneUrl((ip+"/"+username));
		bz.setBlogZoneName("way's zone");
		bz.setBlogZoneDesc("good zone");
		bz.setBlogZoneCreateTime(MyFormatDate.getFullDate(new Date()));
		UserLogin ul = userLoginService.loadAll().get(0);
		bz.setUserLogin(ul);
		ul.setBlogZone(bz);
		blogZoneService.save(bz);
	}
	
	@Test
	public void findByProperty(){
		this.init();
		UserLogin ul = userLoginService.findUserLoginByUserName("fenzaiway");
		BlogZone bz = ul.getBlogZone();
		System.out.println(bz.getBlogZoneName());
	}
}