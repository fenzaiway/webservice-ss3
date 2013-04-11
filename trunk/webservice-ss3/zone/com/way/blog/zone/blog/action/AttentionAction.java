package com.way.blog.zone.blog.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.zone.blog.service.impl.AttentionServiceImpl;
import com.way.blog.zone.entity.Attention;


@Controller("attentionAction")
@ParentPackage("interceptor")
@Namespace("/attention")
public class AttentionAction extends BaseAction {
	
	@Autowired private AttentionServiceImpl attentionServiceImpl;
	
	
	private List<Attention> attentionList = new ArrayList<Attention>();
	private List<Attention> fansList = new ArrayList<Attention>();
	
	/**
	 * 读取用户关注列表
	 * @return
	 */
	@Action(value="readAttentionList",results={
			@Result(name="success",location="/WEB-INF/jsp/zone/readAttention.jsp"),
	})
	public String readAttentionList(){
		attentionList = attentionServiceImpl.getAttentionList(myusername);
		return SUCCESS;
	}
	
	/**
	 * 读取用户粉丝列表
	 * @return
	 */
	@Action(value="readFansList",results={
			@Result(name="success",location="/WEB-INF/jsp/zone/readFans.jsp"),
	})
	public String readFansList(){
		fansList = attentionServiceImpl.getFansList(myusername);
		fansList = attentionServiceImpl.getFansList(fansList);
		return SUCCESS;
	}

	public void setAttentionList(List<Attention> attentionList) {
		this.attentionList = attentionList;
	}

	public void setFansList(List<Attention> fansList) {
		this.fansList = fansList;
	}

	public List<Attention> getAttentionList() {
		return attentionList;
	}

	public List<Attention> getFansList() {
		return fansList;
	}
	
	
}
