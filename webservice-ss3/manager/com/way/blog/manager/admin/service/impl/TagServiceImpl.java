package com.way.blog.manager.admin.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.entity.Space;
import com.way.blog.base.entity.TagSpace;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.service.impl.UserHeadImgServiceImpl;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.util.MyFormatDate;
import com.way.blog.util.NumberUtil;
import com.way.blog.zone.blog.service.impl.BlogZoneServiceImpl;
import com.way.blog.zone.blog.service.impl.LogTagServiceImpl;
import com.way.blog.zone.entity.BlogZone;
import com.way.blog.zone.entity.LogTag;

@Service("tagServiceImpl")
public class TagServiceImpl extends BaseGenericService<Tag, Integer> {
	@Autowired private UserLoginServiceImpl userLoginServiceImpl;
	@Autowired private LogTagServiceImpl logTagServiceImpl;
	@Autowired private BlogZoneServiceImpl blogZoneServiceImpl;
	@Autowired private UserHeadImgServiceImpl userHeadImgServiceImpl;
	@Autowired private UserLogin userLogin;
	@Autowired private Tag tag;
	@Autowired private LogTag logTag;
	
	public static final String HQL = "from Tag where 1=1 ";
	
	private static final int SIZE = 3;///返回的的另一批tag的数量
	private List<Tag> tags;
	private List<LogTag> logTagList;
	@Override
	@Resource(name="tagDao")
	public void setDao(IHibernateGenericDao<Tag, Serializable> dao) {
		super.setDao(dao);
	}
	
	
	
	/**
	 * 保存用户通过页面查询订阅的标签
	 * @param username 用户名
	 * @param tagName 标签名
	 * @return
	 */
	public int savePageUserSubTag(String username, String tagName){
		/**
		 * 步骤:
		 * 1、先判断这个标签名在标签表中是不是已经存在
		 * 2、如果还没有存在，则先保存这个标签，并设置这个标签不是系统标签
		 * 3、设置标签和用户之间的双向关联关系，并更新两者之间的关系
		 */
		//1、这个标签还没存在
		int tagid = 0;
		if(!isTagExits(tagName)){
			tag = new Tag();
			tag.setCreateTime(MyFormatDate.getNowDate());
			tag.setIsSysTag(0);
			tag.setTagName(tagName);
			tagid = this.save(tag);
		}else{
			///如果存在，则先取出这个标签Id
			tagid = this.myFindByProperty("tagName", tagName).getId();
		}
		
		//设置关联关系并保存
		
		saveUserSubTag(tagid,username);
		
		return saveUserSubTag(tagid,username);
	}
	
	/**
	 * 判断用户是否已经订阅的点击的这个标签
	 * 如果用户已经订阅这个标签，就返回这个标签的Id
	 * @param username
	 * @param tagName
	 * @return
	 */
	public int isUserSubTagName(String username, String tagName){
		/**
		 * 步骤：
		 * 1、先判断这个标签是否已经存在，（由于设计了日志标签和系统标签，所以现在这里要先进行判断）
		 *   如果该标签没有存在，则返回false
		 * 2、如果该标签存在，取出该用户订阅的标签与这个标签进行匹配，如果成功，则返回false;
		 */
		int tagid = 0;
		//数据库中存在该标签
		if(isTagExits(tagName)){
			List<Tag> tagList = loadUserSubTagList(username);
			for(Tag tag : tagList){
				if(tagName.equals(tag.getTagName())){
					/////匹配成功，表示用户已经订阅了该标签
					tagid = tag.getId();
					break; ///退出tag循环
				}
			}
		}
		
		return tagid;
	}
	
	/**
	 * 根据标签名判断这个标签是不是在系统中存在
	 * 如果存在，返回true
	 * @param tagName
	 * @return
	 */
	public boolean isTagExits(String tagName){
		boolean flag = false;
		tag = this.myFindByProperty("tagName", tagName);
		//数据库中存在该标签
		if(null != tag && 0!=tag.getId()){
			flag = true;
		}
		return flag;
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
	public int saveUserSubTag(int tagid, String username){
		tag = this.findById(tagid);
		userLogin = userLoginServiceImpl.myFindByProperty("nickname", username);
		///设置双向关联
		userLogin.getTags().add(tag);
		tag.getUserLogins().add(userLogin);
		return this.save(tag);
	}
	
	/**
	 * 加载用户已经订阅的标签
	 * @param username
	 * @return
	 */
	public List<Tag> loadUserSubTagList(String username){
		tags = this.find("select t from Tag t join  t.userLogins as u where u.nickname=?", new String[]{username});
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
		String hql = "from Tag t where t.id in("+getIntId(ids)+") and isSysTag=1";
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
		UserLogin userLogin = userLoginServiceImpl.myFindByProperty("nickname", username);
		if(null!=userLogin && !userLogin.getTags().isEmpty()){
			String ids = getIntId(userLogin.getTags());
			String hql = "from Tag t where t.id not in("+ids+") and isSysTag=1";
			tags = this.find(hql, new Object[]{});
		}else{
			tags = this.find(HQL+" and isSysTag=1",new Object[]{});
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
			/*for(int i=0; i<SIZE; i++){
				int j = new Random().nextInt(length);
				returnIds[i] = allIds[j];
			}*/
			int ran[] = NumberUtil.ranNumber(SIZE, length);
			for(int i=0; i<SIZE; i++){
				returnIds[i] = tags.get(ran[i]).getId();
			}
			
		}
		return returnIds;
	}
	
	
	/**
	 * 获取系统发现标签页面所需的内容
	 * @return
	 */
	public List<TagSpace> getTagSpaceList(){
		
		List<TagSpace> tagSpaceList = new ArrayList<TagSpace>();
		TagSpace tagSpace = null;
		tags = this.find(HQL+" and isSysTag=?", 1);
		for(Tag tag : tags){
			tagSpace = new TagSpace();
			tagSpace.setTag(tag);
			tagSpace.setLogTagList(this.getLogTagList(tag));
			tagSpace.setSpaceList(this.getSpaceList(""));
			tagSpaceList.add(tagSpace);
		}
		
		return tagSpaceList;
	}
	
	/**
	 * 根据系统分类获取该分类目录下热门的标签top-N
	 * @param tagName
	 * @return
	 */
	//public List<LogTag> getLogTagList(String tagName){
	public List<LogTag> getLogTagList(Tag tag){
		
		/////还没开始对标签进行分类，现在通过生成随机数获取日志标签
		/*List<LogTag> logTags = logTagServiceImpl.loadAll();
		logTagList = new ArrayList<LogTag>();
		////生成5个随机整数
		int maxSize = logTags.size();
		for(int i=0; i<4; i++){
			int index = new Random().nextInt(maxSize);
			logTagList.add(logTags.get(index));
		}*/
		//String hql = LogTagServiceImpl.HQL + " and tagName=?";  ////这个是获取到还没有经过算法取得数据，后期会先经行热门标签的额计算，然后将然们标签返回去
		//List<LogTag> logTags = logTagServiceImpl.find(hql, tagName);
		//tag = myFindByProperty("tagName", tagName);
		List<LogTag>  logTags = new ArrayList<LogTag>(tag.getLogTags());
		logTagList = new ArrayList<LogTag>();
		int maxSize = logTags.size();
		System.out.println("----------size--" + maxSize);
		if(4 < maxSize){ ////如果该系统分类下的标签超过5个的话，就经过一个随机函数返回其中的4个标签
			/*int maxSize = logTags.size();
			for(int i=0; i<4; i++){
				int index = new Random().nextInt(maxSize);
				logTagList.add(logTags.get(index));
			}*/
			int [] a = NumberUtil.ranNumber(4, maxSize);
			for (int i : a) {
				logTagList.add(logTags.get(i));
			}
			
		}else{
			for (LogTag logTag : logTags) {
				logTagList.add(logTag);
			}
		}

		return logTagList;
	}
	

	
	/**
	 * 获取标签分类目录下热门的空间用户
	 * @param tagName
	 * @return
	 */
	public List<Space> getSpaceList(String tagName){
		List<Space> spaces = new ArrayList<Space>();
		Space space = null;
		List<UserLogin> userLogList = userLoginServiceImpl.loadAll();
		int maxSize = userLogList.size();
		for(int i=0; i<5; i++){
			int index = new Random().nextInt(maxSize);
			userLogin = userLogList.get(index);
			String img = userHeadImgServiceImpl.getHeadImgUrl(userLogin.getNickname());
			space = new Space();
			space.setImg(img);
			space.setUserLogin(userLogin);
			spaces.add(space);
		}
		
		return spaces;
	}
	
	/**
	 * 更新系统标签和日志标签的关系
	 * @param tagid
	 * @param logTagIds
	 */
	public void updateSysTag(int tagid, String logTagIds){
		
		String logidArray[] = logTagIds.split(",");
		tag = findById(tagid);
		Set<Tag> tagSet;
		Set<LogTag> logTagSet;
		for(int i=0; i< logidArray.length; i++){
			logTag = logTagServiceImpl.findById(Integer.parseInt(logidArray[i].trim()));
			if(null != logTag.getTags() && !logTag.getTags().isEmpty() ){
				logTag.getTags().add(tag);
			}else{
				tagSet = new HashSet<Tag>();
				tagSet.add(tag);
				logTag.setTags(tagSet);
			}
			
			
			if(null != tag.getLogTags() && !tag.getLogTags().isEmpty()){
				tag.getLogTags().add(logTag);
			}else{
				logTagSet = new HashSet<LogTag>();
				logTagSet.add(logTag);
				tag.setLogTags(logTagSet);
			}
			
			logTagServiceImpl.update(logTag);
			
		}
		
		update(tag);
	}
	
	/**
	 * 根据标签名获取标签被关注的数量
	 * @param tagName
	 * @return
	 */
	public int getTagAttentionNum(String tagName){
		tag = myFindByProperty("tagName", tagName);
		if(null!=tag && !tag.getUserLogins().isEmpty()){
			return tag.getUserLogins().size();
		}
		return 0;
	}
	
	public List<Tag> loadSysTag(){
		return this.find(HQL+" and isSysTag=1", new Object[]{});
	}
}
