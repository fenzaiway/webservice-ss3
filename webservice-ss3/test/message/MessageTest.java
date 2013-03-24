package message;

import org.junit.Test;

import com.way.blog.base.service.impl.MessageServiceImpl;
import com.way.blog.zone.entity.LogInfo;

import base.BaseTest;

public class MessageTest extends BaseTest {
	
	private MessageServiceImpl messageServiceImpl;
	
	public void init(){
		messageServiceImpl = (MessageServiceImpl) this.context.getBean("messageServiceImpl");
	}
	
	@Test
	public void getMessageTest(){
		this.init();
		LogInfo logInfo = new LogInfo();
		logInfo.setId(216);
		System.out.println("----message---" + messageServiceImpl);
		System.out.println("aawaa====" + messageServiceImpl.find("from Message where 1=1 and logInfo=?", new Object[]{logInfo}));
		
		
		
		
	}
	
	
}
