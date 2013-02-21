package com.way.blog.zone.blog.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.way.blog.base.action.BaseAction;
import com.way.blog.zone.blog.service.impl.AlbumTypeServiceImpl;
import com.way.blog.zone.entity.AlbumType;

@Controller("albumTypeAction")
@ParentPackage("interceptor")
@Namespace("/albumtype")
public class AlbumTypeAction extends BaseAction implements ModelDriven<AlbumType>{

	@Autowired
	private AlbumTypeServiceImpl albumTypeServiceImpl;
	@Autowired
	private AlbumType albumType;
	private List<AlbumType> albumTypeList; 
	/**
	 * 创建相册分类
	 * @return
	 */
	@Action(value="save",results={
			@Result(name="success",location="/albumtype/gotoAlbumTypeList.do",type="redirect"),
	})
	public String createAlbumType(){
		System.out.println("albumType----->" + albumType.getAlbumTypeName());
		albumType.setUsername(myusername);
		albumType.setCoverImg("");
		albumTypeServiceImpl.save(albumType);
		return SUCCESS;
	}
	
	/**
	 * 进入相册列表
	 */
	@Action(value="gotoAlbumTypeList",results={
			@Result(name="success",location="/WEB-INF/jsp/album/albumTypeList.jsp"),
	})
	public String gotoAlbumList(){
		albumTypeList = albumTypeServiceImpl.find("from AlbumType where username=?", new String[]{zoneuser});
		return SUCCESS;
	}
	
	public AlbumType getModel() {
		return albumType;
	}
	
	public List<AlbumType> getAlbumTypeList() {
		return albumTypeList;
	}

	public void setAlbumTypeList(List<AlbumType> albumTypeList) {
		this.albumTypeList = albumTypeList;
	}
}
