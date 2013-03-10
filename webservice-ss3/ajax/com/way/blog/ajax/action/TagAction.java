package com.way.blog.ajax.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.loginfo.service.LogInfoService;
import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.manager.admin.service.impl.TagServiceImpl;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.blog.service.impl.LogTagServiceImpl;
import com.way.blog.zone.entity.LogInfo;

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
	
	private List<Tag> otherTagList; ///换一批标签
	private List<Tag> userTagList;
	
	@Action(value="saveLogTag",results={
			@Result(name="success",type="json")
	})
	public String saveLogTag(){
		logInfo = logInfoServiceImpl.findById(logid);
		logTagServiceImpl.saveTag(logInfo, tags);
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
	
	
}
