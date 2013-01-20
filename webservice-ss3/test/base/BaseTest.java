package base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class BaseTest {
	protected ApplicationContext context = null;
	public static SessionFactory sessionFactory = null;     
	public static Session session = null;
	
	
	/*****************以下为定义的一些变量******************/
	protected static String email = "fenzaiway@qq.com";
	protected static String password = "way890727";
	protected static String username="fenzaiway";
	protected static String ip = "127.0.0.1";
	
	@Before
	public void mybefore(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		sessionFactory = (SessionFactory) context.getBean("sessionFactory");     
        session = sessionFactory.openSession();     
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session)); 
	}
	
	@After
	public void afterUser(){
		if(null != session){
			session.close();
		}
		if(null !=sessionFactory){
			sessionFactory.close();
		}
	}
}
