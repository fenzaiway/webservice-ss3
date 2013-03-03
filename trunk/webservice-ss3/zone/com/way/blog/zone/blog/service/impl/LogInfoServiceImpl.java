package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.util.MyFormatDate;
import com.way.blog.util.PaginationSupport;
import com.way.blog.util.RegexPatternUtil;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogTag;

@Service("logInfoServiceImpl")
public class LogInfoServiceImpl extends BaseGenericService<LogInfo, Integer> {

	@Override
	@Resource(name="logInfoDao")
	public void setDao(IHibernateGenericDao<LogInfo, Serializable> dao) {
		super.setDao(dao);
	}

	/**
	 * 根据当前用户加载该用户关注的日志数据
	 * @param username
	 * @return
	 */
	public PaginationSupport loadLogInfoDate(String username, int pageSize, int startIndex,Object... values){
		PaginationSupport paginationSupport;
		String hql = "from LogInfo where username=?";
		paginationSupport = this.findPageByQuery(hql, pageSize, startIndex, new String[]{username});
		return paginationSupport;
	}
	
	
	/**
	 * 发布新日志
	 */
	@Override
	public int save(LogInfo logInfo) {
		
		logInfo.setLogPublishTime(MyFormatDate.getNowDate());
		logInfo.setLogToTop(0); //设置置顶
		logInfo.setLogIsOriginal(0); ///日志发表类型
		logInfo.setLogContentStatus(1);
		logInfo.setDeleteStatue(0);
		return super.save(logInfo);
	}
	
	/**
	 * 改变logText的长度，方便列表显示
	 * @param logInfoList
	 * @return
	 */
	public List<LogInfo> changeLogInfoText(List<LogInfo> logInfoList){
		List<LogInfo> myLogInfoList = new ArrayList<LogInfo>();
		for(LogInfo logInfo : logInfoList){
			String logText = RegexPatternUtil.Html2Text(logInfo.getLogText());
			//System.out.println(logText);
			///只有长度超过200个字符的才转换
			if(logText.length() > 200){
				logInfo.setLogText(logText.substring(0,150));
			}
			System.out.println(logInfo.getLogText());
			System.out.println("\n\n--------------------------------");
			myLogInfoList.add(logInfo);
		}
		return myLogInfoList;
	}
	
	/**
	 * 根据日志ID取出原创日志
	 * @param id
	 * @return 原创日志
	 */
	public LogInfo findOriginalLogInfo(int id){
		LogInfo logInfo = null;
		logInfo = super.findById(id);
		if(null!=logInfo && 0!=logInfo.getId()){
			int sourceLogInfoId = logInfo.getSourceLogInfoId();
			if(0!=sourceLogInfoId){
				///不是原创日志，得再根据源日志ID取出原创日志
				logInfo = null;
				logInfo = super.findById(sourceLogInfoId);
			}
		}
		return logInfo;
	}

	@Override
	public void update(LogInfo logInfo) {
		logInfo.setLogPublishTime(MyFormatDate.getNowDate());
		logInfo.setLogToTop(0);
		logInfo.setLogContentStatus(1);
		super.update(logInfo);
	}
	
	/**
	 * 根据用户名取得用户发表过的标签
	 * @param username
	 * @return
	 */
	public List<LogTag> getUserLogInfoTagList(String username){
		/**
		 * 步骤
		 * 1、
		 */
		//List<LogTag> logTagList = new ArrayList<LogTag>();
		Set<LogTag> logTagSet = new HashSet<LogTag>();
		List<LogInfo> logInfoList = this.findByProperty("username", username);
		for (LogInfo logInfo : logInfoList) {
			if(null!=logInfo.getLogTags() && !logInfo.getLogTags().isEmpty()){
				//如果该篇日志的有关键字
				//logTagList.
				//logTagSet.add(e)
				for (LogTag logTag : logInfo.getLogTags()) {
					logTagSet.add(logTag);
				}
			}
		}
		
		return new ArrayList<LogTag>(logTagSet);
	}
}
