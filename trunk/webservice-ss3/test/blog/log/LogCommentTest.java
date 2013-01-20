package blog.log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.blog.service.impl.LogCommentServiceImpl;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.entity.LogComment;
import com.way.blog.zone.entity.LogInfo;

import base.BaseTest;

/**
 * 
 * @author fenzaiway
 * 日志评论测试类
 */
public class LogCommentTest extends BaseTest {
	
	private LogCommentServiceImpl logCommentService; 
	private LogInfoServiceImpl logInfoService;
	
	public void init(){
		logCommentService = (LogCommentServiceImpl) this.context.getBean("logCommentServiceImpl");
		logInfoService = (LogInfoServiceImpl) this.context.getBean("logInfoServiceImpl");
	}
	
	
	
	
	////测试文章评论（这里把所有的文章都取出来，然后每一篇都做相同评论）
	@Test
	public void addLogComment(){
		this.init();
		LogComment lc = null;
		Set<LogComment> lcs = null;
		List<LogComment> lcList = new ArrayList<LogComment>();
		for(LogInfo li : logInfoService.loadAll()){
			lc = new LogComment();
			System.out.println(li.getLogTitle());
			lc.setUsername("fenzaiway");
			lc.setCommentContent("这个是什么文章啊？");
			lcs = new HashSet<LogComment>();
			lcs.add(lc);
			lc.setLogInfo(li);
			lc.setCommentTime(MyFormatDate.getFullDate(new Date()));
			li.setLogComments(lcs);
			lcList.add(lc);
		}
		System.out.println(lcList.size());
		logCommentService.saveAll(lcList);
	}
	
	@Test
	public void getLogComment(){
		SetNullDemo<LogComment>  nullDemo = SetNullDemo.instance();
		System.out.println(nullDemo);
		this.init();
		LogInfo li = new LogInfo();
		li.setId(1);
		List<LogComment> lcList = logCommentService.find("from LogComment where logInfo=?", li);
		//System.out.println(lcList.size());
		lcList = nullDemo.setNull(lcList, null, new String[]{"logInfo","logCommentReplys"});
		System.out.println(lcList);
		System.out.println(lcList.get(0).getLogCommentReplys());
	}
}
