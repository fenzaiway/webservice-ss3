package admin;

import org.junit.Test;

import base.BaseTest;

import com.way.blog.manager.admin.entity.Area;
import com.way.blog.manager.admin.entity.City;
import com.way.blog.manager.admin.entity.Province;
import com.way.blog.manager.admin.service.IProvinceService;
import com.way.blog.manager.admin.service.impl.ProvinceServiceImpl;


public class ProvinceTest extends BaseTest{
	
	private ProvinceServiceImpl provinceService;
	
	public void init(){
		provinceService = (ProvinceServiceImpl) this.context.getBean("provinceServiceImpl");
	}
	
	@Test
	public void getProvinceById(){
		this.init();
		Province pro = provinceService.findById(20);
		//System.out.println(pro.getProvince());
		
//		for(City city : pro.getCitySet()){
//			System.out.println(city.getCity());
//			for(Area area : city.getAreaSet()){
//				System.out.println(area.getArea());
//			}
//		}
		
	}
	
	@Test
	public void loadAll(){
		this.init();
		for(Province p : provinceService.loadAll()){
			System.out.println(p.getProvince());
		}
	}
}
