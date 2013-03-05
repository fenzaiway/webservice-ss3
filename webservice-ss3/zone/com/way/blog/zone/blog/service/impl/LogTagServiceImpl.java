package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogTag;

@Service("logTagServiceImpl")
public class LogTagServiceImpl extends BaseGenericService<LogTag, Integer> {

	@Override
	@Resource(name="logTagDao")
	public void setDao(IHibernateGenericDao<LogTag, Serializable> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}
	
	
	/**
	 * 保存关键字
	 */
	public String saveTag(LogInfo logInfo,String myLogTags){
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
		return null;
	}
	
	
	
}
