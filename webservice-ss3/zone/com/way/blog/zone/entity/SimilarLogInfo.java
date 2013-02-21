package com.way.blog.zone.entity;

import java.io.Serializable;

/**
 * 相似文章
 * project_name webservice-ss3
 *
 * package com.way.blog.zone.entity
 *
 * @author fenzaiway
 *
 * @email fenzaiway@qq.com
 *
 * create_time 2013-2-16下午09:34:34
 *
 *
 */
public class SimilarLogInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String logTitle;
	
	private String zoneuser;
	
	private int logId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogTitle() {
		return logTitle;
	}

	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}

	public String getZoneuser() {
		return zoneuser;
	}

	public void setZoneuser(String zoneuser) {
		this.zoneuser = zoneuser;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}
	
	
}
