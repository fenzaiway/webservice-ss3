package com.way.blog.ajax.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.manager.admin.service.impl.TagServiceImpl;

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
	
	private String username;
	
	private int tagid;
	
	private List<Tag> otherTagList; ///换一批标签
	private List<Tag> userTagList;
	
	/**
	 * 换另一批标签（每次加载3个）
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
	 * 保存用户订阅的标签
	 * @return
	 */
	@Action(value="saveTag",results={
			@Result(name="success",type="json")
	})
	public String saveUserSubTag(){
		
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
	
	
}