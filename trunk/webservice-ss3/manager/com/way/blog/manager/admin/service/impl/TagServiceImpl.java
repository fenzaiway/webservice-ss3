package com.way.blog.manager.admin.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.service.impl.UserLoginServiceImpl;

@Service("tagServiceImpl")
public class TagServiceImpl extends BaseGenericService<Tag, Integer> {
	@Autowired
	private UserLoginServiceImpl userLoginServiceImpl;
	@Autowired
	private UserLogin userLogin;
	@Autowired
	private Tag tag;
	
	private static final int SIZE = 3;///返回的的另一批tag的数量
	private List<Tag> tags;
	@Override
	@Resource(name="tagDao")
	public void setDao(IHibernateGenericDao<Tag, Serializable> dao) {
		super.setDao(dao);
	}
	
	///用户取消订阅标签
	public void deleteUserSubTag(int tagid){
		tag = this.findById(tagid);
		tag.setUserLogins(null);
		this.update(tag);
	}
	
	/**
	 * 保存用户订阅的标签
	 * @param tagid
	 * @param username
	 */
	public void saveUserSubTag(int tagid, String username){
		tag = this.findById(tagid);
		userLogin = userLoginServiceImpl.myFindByProperty("username", username);
		///设置双向关联
		userLogin.getTags().add(tag);
		tag.getUserLogins().add(userLogin);
		this.save(tag);
	}
	
	/**
	 * 加载用户已经订阅的标签
	 * @param username
	 * @return
	 */
	public List<Tag> loadUserSubTagList(String username){
		tags = this.find("from Tag t join  t.userLogins as u where u.username=?", new String[]{username});
		return tags;
	}
	
	/**
	 * 根据用户加载另一批用户还没有订阅的标签
	 * 查询方式：from Teacher t left join fetch t.course c where t.sex=’女’ and c.name=’语文’
	 * @param username 用户
	 * @return
	 */
	public List<Tag> loadOtherTag(String username){
		
		//userLogin = userLoginServiceImpl.myFindByProperty("username", username);
		//tags = this.find("from Tag t left join fetch t.userLogins u where u.username!=?", new String[]{username});
		//tags = ;
		int[] ids = getIds(nologUserSubTagList(username));
		//tag = 
		String hql = "from Tag t where t.id in("+getIntId(ids)+")";
		tags = this.find(hql, new Object[]{});
		System.out.println(tags.size());
		return tags;
	}
	
	/**
	 * 根据用户名取得所有用户还没订阅的标签
	 * @param username
	 * @return
	 */
	public List<Tag> nologUserSubTagList(String username){
		List<Tag> tags;
		UserLogin userLogin = userLoginServiceImpl.myFindByProperty("username", username);
		if(null!=userLogin.getTags() && !userLogin.getTags().isEmpty()){
			String ids = getIntId(userLogin.getTags());
			String hql = "from Tag t where t.id not in("+ids+")";
			tags = this.find(hql, new Object[]{});
		}else{
			tags = this.loadAll();
		}
		return tags;
	}
	
	/**
	 * 拼接in(id)形式
	 * @param tags
	 * @return
	 */
	public String getIntId(Set<Tag> tags){
		String ids = "";
		List<Integer> idList = new ArrayList<Integer>();
		List<Tag> tagList = new ArrayList<Tag>(tags);
		for(int i=0; i<tagList.size(); i++){
			//Tag tag = 
			idList.add(tagList.get(i).getId());
		}
		ids = idList.toString().substring(1,idList.toString().length()-1);
		return ids;
	}
	
	public String getIntId(int[] ids){
		String id = "";
		for(int i = 0; i<ids.length; i++){
			id+=ids[i];
			id+=",";
		}
		id = id.substring(0, id.length()-1);
		return id;
	}
	
	/**
	 * 根据传递进来的tags来获取随机的3个Id
	 * @param tags
	 * @return
	 */
	public int[] getIds(List<Tag> tags){
		////取出tag中所有的Id
		int length = tags.size();//用户还没有关注标签的数量
		int [] returnIds ;
		int [] allIds = new int[length]; ////全部id
	
		for(int i=0; i<length; i++){
			allIds[i] = tags.get(i).getId();
		}
		
		//先判断length的长度是不是length>=SIZE
		if(SIZE>=length){
			returnIds = new int[length]; ////要返回的id
			for(int i=0; i<length;i++){
				returnIds[i] = allIds[i];
			}
		}else{
			returnIds = new int[SIZE]; ////要返回的id
			///生成随机的SIZE个数
			for(int i=0; i<SIZE; i++){
				int j = new Random().nextInt(length);
				returnIds[i] = allIds[j];
			}
		}
		return returnIds;
	}
}
