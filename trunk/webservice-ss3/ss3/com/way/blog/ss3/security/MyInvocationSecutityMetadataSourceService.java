package com.way.blog.ss3.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.way.blog.util.web.AntUrlPathMatcher;
import com.way.blog.util.web.UrlMatcher;

public class MyInvocationSecutityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource {

	private Map<String,Collection<ConfigAttribute>> resourceMap; ////资源权限map
	
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	
	public MyInvocationSecutityMetadataSourceService() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		////类在初始化的时候，加载所有的资源权限关系表
		loadResourceDefine();
	}

	private void loadResourceDefine() {
		////
		System.out.println("开始加载资源权限关系。。。。。");
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
	
		String username = "";
		String sql = "";
		
		//在web启动的时候，提取所有的权限
		sql = "select authorityName from MyAuthority";
		
		List<String> query = session.createQuery(sql).list();
		
		/**
		 * 资源权限map,资源为key,
		 * 一个资源对应多个访问权限，所有value为访问权限集合
		 */
		//resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		
		for(String auth : query){
			ConfigAttribute ca = new SecurityConfig(auth);
			System.out.println("=====" + auth);
			/**
			 * 根据权限获取对应的资源
			 */
			
			String hql = "select a.url from MyResources a inner join a.myAuthoritys as b where  b.authorityName='" + auth + "'";
			List<String> query1 = session.createQuery(hql).list();
			System.out.println("---------------------------------");
			for(String res : query1){
				System.out.println("-------------res-----" + res);
				/*
			     * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
			     * fenzaiway
			     */
				
				if(resourceMap.containsKey(res)){
					Collection<ConfigAttribute> value = resourceMap.get(res);
					value.add(ca);
				}else{
					Collection<ConfigAttribute> attrs = new ArrayList<ConfigAttribute>();
					attrs.add(ca);
					resourceMap.put(res, attrs);
				}
			}
		}
	}
	
	/**
	 * 得到所有权限的集合
	 */
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		System.out.println("---------====1111====11==1=1=");
		Set<ConfigAttribute> set = new HashSet<ConfigAttribute>();
		  for (Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
			  System.out.println("22222222222222");
			  set.addAll(entry.getValue());
		  }
		  return set;
		//return null;
	}

	////根据用户请求的URL取出这个URL的访问权限
	/**
	 * object为用户请求的URL
	 */
	
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		
		if(null == resourceMap){
			loadResourceDefine();
		}
		System.out.println("-------size--------" + resourceMap.entrySet().size());
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
			System.out.println("----request-url==- " + url);
			String keyStr = entry.getKey();
			System.out.println("keyStr==" + keyStr);
			
			System.out.println(keyStr.equals(url)+"====================");
            if (urlMatcher.pathMatchesUrl(keyStr, url)) {
                return entry.getValue();
            }
        }
		return null;
		//return resourceMap.get(object);
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	
}

/**
 * 
 * @author fenzaiway
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * //1 加载资源与权限的对应关系 
 */
//@Component("securityMetadataSource")
/*
public class MyInvocationSecutityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource {

	private Map<String,Collection<ConfigAttribute>> resourceMap; ////资源权限map
	
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	
	public MyInvocationSecutityMetadataSourceService() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		////类在初始化的时候，加载所有的资源权限关系表
		loadResourceDefine();
	}

	private void loadResourceDefine() {
		////
		System.out.println("开始加载资源权限关系。。。。。");
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
	
		String username = "";
		String sql = "";
		
		//在web启动的时候，提取所有的权限
		sql = "select roleName from Roles";
		
		List<String> query = session.createQuery(sql).list();
		
		/**
		 * 资源权限map,资源为key,
		 * 一个资源对应多个访问权限，所有value为访问权限集合
		 */
		//resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		/*
		for(String auth : query){
			ConfigAttribute ca = new SecurityConfig(auth);
			System.out.println("=====" + auth);
			/**
			 * 根据权限获取对应的资源
			 */
			/*
			String hql = "select a.resourceName from Resources a inner join a.roles as b where  b.roleName='" + auth + "'";
			List<String> query1 = session.createQuery(hql).list();
			System.out.println("---------------------------------");
			for(String res : query1){
				
				/*
			     * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
			     * fenzaiway
			     */
				/*
				if(resourceMap.containsKey(res)){
					Collection<ConfigAttribute> value = resourceMap.get(res);
					value.add(ca);
				}else{
					Collection<ConfigAttribute> attrs = new ArrayList<ConfigAttribute>();
					attrs.add(ca);
					resourceMap.put(res, attrs);
				}
			}
		}
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	////根据用户请求的URL取出这个URL的访问权限
	/**
	 * object为用户请求的URL
	 */
	/*
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		
		if(null == resourceMap){
			loadResourceDefine();
		}

		for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            if (urlMatcher.pathMatchesUrl(entry.getKey(), url)) {
                return entry.getValue();
            }
        }
		return null;
		//return resourceMap.get(object);
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}*/
 