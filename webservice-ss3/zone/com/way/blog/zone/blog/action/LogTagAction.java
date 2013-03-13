package com.way.blog.zone.blog.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.way.blog.base.action.BaseAction;
import com.way.blog.base.entity.TagSpace;
import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.manager.admin.entity.TagClickCount;
import com.way.blog.manager.admin.service.impl.TagClickCountServiceImpl;
import com.way.blog.manager.admin.service.impl.TagServiceImpl;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.LogTag;

@Controller("logTagAction")
@ParentPackage("struts-default")
@Namespace("/logtag")
public class LogTagAction extends BaseAction implements ModelDriven<LogTag>{

	@Autowired TagClickCountServiceImpl tagClickCountServiceImpl;
	@Autowired TagServiceImpl tagServiceImpl;
	@Autowired LogTag logTag;
	@Autowired TagClickCount tagClickCount;
	@Autowired Tag tag;
	private List<TagSpace> tagSpaceList;
	
	
	
	
	/**
	 * 根据用户传递的tagName来查询出与这个tag相关的文章
	 * @return
	 */
	@Action(value="findLogInfoByTagName",results={
			@Result(name="success",location="/WEB-INF/jsp/zone/tagfeed.jsp"),
			@Result(name="tagPage",location="/logtag/gotoFindTag.do", type="redirect"),
	})
	public String findLogInfoByTagName(){
		
		System.out.println("--------" + logTag.getTagName());
		
		if(null == logTag.getTagName() || "".equals(logTag.getTagName())){
			return "tagPage";
		}else{
			saveTagClick(logTag); ///保存每次用户通过点击标签进行查询的记录
			
		}
		
		return SUCCESS;
	}

	
	/**
	 * 进入发现更多标签内容页
	 * @return
	 */
	@Action(value="gotoFindTag",results={
			@Result(name="success",location="/tags/hot_tag.jsp"),
	})
	public String gotoFindTag(){
		
		/**
		 * 步骤：
		 * 1、获取系统热门标签下对用的热门用户的空间，取得Top-5，然后由系统定时的随机的显示加载
		 * 2、获取每个系统标签分类下对应的热门日志标签，在随机的获取与日志标签相关的用户空间
		 * 3、
		 */
		tagSpaceList =  tagServiceImpl.getTagSpaceList();

		
		return SUCCESS;
	}
	
	
	
	
	public void saveTagClick(LogTag myLogTag){
		tagClickCount.setIp(request.getRemoteAddr());
		tagClickCount.setTagName(myLogTag.getTagName());
		tagClickCount.setClickTime(MyFormatDate.getNowDate());
		if(null == myusername){
			tagClickCount.setUsername("游客");
		}else{
			tagClickCount.setUsername(myusername);
		}
		tagClickCountServiceImpl.save(tagClickCount);
	}

	
	public LogTag getModel() {
		// TODO Auto-generated method stub
		return logTag;
	}


	public List<TagSpace> getTagSpaceList() {
		return tagSpaceList;
	}


	public void setTagSpaceList(List<TagSpace> tagSpaceList) {
		this.tagSpaceList = tagSpaceList;
	}
	
	
	
	
}
