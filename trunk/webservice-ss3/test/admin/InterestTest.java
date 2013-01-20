package admin;

import java.util.Date;

import org.junit.Test;

import com.way.blog.manager.admin.entity.Interest;
import com.way.blog.manager.admin.service.impl.InterestServiceImpl;
import com.way.blog.util.MyFormatDate;

import base.BaseTest;

/**
 * 
 * @author fenzaiway
 * 用户兴趣
 */
public class InterestTest extends BaseTest {
	
	private InterestServiceImpl interestService;
	
	private String[] str = new String[]{"图书/音像","娱乐","旅游/度假","体育/户外/健身","汽车","游戏/聊天","IT/数码","购物/消费"};
	public void init(){
		interestService = (InterestServiceImpl) this.context.getBean("interestServiceImpl");
	}
	
	@Test
	public void addInterest(){
		this.init();
		Interest in = null;
		for(int i=0; i<str.length; i++){
			in = new Interest();
			in.setUsername("fenzaiway");
			in.setCreateTime(MyFormatDate.getFullDate(new Date()));
			in.setInterestName(str[i]);
			interestService.save(in);
		}
	}
	
	@Test
	public void load(){
		this.init();
		for(Interest in : interestService.loadAll()){
			System.out.print(in.getInterestName() + "\t");
		}
	}
}
