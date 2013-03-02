package com.way.blog.util.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
	//private static final String HOST = "smtp.sina.com";	 ///邮件服务器地址
	private static final String HOST = "smtp.qq.com";	 ///邮件服务器地址
	private static final String ISAUTHKEY = "mail.smtp.auth"; //将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 
	private static final String ISAUTHVALUE = "true";	//将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 
	private static final String TIMEOUTKEY = "mail.smtp.timeout";	//设置超时时间
	private static final String TIMEOUTVALUE = "25000";		//超时时间/s
	//private static final String SENDFROM = "fenzaiway@sina.com";
	private static final String SENDFROM = "fenzaiway@qq.com";
	//private static final String USERNAME = "fenzaiway@sina.com";
	private static final String USERNAME = "fenzaiway@qq.com";
	//private static final String PASSWORD = "moway890727";
	private static final String PASSWORD = "Ajavaway890905";
	private static final String REPLAYTO = "fenzaiway@vip.qq.com";	//回复地址
	private static final String CC = "fenzaiway@sina.com";	//抄送地址
	private static final String ENCODE = "UTF-8";
	
	private String username;
	private String code;
	private static final String SUBJECT = "感谢使用轻轻一点博客系统，请完成邮件验证";
	private static final String VERIFIHTML = "<html><head><title>请完成邮件验证</title></head><body>%{username}，您好！<br/></br/><br/>点击以下链接完成邮箱验证并激活在轻轻一点的帐号：<br/><a href='http://192.168.32.1:8090/ss3/register/userverify.do?code=%{code}'>http://192.168.32.1:8090/ss3/register/userverify.do?code=%{code}</a><br/>如无法点击，请将链接拷贝到浏览器地址栏中直接访问。</body></html>";
	
	private JavaMailSenderImpl sender = null;
	private MimeMessage mimeMessage = null;
	Properties prop = null;
	
	public Properties getProperties(){
		prop = new Properties();
		prop.put( ISAUTHKEY , ISAUTHVALUE ) ;  //  将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 
		prop.put( TIMEOUTKEY, TIMEOUTVALUE ) ; 
		return prop;
	}
	
	public JavaMailSenderImpl getJavaMailSenderImpl(){
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(HOST);
		javaMailSenderImpl.setUsername(USERNAME);
		javaMailSenderImpl.setPassword(PASSWORD);
		javaMailSenderImpl.setJavaMailProperties(this.getProperties());
		return javaMailSenderImpl;
	}
	
	public MimeMessage getMimeMessage(JavaMailSenderImpl javaMailSenderImpl){
		return javaMailSenderImpl.createMimeMessage();
	}
	
	public String getSentToAddress(List<Map<String,String>> sentToList) throws UnsupportedEncodingException{
		StringBuffer sbf = new StringBuffer();
		String key;
		String value;
		for (Map<String, String> map : sentToList) {
			Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				key = entry.getKey();
				value = entry.getValue();
				//sbf.append("\"").append(MimeUtility.encodeText(key)).append("\");
			}
		}
		return sbf.toString();
	}
	
	public String getCCAdderss(List<Map<String,String>> ccToList) throws UnsupportedEncodingException{
		StringBuffer sbf = new StringBuffer();
		if(null!=ccToList){
			String key;
			String value;
			for (Map<String, String> map : ccToList) {
				Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
				while(it.hasNext()){
					Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
					key = entry.getKey();
					value = entry.getValue();
					sbf.append(MimeUtility.encodeText(key)).append(" <").append(value).append(">").append(",");
					
				}
			}
		}
		
		return sbf.toString().substring(0,sbf.toString().length()-1);
		
	}
	
	/**
	 * 发送文本邮件
	 * @param sendTo 发送给谁
	 * @param subject 邮件的主题
	 * @param text 邮件内容
	 */
	public void sendMailByText(String sendTo,String subject, String text){	//发送文本型邮件
		sender = this.getJavaMailSenderImpl();
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(SENDFROM);
		mail.setTo(sendTo);
		mail.setReplyTo(REPLAYTO);
		mail.setCc(CC);
		mail.setSubject(subject);
		mail.setText(text);
		sender.send(mail);
	}
	
	/**
	 * 发送html型邮件
	 * @param sentToList 邮件接收用户列表
	 * @param subject 邮件主题
	 * @param htmlText 发送的html内容
	 * @param ccList 抄送人列表
	 */
	public void sendMailByHtml(List<String> sendToList,String subject,String htmlText,List<Map<String,String>> ccList){
		sender = this.getJavaMailSenderImpl();
		mimeMessage = sender.createMimeMessage();//this.getMimeMessage(sender);
		try {  
			// System.out.println("HTML脚本形式邮件正在发送...");  
			//设置utf-8或GBK编码，否则邮件会有乱码  
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,ENCODE); 
			// MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");  
			//设置发送人名片  
			helper.setFrom(new InternetAddress("\"" + MimeUtility.encodeText("轻轻一点") + "\" <"+SENDFROM+">"));  
			//设置收件人名片和地址  
			helper.setTo(sendToList.get(0));// 发送者  
			//helper.set
			// 邮件发送时间  
			helper.setSentDate(new Date());  
			//设置回复地址  
			helper.setReplyTo(new InternetAddress(REPLAYTO));  
			//设置抄送的名片和地址  
			//helper.setCc(InternetAddress.parse(this.getCCAdderss(ccList)));  
			//主题  
			helper.setSubject(subject);  
			// 邮件内容，注意加参数true，表示启用html格式  
			helper.setText(htmlText,true);  
			
           	//发送  
			sender.send(mimeMessage);  
		} catch (Exception e) {  
           e.printStackTrace();  
		}  
       //System.out.println("HTML脚本形式邮件发送成功！！！");  
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
