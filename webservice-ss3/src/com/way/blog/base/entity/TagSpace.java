package com.way.blog.base.entity;

import java.util.List;

import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.zone.entity.BlogZone;
import com.way.blog.zone.entity.LogTag;

/**
 * @author fenzaiway
 *
 */
public class TagSpace {
		
	private Tag tag; //标签
	
	private List<LogTag> logTagList; //相关标签，有热门的日志标签统计，前期如果没有点击量，就通过随机数取出
	
	private List<Space> spaceList; //热门空间

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List<LogTag> getLogTagList() {
		return logTagList;
	}

	public void setLogTagList(List<LogTag> logTagList) {
		this.logTagList = logTagList;
	}

	public List<Space> getSpaceList() {
		return spaceList;
	}

	public void setSpaceList(List<Space> spaceList) {
		this.spaceList = spaceList;
	}

	
	
}
