package com.way.blog.base.entity;

import java.util.List;

/**
 * 这个类的设计是返回json数据到前台页面显示数据
 * project_name webservice-ss3
 *
 * package com.way.blog.base.entity
 *
 * @author fenzaiway
 *
 * @email fenzaiway@qq.com
 *
 * create_time 2013-3-5上午01:10:38
 *
 *
 */
public class LogInfoJson {
	
	private int logid;
	
	private String logTitle;
	
	private String publishTime;
	
	private String headImgUrl;
	
	private List<String> tags;
	
	private int hotNum;
	
	private int commentNum;
	
	private int likeNum;

	private int reprintNum;
	
	private String logContent;
	
	private String[] imgs; ///日志图像列表
	
}
