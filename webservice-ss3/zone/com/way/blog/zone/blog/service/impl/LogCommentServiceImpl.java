package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.entity.CommentListData;
import com.way.blog.base.entity.CommentListJson;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.user.service.impl.UserHeadImgServiceImpl;
import com.way.blog.util.JsonUtil;
import com.way.blog.util.MyFormatDate;
import com.way.blog.util.PaginationSupport;
import com.way.blog.zone.entity.LogComment;
import com.way.blog.zone.entity.LogInfo;

@Service("logCommentServiceImpl")
public class LogCommentServiceImpl extends BaseGenericService<LogComment, Integer> {

	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	@Autowired private UserHeadImgServiceImpl userHeadImgServiceImpl;
	@Autowired private LogComment logComment;
	@Autowired private LogInfo logInfo;
	
	private PaginationSupport paginationSupport;
	
	@Override
	@Resource(name="logCommentDao")
	public void setDao(IHibernateGenericDao<LogComment, Serializable> dao) {
		super.setDao(dao);
	}

	@Override
	public int save(LogComment logComment) {
		logComment.setCommentTime(MyFormatDate.getNowDate());
		return super.save(logComment);
	}

	
	/**
	 * 根据日志id取出这篇日志对应的日志评论内容
	 * @param logId
	 * @return
	 */
	public String loadCommentList(int logId){
		//List<CommentListJson> commentListJsonList = new ArrayList<CommentListJson>();
		List<CommentListData> commentListJsonList = new ArrayList<CommentListData>();
		//CommentListJson commentListJson = null;
		CommentListData commentListData = null;
		List<LogComment> logCommList = null;
		String username;
		logInfo = logInfoServiceImpl.findById(logId);
		//System.out.println(logInfo);
		logInfo.setId(logId);
		//String hql = "from LogComment where LogInfo=?";
		//paginationSupport = this.findPageByQuery(hql, PaginationSupport.PAGESIZE, 0, new Object[]{logInfo});
		logCommList = new ArrayList<LogComment>(logInfo.getLogComments());
		if(null != logCommList && !logCommList.isEmpty()){
			for(LogComment comment : logCommList){
				//commentListJson = new CommentListJson();\
				commentListData = new CommentListData();
				username = comment.getUsername();
				commentListData.setCommentUsername(username);
				commentListData.setHeadimgUrl(userHeadImgServiceImpl.getHeadImgUrl(username));
				commentListData.setId(comment.getId());
				commentListData.setConten(comment.getCommentContent());
				commentListJsonList.add(commentListData);
			}
			
		}
		
		return JsonUtil.toJson(commentListJsonList);
	}
}
