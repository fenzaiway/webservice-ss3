package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.entity.LogTagDetail;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.manager.admin.service.impl.TagClickCountServiceImpl;
import com.way.blog.manager.admin.service.impl.TagServiceImpl;
import com.way.blog.util.MyFormatDate;
import com.way.blog.util.PaginationSupport;
import com.way.blog.zone.entity.LogComment;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogTag;

@Service("logTagServiceImpl")
public class LogTagServiceImpl extends BaseGenericService<LogTag, Integer> {
	
	public static final String HQL = "from LogTag where 1=1 ";
	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	@Autowired private TagServiceImpl tagServiceImpl;
	
	@Override
	@Resource(name="logTagDao")
	public void setDao(IHibernateGenericDao<LogTag, Serializable> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}
	
	@Autowired private LogTag logTag;
	@Autowired private TagClickCountServiceImpl  tagClickCountServiceImpl;
	/**
	 * 保存关键字
	 */
	public String saveTag(LogInfo logInfo,String myLogTags){
		if(isLogTagLegal(myLogTags)){ ///关键字不空字符串或者不为null的时候，才保存关键字
		////保存关键字
			String[] tags = myLogTags.split(",");////根据，分隔
			for(int i=0; i<tags.length; i++){
				////先根据关键字判断该关键字是否在tag表中,
				//后期为了扩充关键字，改为like的方式，然后在判断全出来的关键字是不是相等，
				//如果相等的话，就更新，否则将该关键字添加保存，同时相似的关键字也保存文章信息
				LogTag logTag = this.myFindByProperty("tagName", tags[i].trim());
				if(null!=logTag && null != logTag.getTagName()){	////tag表中已经存在该关键字
					
					if(null !=logTag.getLogInfos() && !logTag.getLogInfos().isEmpty()){
						logTag.getLogInfos().add(logInfo);
						Set<LogTag> tagset = new HashSet<LogTag>();
						tagset.add(logTag);
						logInfo.setLogTags(tagset);
						this.save(logTag);
					}else{
						Set<LogInfo> logInfoSet = new HashSet<LogInfo>();
						logInfoSet.add(logInfo);
						logTag.setLogInfos(logInfoSet);
						Set<LogTag> tagset = new HashSet<LogTag>();
						tagset.add(logTag);
						logInfo.setLogTags(tagset);
						this.save(logTag);
					}
					
				}else{
					logTag = new LogTag();
					logTag.setTagCreateTime(MyFormatDate.getNowDate());
					logTag.setTagName(tags[i]);
					///设置双向关联
					Set<LogInfo> logInfoSet = new HashSet<LogInfo>();
					logInfoSet.add(logInfo);
					logTag.setLogInfos(logInfoSet);
					Set<LogTag> tagset = new HashSet<LogTag>();
					tagset.add(logTag);
					logInfo.setLogTags(tagset);
					this.save(logTag);
				}
				
			}
		}
		
		return null;
	}
	
	/**
	 * 验证传递过来的日志标签是否合法
	 * @param logTags
	 * @return
	 */
	public boolean isLogTagLegal(String logTags){
		boolean flag = false;
		if(!"".equals(logTags) && null != logTags){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 更新系统标签和日志标签之间的关系
	 * @param tagid
	 * @param logtags
	 * @return
	 */
	public String updateLogTagToTag(int tagid, String logTags){
		if(isLogTagLegal(logTags)){
			String logTagNames[] = logTags.split(",");
			Tag tag = tagServiceImpl.findById(tagid);
			int length = logTagNames.length;
			Set<LogTag> logTagSet = new HashSet<LogTag>();
			Set<Tag> tagSet;
			for(int i=0; i<length; i++){
				logTag = this.myFindByProperty("tagName", logTagNames[i].trim());
				if(null!=logTag.getTags() && !logTag.getTags().isEmpty()){
					if(!logTag.getTags().contains(tag)){ ///如果该标签已经包含了系统标签，那么就不更新两者直接的关系了
						logTag.getTags().add(tag);
						logTagSet.add(logTag);
					}
				}else{
					tagSet = new HashSet<Tag>();
					tagSet.add(tag);
					logTag.setTags(tagSet);
				}
				
			}
			if(null!=tag.getLogTags() && !tag.getLogTags().isEmpty()){ //这个系统标签下对应有日志标签
				//tag.getLogTags().add(e)
				for(LogTag lt : logTagSet){
					tag.getLogTags().add(lt);
				}
			}else{
				tag.setLogTags(logTagSet);
			}
			
			tagServiceImpl.update(tag);
		}
		return null;
	}
	
	/**
	 * 根据标签的名称获取标签的详细信息
	 * @param tagName
	 * @return
	 */
	public LogTagDetail getLogTagDetailByTagId(int tagId){
		LogTagDetail logTagDetail = new LogTagDetail();
		
		//、logTag = this.myFindByProperty("tagName", tagName);
		logTag = findById(tagId);
		String tagName = logTag.getTagName();
		logTagDetail.setTagName(tagName);
		logTagDetail.setTagCreateTime(logTag.getTagCreateTime());
		logTagDetail.setTagClickNum(tagClickCountServiceImpl.getTagClickNum(tagName));
		logTagDetail.setLogNum(logTag.getLogInfos().size());
		logTagDetail.setSysTag(getSysTag(logTag));
		return logTagDetail;
	}
	
	/**
	 * 根据标签名保存日志标签
	 * @param tagName
	 */
	public void save(String tagName){
		logTag.setTagName(tagName);
		logTag.setTagCreateTime(MyFormatDate.getNowDate());
		save(logTag);
	}
	
	/**
	 * 获取标签所属的对用的系统标签的名称
	 * @param logTag
	 * @return
	 */
	public String getSysTag(LogTag logTag){
		StringBuffer sb = new StringBuffer();
		for(Tag tag : logTag.getTags()){
			sb.append(tag.getTagName()).append(",");
		}
		return sb.toString();
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
		List<LogInfo> logInfoList = logInfoServiceImpl.findByProperty("username", username);
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
	
	/**
	 * 根据tagName 获取到这个tagName下日志的Id 如:1,2,3,
	 * @param tagName
	 * @return
	 */
	public String getLogInfoIds(String tagName){
		logTag = myFindByProperty("tagName", tagName);
		StringBuffer sb = new StringBuffer();
		if(null!=logTag && !logTag.getLogInfos().isEmpty()){
			for(LogInfo logInfo : logTag.getLogInfos()){
				sb.append(logInfo.getId()).append(",");
			}
		}
		String ids = sb.toString();
		ids = ids.substring(0,ids.length()-1);
		return ids;
	}
	
	public PaginationSupport search(int pageSize, int startIndex, String logTagName){
		String hql = HQL+" and tagName like ?";
		logTagName = "%"+logTagName+"%";
		return this.findPageByQuery(hql, pageSize, startIndex, new String[]{logTagName});
	}
	
	/**
	 * 根据用户订阅的标签取出标签对应的内容
	 * @param tagList
	 * @return
	 */
	public List<LogInfo> getLogInfoList(List<Tag> tagList){
		List<LogInfo> logInfoList = new ArrayList<LogInfo>();
		for(Tag tag : tagList){
			logTag = this.myFindByProperty("tagName", tag.getTagName());
			if(null!=logTag && !logTag.getLogInfos().isEmpty()){
				for(LogInfo li : logTag.getLogInfos()){
					logInfoList.add(li);
				}
			}
			if(null!=tag.getLogTags() && !tag.getLogTags().isEmpty()){
				for(LogTag lt : tag.getLogTags()){
					if(null!=lt.getLogInfos() && !lt.getLogInfos().isEmpty()){
						for(LogInfo li : lt.getLogInfos()){
							logInfoList.add(li);
						}
					}
				}
			}
			
		}
		////去掉List中重复的内容
		Set<LogInfo> logInfoSet = new HashSet<LogInfo>(logInfoList);
		logInfoList.clear();
		logInfoList.addAll(logInfoSet);
		
		return logInfoList;
	}
	
	
	
}
