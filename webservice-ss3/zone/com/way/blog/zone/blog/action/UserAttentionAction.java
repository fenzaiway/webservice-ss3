package com.way.blog.zone.blog.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.blog.service.impl.AttentionServiceImpl;
import com.way.blog.zone.blog.service.impl.BlogZoneServiceImpl;
import com.way.blog.zone.entity.Attention;
import com.way.blog.zone.entity.BlogZone;

@Controller("userAttentionAction")
@ParentPackage("interceptor")
@Namespace("/attention")
public class UserAttentionAction extends BaseAction {

	@Autowired
	private AttentionServiceImpl attentionServiceImpl;
	@Autowired
	private BlogZoneServiceImpl blogZoneServiceImpl;
	@Autowired
	private Attention attention;
	private BlogZone blogZone;
	
	private String toUserName;
	private String toUserNames;
	private List<Attention> attentionList;
	
	
	
	/**
	 * 加关注的时候，先判断用户是否以前已经关注过
	 * 如果用户以前关注过，那么现在重新更新以前的记录为已关注和新的关注时间
	 * 否则重新添加一条新的记录
	 * @return
	 */
	@Action(value="addAttention",results={
			@Result(type="json")
	})
	public String addAttention(){
		//1、先判断用户以前是否关注过
		attentionList = attentionServiceImpl.find("from Attention where fromUserName=? and toUserName=?", new String[]{myusername,toUserName});
		if(attentionList!=null&&!attentionList.isEmpty()){
			attention = attentionList.get(0);
			attention.setAttentionTime(MyFormatDate.getNowDate());
			attention.setIsAttention(1);
			attentionServiceImpl.update(attention);
		}else{
			attention.setAttentionTime(MyFormatDate.getNowDate());
			attention.setFromUserName(myusername);
			attention.setToUserName(toUserName);
			attention.setIsAttention(1);///1、为关注0为取消关注
			blogZone = blogZoneServiceImpl.myFindByProperty("username", toUserName);///取出被关注者的空间
			////设置双向关联
			attention.setBlogZone(blogZone);
			
			if(blogZone.getAttentions()==null||blogZone.getAttentions().isEmpty()){
				///还没有被关注
				Set<Attention> attentions = new HashSet<Attention>();
				attentions.add(attention);
				blogZone.setAttentions(attentions);
			}else{
				blogZone.getAttentions().add(attention);
			}
			///blogZone.setAttentions(attentions)
			attentionServiceImpl.save(attention);
		}
		
		return null;
	}
	
	/**
	 * 取消关注
	 * @return
	 */
	@Action(value="updateAttention",results={
			@Result(type="json")
	})
	public String updateAttention(){
		attentionList = attentionServiceImpl.find("from Attention where fromUserName=? and toUserName=?", new String[]{myusername,toUserName});
		attention = attentionList.get(0);
		attention.setCancelAttentionTime(MyFormatDate.getNowDate());
		attention.setIsAttention(0);
		attentionServiceImpl.update(attention);
		return null;
	}
	
	@Action(value="isAttention",results={
			@Result(type="json")
	})
	public String loadUserIsAttention(){
		
		String[] toUserNamess = toUserNames.split(",");
		List<Attention> myAttentionList = new ArrayList<Attention>();
		for(int i=0; i<toUserNamess.length; i++){
			attentionList = attentionServiceImpl.find("from Attention where fromUserName=? and toUserName=?", new String[]{myusername,toUserNamess[i]});
			if(null!=attentionList && !attentionList.isEmpty()){
				myAttentionList.add(attentionList.get(0));
			}else{
				attention.setIsAttention(0);
				attention.setFromUserName(myusername);
				myAttentionList.add(attention);
			}
		}
		this.returnJsonByObjectOfExpose(myAttentionList);
		
		
		
		
//		attentionList = attentionServiceImpl.find("from Attention where fromUserName=? and toUserName=?", new String[]{myusername,toUserName});
//		System.out.println("333" + attentionList);
//		if(null!=attentionList && !attentionList.isEmpty()){
//			attention = attentionList.get(0);
//			this.returnJsonByObjectOfExpose(attention);
//			System.out.println("1111111111" + toUserName);
//		}else{
//			attention.setIsAttention(0);
//			System.out.println("222222222222" + toUserName);
//			this.returnJsonByObject(attention);
//		}
		return null;
	}
	
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getToUserNames() {
		return toUserNames;
	}

	public void setToUserNames(String toUserNames) {
		this.toUserNames = toUserNames;
	}
	
	
}
