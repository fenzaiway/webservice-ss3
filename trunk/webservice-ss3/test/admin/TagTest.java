package admin;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.manager.admin.service.impl.TagServiceImpl;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.util.MyFormatDate;

import base.BaseTest;

public class TagTest extends BaseTest {

	private TagServiceImpl tagServiceImpl;
	private UserLoginServiceImpl userLoginServiceImpl;
	
	String[] tags = new String[]{"父母","萌","cosplay","明星","文字","设计","自然","插画","生活","心情","育儿"
								,"摄影","旅游","美食","宠物","家居","时尚","美女","文学","艺术","创意","星座"
								,"音乐","影视","恋物","搞笑","游戏","体育","动漫","数码","科学","资讯","汽车"
								};
	
	public void init(){
		tagServiceImpl = (TagServiceImpl) this.context.getBean("tagServiceImpl");
		userLoginServiceImpl = (UserLoginServiceImpl)this.context.getBean("userLoginServiceImpl");
	}
	
	
	/**
	 * 增加系统tag
	 */
	@Test
	public void addTagTest(){
		this.init();
		Tag tag = null;
		List<Tag> mytags = new ArrayList<Tag>();
 		for(int i=0; i<tags.length; i++){
			tag = new Tag();
			tag.setTagName(tags[i]);
			tag.setCreateTime(MyFormatDate.getNowDate());
			//tagServiceImpl
			mytags.add(tag);
		}
 		
 		tagServiceImpl.saveAll(mytags);
		
	}
	
	/**
	 * 用户订阅tag
	 */
	@Test
	public void addUserSubTagTest(){
		this.init();
		UserLogin userLogin = userLoginServiceImpl.myFindByProperty("username", username);
		System.out.println(userLogin);
		Tag tag = tagServiceImpl.findById(20);
		///设置双向关联
		userLogin.getTags().add(tag);
		tag.getUserLogins().add(userLogin);
		tagServiceImpl.save(tag);
	}
	
	/**
	 * 根据用户取出不包用户订阅过的标签
	 */
	@Test
	public void loadTagsTest(){
		this.init();
		List<Tag> tags;
		UserLogin userLogin = userLoginServiceImpl.myFindByProperty("username", username);
		if(null!=userLogin.getTags() && !userLogin.getTags().isEmpty()){
			String ids = "";
			List<Integer> idList = new ArrayList<Integer>();
			List<Tag> tagList = new ArrayList<Tag>(userLogin.getTags());
			for(int i=0; i<tagList.size(); i++){
				//Tag tag = 
				idList.add(tagList.get(i).getId());
			}
			ids = idList.toString().substring(1,idList.toString().length()-1);
			String hql = "from Tag t where t.id not in("+ids+")";
			tags = tagServiceImpl.find(hql, new Object[]{});
		}else{
			tags = tagServiceImpl.loadAll();
		}
		//tags = tagServiceImpl.find("from Tag t join  t.userLogins asa u where u.username!='fenzaiway'", new String[]{});
		System.out.println(tags.size());
	}
}
