package blog.log;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.blog.service.impl.BlogZoneServiceImpl;
import com.way.blog.zone.blog.service.impl.LogTypeServiceImpl;
import com.way.blog.zone.entity.BlogZone;
import com.way.blog.zone.entity.LogType;

import base.BaseTest;

public class LogTypeTest extends BaseTest {
	
	private LogTypeServiceImpl logTypeService;
	private BlogZoneServiceImpl blogZoneService;
	
	public void init(){
		logTypeService = (LogTypeServiceImpl) this.context.getBean("logTypeServiceImpl");
		blogZoneService = (BlogZoneServiceImpl)this.context.getBean("blogZoneServiceImpl");
	}
	
	////添加分类
	@Test
	public void addLogType(){
		this.init();
		LogType lt = new LogType();
		lt.setLogTypeCreateTime(MyFormatDate.getFullDate(new Date()));
		lt.setLogTypeName("软件开发");
		lt.setIsDefaultLogType(1);
		////取得用户空间（根据URL访问）
		BlogZone bz = blogZoneService.findByProperty("zoneUrl", "127.0.0.1/fenzaiway2233").get(0);
		lt.setBlogZone(bz);
		Set<LogType> logTypes = new HashSet<LogType>();
		logTypes.add(lt);
		bz.setLogTypes(logTypes);
		logTypeService.save(lt);
		
	}
	
	///获取分类
	@Test
	public void getAllLogType(){
		this.init();
		for(LogType lt : logTypeService.loadAll()){
			System.out.println(lt.getLogTypeName());
		}
	}
}
