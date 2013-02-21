package com.way.blog.zone.blog.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.way.blog.base.action.BaseAction;
import com.way.blog.zone.blog.service.impl.AlbumServiceImpl;
import com.way.blog.zone.blog.service.impl.AlbumTypeServiceImpl;
import com.way.blog.zone.entity.Album;
import com.way.blog.zone.entity.AlbumType;

@Controller("albumAction")
@ParentPackage("interceptor")
@Namespace("/album")
public class AlbumAction extends BaseAction implements ModelDriven<Album>{
	
	@Autowired
	private AlbumServiceImpl albumServiceImpl;
	@Autowired
	private AlbumTypeServiceImpl albumTypeServiceImpl;
	@Autowired
	private Album album;
	@Autowired
	private AlbumType albumType;
	
	private List<Album> albumList;
	
	private int albumTypeId;
	
	/**
	 * 进入相册列表
	 * 根据的是传递进来的相册分类Id来取得相册图片
	 */
	@Action(value="gotoAlbumList",results={
			@Result(name="success",location="/WEB-INF/jsp/album/albumList.jsp"),
	})
	public String gotoAlbumList(){
		albumType = albumTypeServiceImpl.findById(albumTypeId);
		albumList = albumServiceImpl.find("from Album where albumType=?", new Object[]{albumType});
		return SUCCESS;
	}


	@Action(value="saveAlbum",results={
			@Result(type="json"),
	})
	public String saveByJquery(){
		albumType = albumTypeServiceImpl.findById(albumTypeId);
		////设置封面
		if(null == albumType.getCoverImg() || "".equals(albumType.getCoverImg())){
			albumType.setCoverImg(album.getAlbumLocation());
		}
		/**
		 * 设置双向关联
		 */
		album.setAlbumType(albumType);
		if(null != albumType.getAlbum()){
			albumType.getAlbum().add(album);
		}else{
			Set<Album> albums = new HashSet<Album>();
			albums.add(album);
			albumType.setAlbum(albums);
		}
		
		albumServiceImpl.save(album);
		return SUCCESS;
	}
	
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public AlbumType getAlbumType() {
		return albumType;
	}

	public void setAlbumType(AlbumType albumType) {
		this.albumType = albumType;
	}

	public List<Album> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
	}




	public Album getModel() {
		// TODO Auto-generated method stub
		return album;
	}

	public int getAlbumTypeId() {
		return albumTypeId;
	}

	public void setAlbumTypeId(int albumTypeId) {
		this.albumTypeId = albumTypeId;
	}
	
}
