package com.way.blog.util.web;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
	@Autowired
	private MailSender sender;
	public void sendMail(){
		/*SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("673334698.qq.com");
		mail.setTo("fenzaiway@qq.com");
		mail.setReplyTo("fenzaiway@vip.qq.com");
		mail.setCc("fenzaiway@sina.com");
		mail.setSubject("注册成功");
		mail.setText("尊敬的fenzaiway用户，恭喜您在轻轻一点博客注册成功");
		sender.send(mail);*/
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost( "smtp.sina.com" );
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setFrom("fenzaiway@sina.com");
		mail.setTo("fenzaiway@qq.com");
		mail.setReplyTo("fenzaiway@vip.qq.com");
		mail.setCc("fenzaiway@sina.com");
		mail.setSubject("注册成功11");
		mail.setText("尊敬的fenzaiway用户，恭喜您在轻轻一点博客注册成功");
		sender.setUsername( "fenzaiway@sina.com" ) ;  //  根据自己的情况,设置username 
		sender.setPassword( "moway890727" ) ;  //  根据自己的情况, 设置password 
		 Properties prop  =   new  Properties() ;
		 prop.put( "mail.smtp.auth" ,  "true" ) ;  //  将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 
		 prop.put( "mail.smtp.timeout" ,  "25000" ) ; 
		 sender.setJavaMailProperties(prop);  
		sender.send(mail);
	}
}
