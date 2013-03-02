package mail;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; 
import javax.mail.internet.MimeUtility;


import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl; 
import org.springframework.mail.javamail.MimeMessageHelper; 
import org.springframework.stereotype.Service;

import com.way.blog.util.web.SendMailService;

import base.BaseTest;

@Service
public class MailServiceTest extends BaseTest{
	
	private JavaMailSenderImpl sender;
	
	private SendMailService sendMailService;
	
	public void init(){
	sender = (JavaMailSenderImpl) this.context.getBean("sender");
	sendMailService = (SendMailService) this.context.getBean("sendMailService");
	}
	
	@Test
	public void sendHtmlTest(){
		this.init();
		List<Map<String, String>> maps = new ArrayList<Map<String,String>>();
		Map map1 = new HashMap<String, String>();
		map1.put("抄送人001", "fenzaiway@sina.com");
		Map map2 = new HashMap<String, String>();
		map2.put("抄送人001", "fenzaiway@foxmail.com");
		maps.add(map1);
		maps.add(map2);
		List<String> userEmailList = new ArrayList<String>();
		userEmailList.add("fenzaiway@qq.com");
		String htmlText="<html><head><title>测试封装的发送html</title></head><body>111这是一篇使用封装的HTML邮件，<a href='http://www.baidu.com'>百度</a></body></html>";
		sendMailService.sendMailByHtml(userEmailList, "测试发送html邮件", htmlText, maps);
	}
	
	@Test
	public void sendMailTest(){
		this.init();
		System.out.println(sender);
		System.out.println(sender.getPassword());
		System.out.println(sender.getUsername());
		System.out.println(sender.getHost());
		System.out.println(sender.getJavaMailProperties().getProperty("mail.smtp.auth"));
		System.out.println(sender.getJavaMailProperties().getProperty("mail.smtp.timeout"));
		//sender = new JavaMailSenderImpl();
		sender.setHost( "smtp.sina.com" );
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setFrom("fenzaiway@sina.com");
		mail.setTo("fenzaiway@qq.com");
		mail.setReplyTo("fenzaiway@vip.qq.com");
		mail.setCc("fenzaiway@sina.com");
		mail.setSubject("注册成功11");
		mail.setText("尊敬的fenzaiway用户，恭喜您在轻轻一点博客注册成功");
		//sender.setUsername( "fenzaiway@sina.com" ) ;  //  根据自己的情况,设置username 
		//sender.setPassword( "moway890727" ) ;  //  根据自己的情况, 设置password 
		// Properties prop  =   new  Properties() ;
		// prop.put( "mail.smtp.auth" ,  "true" ) ;  //  将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 
		// prop.put( "mail.smtp.timeout" ,  "25000" ) ; 
		// sender.setJavaMailProperties(prop);  
		sender.send(mail);
	}
	
	/*public   static   void  main(String args[]){ //测试简单文本邮件
	    JavaMailSenderImpl senderImpl  =   new  JavaMailSenderImpl(); 
	   // 设定mail server  
	    senderImpl.setHost( "smtp.sina.com" );
	     
	     // 建立邮件消息  
	    SimpleMailMessage mailMessage  =   new  SimpleMailMessage(); 
	     // 设置收件人，寄件人 用数组发送多个邮件
	     // String[] array = new String[]    {"sun111@163.com","sun222@sohu.com"};    
	     // mailMessage.setTo(array);  
	    mailMessage.setTo( "fenzaiway@qq.com" ); 
	    mailMessage.setFrom( "fenzaiway@sina.com" ); 
	    mailMessage.setSubject( " 测试简单文本邮件发送！ " ); 
	    mailMessage.setText( " 测试我的简单邮件发送机制！！ " ); 
	    
	    senderImpl.setUsername( "fenzaiway@sina.com" ) ;  //  根据自己的情况,设置username 
	    senderImpl.setPassword( "moway890727" ) ;  //  根据自己的情况, 设置password 
	    
	 Properties prop  =   new  Properties() ;
	 prop.put( "mail.smtp.auth" ,  "true" ) ;  //  将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 
	 prop.put( "mail.smtp.timeout" ,  "25000" ) ; 
	 senderImpl.setJavaMailProperties(prop);  
	     // 发送邮件  
	    senderImpl.send(mailMessage); 
	     
	    System.out.println( " 邮件发送成功.. " ); 
	     } */
	
	public static void main(String[] args) throws Exception{ //测试html邮件
//			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl(); 
//			 senderImpl.setHost( "smtp.qq.com" );
//	      //  JavaMailSender mailSender = MailSenderFactory.getJavaMailSender();  
//	        MimeMessage mimeMessage = senderImpl.createMimeMessage();  
//	        try {  
//	            System.out.println("HTML脚本形式邮件正在发送...");  
//	            //设置utf-8或GBK编码，否则邮件会有乱码  
//	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8"); 
//	           // MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");  
//	            //设置发送人名片  
//	            helper.setFrom("fenzaiway@qq.com");  
//	            //设置收件人名片和地址  
//	            helper.setTo(new InternetAddress("\"" + MimeUtility.encodeText("qq邮箱") + "\" <fenzaiway@qq.com>"));// 发送者  
//	            // 邮件发送时间  
//	            helper.setSentDate(new Date());  
//	            //设置回复地址  
//	            helper.setReplyTo(new InternetAddress("fenzaiway@sina.com"));  
//	            //设置抄送的名片和地址  
//	            helper.setCc(InternetAddress.parse(MimeUtility.encodeText("抄送人001") + " <fenzaiway@sina.com>," + MimeUtility.encodeText("抄送人002") + " <fenzaiway@foxmail.com>"));  
//	            //主题  
//	            helper.setSubject("HTML邮件");  
//	            // 邮件内容，注意加参数true，表示启用html格式  
//	            helper.setText("<html><head></head><body><h1>hello!!我是乔布斯111</h1><a href=\"http://172.16.13.98:8080/ss3/zone/fenzaiway\">验证</a></body></html>",true);  
//	            senderImpl.setUsername( "fenzaiway@qq.com" ) ;  //  根据自己的情况,设置username 
//	    	    senderImpl.setPassword( "Ajavaway890905" ) ;  //  根据自己的情况, 设置password 
//	    	    
//	    	 Properties prop  =   new  Properties() ;
//	    	 prop.put( "mail.smtp.auth" ,  "true" ) ;  //  将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 
//	    	 prop.put( "mail.smtp.timeout" ,  "25000" ) ; 
//	    	 senderImpl.setJavaMailProperties(prop);  
//	            //发送  
//	            senderImpl.send(mimeMessage);  
//	        } catch (Exception e) {  
//	            e.printStackTrace();  
//	        }  
	        System.out.println("HTML脚本形式邮件发送成功！！！");  
	     
	} 
	/*public static void main(String[] args) throws Exception{ 
	    JavaMailSenderImpl senderImpl = new JavaMailSenderImpl(); 
	    
	    //设定mail server 
	    senderImpl.setHost("smtp.sina.com"); 

	    //建立邮件消息,发送简单邮件和html邮件的区别 
	    MimeMessage mailMessage = senderImpl.createMimeMessage(); 
	    //注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，         
	    //multipart模式 
	    MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true); 
	    
	    //设置收件人，寄件人 
	    messageHelper.setTo("fenzaiway@qq.com"); 
	    messageHelper.setFrom("fenzaiway@sina.com"); 
	    messageHelper.setSubject("测试邮件中嵌套图片!！"); 
	    //true 表示启动HTML格式的邮件 
	    messageHelper.setText("<html><head></head><body><h1>hello!!spring image html mail</h1>" + 
	    "<img src=\"cid:aaa\"/></body></html>",true); 
	        
	    FileSystemResource img = new FileSystemResource(new File("d:"+java.io.File.separator+"a3591073.jpg")); 
	    
	    messageHelper.addInline("aaa",img); 
	    
	    senderImpl.setUsername("fenzaiway@sina.com") ; // 根据自己的情况,设置username
	    senderImpl.setPassword("moway890727") ; // 根据自己的情况, 设置password
	    Properties prop = new Properties() ;
	    prop.put("mail.smtp.auth", "true") ; // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
	    prop.put("mail.smtp.timeout", "25000") ; 
	    senderImpl.setJavaMailProperties(prop); 
	    
	    //发送邮件 
	    senderImpl.send(mailMessage); 
	    
	    System.out.println("邮件发送成功.."); 
	} */
	/*public static void main(String[] args) throws Exception{ 
	    JavaMailSenderImpl senderImpl = new JavaMailSenderImpl(); 
	    
	    //设定mail server 
	    senderImpl.setHost("smtp.sina.com"); 
	    //建立邮件消息,发送简单邮件和html邮件的区别 
	    MimeMessage mailMessage = senderImpl.createMimeMessage(); 
	    //注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，         
	    //multipart模式 为true时发送附件 可以设置html格式
	    MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8"); 
	    
	    //设置收件人，寄件人 
	    messageHelper.setTo("fenzaiway@qq.com");    
	    messageHelper.setFrom("fenzaiway@sina.com"); 
	    messageHelper.setSubject("测试邮件中上传附件!！"); 
	    //true 表示启动HTML格式的邮件 
	    messageHelper.setText("<html><head></head><body><h1>你好：附件中有学习资料！</h1></body></html>",true); 
	        
	    FileSystemResource file = new FileSystemResource(new File("e:"+java.io.File.separator+"使用教程.rar")); 
	    //这里的方法调用和插入图片是不同的。 
	    messageHelper.addAttachment(MimeUtility.encodeWord(file.getFilename()),file); 
	    
	    senderImpl.setUsername("fenzaiway@sina.com") ; // 根据自己的情况,设置username
	    senderImpl.setPassword("moway890727") ; // 根据自己的情况, 设置password
	    Properties prop = new Properties() ;
	    prop.put("mail.smtp.auth", "true") ; // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
	    prop.put("mail.smtp.timeout", "25000") ; 
	    senderImpl.setJavaMailProperties(prop); 
	    //发送邮件 
	    senderImpl.send(mailMessage); 
	    
	    System.out.println("邮件发送成功.."); 
//		 JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();    
//		  MimeMessage mailMessage = senderImpl.createMimeMessage();    
//		  //设置utf-8或GBK编码，否则邮件会有乱码    
//		  MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");    
//		  try {    
//			  messageHelper.setTo("fenzaiway@qq.com");//接受者       
//		   messageHelper.setFrom("fenzaiway@sina.com");//发送者    
//		   messageHelper.setSubject("测试邮件");//主题    
//		   //邮件内容，注意加参数true    
//		   messageHelper.setText("<html><head></head><body><h1>hello!!chao.wang</h1></body></html>",true);    
//		   //附件内容    
//		   messageHelper.addInline("a", new File("E:/xiezi.jpg"));    
//		   messageHelper.addInline("b", new File("E:/logo.png"));     
//		   File file=new File("E:/测试中文文件.rar");      
//		   // 这里的方法调用和插入图片是不同的，使用MimeUtility.encodeWord()来解决附件名称的中文问题    
//		   messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()), file);     
//		   senderImpl.send(mailMessage);    
//		  } catch (Exception e) {    
//		   e.printStackTrace();    
//		  }  
	} */
}
