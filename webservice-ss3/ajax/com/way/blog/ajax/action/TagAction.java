package com.way.blog.ajax.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.base.entity.ReturnStatus;
import com.way.blog.loginfo.service.LogInfoService;
import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.manager.admin.service.impl.TagServiceImpl;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.blog.service.impl.LogTagServiceImpl;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogTag;

import demo1.ReturnCompleteJson;
import demo1.ReturnData;

/**
 * 系统标签控制器
 * project_name webservice-ss3
 *
 * package com.way.blog.ajax.action
 *
 * @author fenzaiway
 *
 * @email fenzaiway@qq.com
 *
 * create_time 2013-2-24下午01:25:02
 *
 *
 */
@Controller("tagAction")
@ParentPackage("json-default")
@Namespace("/ajax/tag")
public class TagAction extends BaseAction {
	@Autowired
	private TagServiceImpl tagServiceImpl;
	@Autowired private LogTagServiceImpl logTagServiceImpl;
	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	@Autowired LogInfo logInfo;
	
	private String username;
	
	private int tagid;
	private String tags;  ////用户传递过来的标签
	private int logid;    ////日志Id
	
	private String keyword; ////自动下拉提示关键字
	
	
	private List<Tag> otherTagList; ///换一批标签
	private List<Tag> userTagList;
	private List<LogTag> logTagList; ///日志标签集合
	private String myTagName; ///标签名称
	
	/**
	 * 保存日志标签和系统标签的关联关系
	 * @return
	 */
	@Action(value="saveLogTag",results={
			@Result(name="success",type="json")
	})
	public String saveLogTag(){
		logInfo = logInfoServiceImpl.findById(logid);
		logTagServiceImpl.saveTag(logInfo, tags);
		return null;
	}
	
	/**
	 * 保存用户订阅经过页面经过查询得到的表恰
	 * @return
	 */
	@Action(value="savePageUserSubTag",results={
			@Result(name="success",type="json")
	})
	public String savePageUserSubTag(){
		System.out.println("----tagName --- " + myTagName);
		tagid = tagServiceImpl.savePageUserSubTag(username, myTagName);
		ReturnStatus rs = new ReturnStatus();
		rs.setStatus(tagid);
		this.returnJsonByObject(rs);
		return null;
	}
	
	
	/**
	 * 换另一批标签（每次加载SIZE个）
	 * 取出来的数据是用户还没有关注的
	 * @return
	 */
	@Action(value="changeTag",results={
			@Result(name="success",type="json")
	})
	public String changeTags(){
		////根据用户加载
		otherTagList = tagServiceImpl.loadOtherTag(username);
		this.returnJsonByObjectOfExpose(otherTagList);
		return null;
	}
	
	/**
	 * 根据当前用户加载用户订阅的标签
	 * @return
	 */
	@Action(value="loadUserTags",results={
			@Result(name="success",type="json")
	})
	public String loadUserSubTagList(){
		userTagList = tagServiceImpl.loadUserSubTagList(username);
		this.returnJsonByObjectOfExpose(userTagList);
		return null;
	}
	
	/**
	 * 保存用户订阅的标签
	 * @return
	 */
	@Action(value="saveTag",results={
			@Result(name="success",type="json")
	})
	public String saveUserSubTag(){
		tagServiceImpl.saveUserSubTag(tagid,username);
		//System.out.println(tagid);
		return null;
	}
	
	/**
	 * 用户取消订阅标签
	 * @return
	 */
	@Action(value="cancelUserSubTag",results={
			@Result(name="success",type="json")
	})
	public String deleteUserSubTag(){
		tagServiceImpl.deleteUserSubTag(tagid);
		return null;
	}

	/**
	 * 自动标签下拉提示
	 * @return
	 */
	@Action(value="tagComplete",results={
			@Result(name="success",type="json")
	})
	public String tagComplete(){
		System.out.println("keyword==" + keyword);
		keyword = "%"+keyword+"%";
		logTagList = logTagServiceImpl.find(LogTagServiceImpl.HQL+" and tagName like ?", keyword);
		ReturnCompleteJson returnCompleteJson = new ReturnCompleteJson();
		List<ReturnData> dataList=new ArrayList<ReturnData>();
		ReturnData returnData = null;
		if(null!=logTagList && !logTagList.isEmpty()){
			for (LogTag logTag : logTagList) {
				returnData = new ReturnData();
				returnData.setTitle(logTag.getTagName());
				dataList.add(returnData);
			}
		}else{
			returnData = new ReturnData();
			returnData.setTitle("");
			dataList.add(returnData);
		}
		returnCompleteJson.setData(dataList);
		this.returnJsonByObject(returnCompleteJson);
		return null;
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTagid() {
		return tagid;
	}

	public void setTagid(int tagid) {
		this.tagid = tagid;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getLogid() {
		return logid;
	}

	public void setLogid(int logid) {
		this.logid = logid;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<LogTag> getLogTagList() {
		return logTagList;
	}

	public void setLogTagList(List<LogTag> logTagList) {
		this.logTagList = logTagList;
	}

	public String getMyTagName() {
		return myTagName;
	}

	public void setMyTagName(String myTagName) {
		this.myTagName = myTagName;
	}
	
	
}
