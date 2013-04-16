package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.LogDraft;

@Service
public class LogDraftServiceImpl extends BaseGenericService<LogDraft, Integer> {

	@Override
	@Resource(name="logDraftDao")
	public void setDao(IHibernateGenericDao<LogDraft, Serializable> dao) {
		super.setDao(dao);
	}

	public static final String HQL = "from LogDraft where 1=1";
	
	@Autowired private LogDraft logDraft;
	private List<LogDraft> logDraftList = null;
	
	
	/**
	 * 判断是否已经保存过草稿
	 * @param username
	 * @param title
	 * @return
	 */
	public boolean hasSaveDraft(String username, String title){
		boolean flag = false;
		if(0!=getDraftId(username,title)){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 取得草稿箱Id
	 * @param username
	 * @param title
	 * @return
	 */
	public int getDraftId(String username, String title){
		String hql = HQL+" and username=? and logTitle=? order by saveTime desc";
		logDraftList = find(hql, new String[]{username,title});
		if(!isListTEmpty(logDraftList)){
			return logDraftList.get(0).getId();
		}
		return 0;
	}
	
	/**
	 * 保存草稿
	 * @param username
	 * @param title
	 * @param content
	 * @return
	 */
	public int save(String username, String title,String content){
		logDraft.setLogTitle(title);
		logDraft.setUsername(username);
		logDraft.setLogText(content);
		logDraft.setSaveTime(MyFormatDate.getNowDate());
		return this.save(logDraft);
	}
	
	/**
	 * 根据草稿id更新
	 * @param content
	 * @param draftId
	 * @return
	 */
	public void update(String content,int draftId){
		logDraft = findById(draftId);
		logDraft.setLogText(content);
		logDraft.setSaveTime(MyFormatDate.getNowDate());
		this.update(logDraft);
	}
}
