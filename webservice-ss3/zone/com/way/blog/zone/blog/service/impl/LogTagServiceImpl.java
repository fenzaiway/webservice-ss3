package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogTag;

@Service("logTagServiceImpl")
public class LogTagServiceImpl extends BaseGenericService<LogTag, Integer> {
	
	public static final String HQL = "from LogTag where 1=1 ";
	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	
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
		if(!"".endsWith(myLogTags) && null != myLogTags){ ///关键字不空字符串或者不为null的时候，才保存关键字
		////保存关键字
			String[] tags = myLogTags.split(",");////根据，分隔
			for(int i=0; i<tags.length; i++){
				////先根据关键字判断该关键字是否在tag表中,
				//后期为了扩充关键字，改为like的方式，然后在判断全出来的关键字是不是相等，
				//如果相等的话，就更新，否则将该关键字添加保存，同时相似的关键字也保存文章信息
				LogTag logTag = this.myFindByProperty("tagName", tags[i]);
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
		logTagDetail.setTagClickNum(tagClickCountServiceImpl.findByProperty("tagName", tagName).size());
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
}
