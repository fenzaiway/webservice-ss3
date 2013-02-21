package query;

import java.util.HashMap;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import org.junit.Test;

import cn.org.rapid_framework.page.Page;

import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.entity.LogInfo;

import base.BaseTest;

/**
 * 
 * project_name webservice-ss3
 *
 * package query
 *
 * @author fenzaiway
 *
 * @email fenzaiway@qq.com
 *
 * create_time 2013-1-25下午08:24:16
 *
 *
 */
public class LogInfoQueryTest extends BaseTest {
	
	private LogInfoServiceImpl logInfoServiceImpl;
	
	public void init(){
		logInfoServiceImpl = (LogInfoServiceImpl) this.context.getBean("logInfoServiceImpl");
	}
	
	
	/**
	 * use xsqlbuilder to query
	 */
	@Test
	public void query1(){
		this.init();
		String hql = "select t from LogInfo t where 1=1 "
					+"/~ t.logTitle = {logTitle} ~/"
					+"/~ t.username = {username} ~/"
					+"/~ t.logText like '%[logText]%' ~/";
		Map<String, String> filters = new HashMap<String, String>();
		filters.put("username", "fenzaiway");
		XsqlFilterResult result = getXsqlFilterResult(hql,filters);
		System.out.println("sql1===" + result.getXsql());
		XsqlBuilder builder = new XsqlBuilder();
		result = builder.generateHql(hql, filters);
		System.out.println("sql2===" + result.getXsql());
		System.out.println(logInfoServiceImpl.find("from LogInfo where username=?", "fenzaiway").size());
	}
	
	public XsqlFilterResult getXsqlFilterResult(String sql,Map filters){
		XsqlFilterResult result = new XsqlFilterResult(sql, filters);
		return result;
	}
}
