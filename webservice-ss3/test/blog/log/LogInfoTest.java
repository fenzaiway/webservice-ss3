package blog.log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.blog.service.impl.LogTypeServiceImpl;
import com.way.blog.zone.entity.LogAttachment;
import com.way.blog.zone.entity.LogComment;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogType;

import base.BaseTest;

public class LogInfoTest extends BaseTest {
	
	private LogInfoServiceImpl logInfoService;
	private LogTypeServiceImpl logTypeService;
	
	public void init(){
		logInfoService = (LogInfoServiceImpl) this.context.getBean("logInfoServiceImpl");
		logTypeService = (LogTypeServiceImpl) this.context.getBean("logTypeServiceImpl");
	}
	
	
	@Test
	public void addLogInfo(){
		this.init();
		LogInfo li = new LogInfo();
		li.setLogAllowComment(1);
		li.setLogAllowVisit(1);
		li.setLogContentStatus(1);
		li.setLogIsOriginal(1);
		li.setLogPublishTime(MyFormatDate.getFullDate(new Date()));
		li.setLogTitle("这是一篇测试文章");
		li.setLogText("出处：屌丝一词起源于雷霆三巨头吧对李毅吧毅丝的恶搞称谓，后被魔兽世界吧会员用于嘲讽毅丝，意为劣等毅丝。此后李毅吧友儿童多篇讲述了自己的猥琐吊丝故事，众毅丝不挂纷纷表示男默女泪。自此，吊丝文化诞生。2012年2月，凤凰网报道专题《屌丝：一个字头的诞生》，腾讯网紧跟其后发表《屌丝：庶民的文化胜利》，此后吊丝文化被发扬光大，被社会广泛接受。");
		li.setLogToTop(2);
		LogType lt = logTypeService.findById(1);
		li.setLogType(lt);
		Set<LogInfo> lis = new HashSet<LogInfo>();
		lis.add(li);
		lt.setLogInfos(lis);
		logInfoService.save(li);
	}
	
	@Test
	public void loadLogInfo(){
		this.init();
		for(LogInfo li : logInfoService.loadAll()){
			System.out.println(li.getLogType().getLogTypeName());
			System.out.println(li.getLogText());
			List<LogComment> lcs = new ArrayList<LogComment>(li.getLogComments());
			System.out.println("评论:" + lcs.get(0).getCommentContent());
		}
	}
	
	////测试插入日志加附件
	@Test
	public void addLogInfoAndAttachment(){
		this.init();
		LogInfo li = new LogInfo();
		li.setLogAllowComment(1);
		li.setLogAllowVisit(1);
		li.setLogContentStatus(1);
		li.setLogIsOriginal(1);
		li.setLogPublishTime(MyFormatDate.getFullDate(new Date()));
		li.setLogToTop(2);
		li.setLogTitle("测试插入日志加附件");
		li.setLogText("这是一篇测试文章，文章中包含附件，测试看看添加数据的时候是否正常");
		////实例化附件对象
		LogAttachment la = new LogAttachment();
		la.setAttachmentDownloadCount(0);
		la.setAttachmentName("图片1111");
		la.setAttachmentUploadTime(MyFormatDate.getFullDate(new Date()));
		la.setUrl("/upload/images/aa.jpg");
		///设置双向关联
		la.setLogInfo(li);
		Set<LogAttachment> las = new HashSet<LogAttachment>();
		las.add(la);
		li.setLogAttachments(las);
		logInfoService.save(li);

	}
	
}
