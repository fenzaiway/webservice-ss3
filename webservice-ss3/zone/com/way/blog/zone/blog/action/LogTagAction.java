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
import com.way.blog.base.entity.LogInfoData;
import com.way.blog.base.entity.SearchTagData;
import com.way.blog.base.entity.TagSpace;
import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.manager.admin.entity.TagClickCount;
import com.way.blog.manager.admin.service.impl.TagClickCountServiceImpl;
import com.way.blog.manager.admin.service.impl.TagServiceImpl;
import com.way.blog.util.MyFormatDate;
import com.way.blog.util.PaginationSupport;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogTag;

@Controller("logTagAction")
@ParentPackage("struts-default")
@Namespace("/logtag")
public class LogTagAction extends BaseAction implements ModelDriven<LogTag>{

	@Autowired TagClickCountServiceImpl tagClickCountServiceImpl;
	@Autowired TagServiceImpl tagServiceImpl;
	@Autowired LogInfoServiceImpl logInfoServiceImpl;
	@Autowired LogTag logTag;
	@Autowired TagClickCount tagClickCount;
	@Autowired Tag tag;
	private List<TagSpace> tagSpaceList;
	//private List<LogInfoData> logInfoDataList;
	private SearchTagData searchTagData;
	
	private int isUserSub; ///判断用户是否已经订阅一个标签 1表示已经订阅，0表示还没有订阅
	private int tagid; //标签Id
	
	
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
			//SELECT t FROM Teacher t join t.students s join s.books b where b.name = 'a'
			tag = tagServiceImpl.getTagByTagName(logTag.getTagName());
			
			if(null!=tag && 0!=tag.getId()){ ///说明用户点击的这个标签是一个系统标签
				
				if(null!=tag.getLogTags() && !tag.getLogTags().isEmpty()){
					String hql = "select DISTINCT l from LogInfo l join l.logTags lt where 1=1 and(";
					String whereStr = "";
					
					int i=0;
					for(LogTag lt : tag.getLogTags()){
						whereStr += " or lt.tagName='"+lt.getTagName()+"'";
					}
					whereStr += " or lt.tagName='"+logTag.getTagName()+"')";
					whereStr = whereStr.substring(3);
					hql += whereStr;
					paginationSupport = logInfoServiceImpl.findPageByQuery(hql, PaginationSupport.PAGESIZE, startIndex, new String[]{});
					paginationSupport = new PaginationSupport(paginationSupport.getItems(),paginationSupport.getItems().size(),PaginationSupport.PAGESIZE,startIndex);
				}else{
					paginationSupport = logInfoServiceImpl.findPageByQuery("select l from LogInfo l join l.logTags lt where lt.tagName=?", PaginationSupport.PAGESIZE, startIndex, logTag.getTagName());
				}
			}else{
				paginationSupport = logInfoServiceImpl.findPageByQuery("select l from LogInfo l join l.logTags lt where lt.tagName=?", PaginationSupport.PAGESIZE, startIndex, logTag.getTagName());
			}
			
			//paginationSupport = logInfoServiceImpl.findPageByQuery(PaginationSupport.PAGESIZE, startIndex, logTag.getTagName());
			/*paginationSupport = logInfoServiceImpl.findPageByQuery("from LogTag lt join lt.logInfos l where lt.tagName=?", 5, 0, logTag.getTagName());
			*/
			/**
			 * 这个位置没有采用延迟加载，采用延迟加载会出现Session close 而导致取不到数据。
			 * 后期修复版的时候看这么避免这个问题
			 */
			searchTagData = logInfoServiceImpl.getSearchTagData(paginationSupport, myusername);
			
			///判断用户是否已经订阅这个标签,只有当用户登录的时候才会做这个判断
			if(null != myusername || !"".equals(myusername)){
				tagid = tagServiceImpl.isUserSubTagName(myusername,logTag.getTagName());
				if(0 != tagid){
					isUserSub = 1;
				}else{
					isUserSub = 0;
				}
			}
			
			
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


	public SearchTagData getSearchTagData() {
		return searchTagData;
	}


	public void setSearchTagData(SearchTagData searchTagData) {
		this.searchTagData = searchTagData;
	}

	public int getIsUserSub() {
		return isUserSub;
	}

	public void setIsUserSub(int isUserSub) {
		this.isUserSub = isUserSub;
	}


	public int getTagid() {
		return tagid;
	}


	public void setTagid(int tagid) {
		this.tagid = tagid;
	}
	
	
}
