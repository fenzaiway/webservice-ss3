package com.way.blog.base.entity;

import java.io.Serializable;

/**
 * 判断返回状态
 * project_name webservice-ss3
 *
 * package com.way.blog.base.entity
 *
 * @author fenzaiway
 *
 * @email fenzaiway@qq.com
 *
 * create_time 2013-2-17下午02:39:09
 *
 *
 */
public class ReturnStatus implements Serializable {
	
	private static final long serialVersionUID = -5438530138978202482L;
	private int status; //通过json返回状态，状态为1表示已经存在，0表示还没存在
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
