package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.Attention;
import com.way.blog.zone.entity.BlogZone;

@Service
public class AttentionServiceImpl extends BaseGenericService<Attention, Integer> {

	@Override
	@Resource(name="attentionDao")
	public void setDao(IHibernateGenericDao<Attention, Serializable> dao) {
		super.setDao(dao);
	}
	@Autowired private BlogZoneServiceImpl blogZoneServiceImpl;
	@Autowired private Attention attention;
	private List<Attention> attentionList;
	private BlogZone blogZone;
	
	public static final String HQL = "from Attention where 1=1 ";
	/**
	 * 加关注的时候，先判断用户是否以前已经关注过
	 * 如果用户以前关注过，那么现在重新更新以前的记录为已关注和新的关注时间
	 * 否则重新添加一条新的记录
	 * 根据当前用户的被关注用户完成用户之间的关注
	 * @param fromusername
	 * @param tousername
	 */
	public void addAttention(String fromusername, String tousername){
		//1、先判断用户以前是否关注过
		attentionList = this.find("from Attention where fromUserName=? and toUserName=?", new String[]{fromusername,tousername});
		if(attentionList!=null&&!attentionList.isEmpty()){
			attention = attentionList.get(0);
			attention.setAttentionTime(MyFormatDate.getNowDate());
			attention.setIsAttention(1);
			this.update(attention);
		}else{
			attention.setAttentionTime(MyFormatDate.getNowDate());
			attention.setFromUserName(fromusername);
			attention.setToUserName(tousername);
			attention.setIsAttention(1);///1、为关注0为取消关注
			blogZone = blogZoneServiceImpl.myFindByProperty("username", tousername);///取出被关注者的空间
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
			this.save(attention);
		}
	}
	
	/**
	 * 取消关注
	 * @return
	 */
	public void updateAttention(String fromusername, String tousername){
		attentionList = this.find("from Attention where fromUserName=? and toUserName=?", new String[]{fromusername,tousername});
		attention = attentionList.get(0);
		attention.setCancelAttentionTime(MyFormatDate.getNowDate());
		attention.setIsAttention(0);
		this.update(attention);
	}
	
	/**
	 * 判断用户是否关注
	 * @return
	 */
	public boolean isUserAttention(String fromusername, String tousername){
		boolean flag = false;
		attentionList = this.find("from Attention where fromUserName=? and toUserName=? and isAttention=1", new String[]{fromusername,tousername});
		if(null!=attentionList && !attentionList.isEmpty()){ ///如果能取到数据，就说明用户
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 获取用户关注列表
	 * @param fromusername
	 * @param tousernames
	 * @return
	 */
	public List<Attention> getAttentionList(String fromusername, String tousernames){
		String[] tousernamess = tousernames.split(",");
		List<Attention> myAttentionList = new ArrayList<Attention>();
		for(int i=0; i<tousernamess.length; i++){
			if(this.isUserAttention(fromusername, tousernamess[i])){
				myAttentionList.add(attentionList.get(0));
			}else{
				attention.setIsAttention(0);
				attention.setFromUserName(fromusername);
				myAttentionList.add(attention);	
			}
		}
		return myAttentionList;
	}
}
