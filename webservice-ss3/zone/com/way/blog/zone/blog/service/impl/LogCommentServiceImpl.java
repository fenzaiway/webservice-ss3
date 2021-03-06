package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.way.blog.zone.entity.LogCommentReply;
import com.way.blog.zone.entity.LogInfo;

@Service("logCommentServiceImpl")
public class LogCommentServiceImpl extends BaseGenericService<LogComment, Integer> {

	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	@Autowired private UserHeadImgServiceImpl userHeadImgServiceImpl;
	@Autowired private LogCommentReplyServiceImpl logCommentReplyServiceImpl;
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

	
	public int save(int logId, String username, String commentContent){
		LogInfo logInfo = logInfoServiceImpl.findById(logId);
		logComment.setCommentContent(commentContent);
		
		logComment.setUsername(username);
		///设置双向关联
		logComment.setLogInfo(logInfo);
		Set<LogComment> logComments = new HashSet<LogComment>();
		logComments.add(logComment);
		logInfo.setLogComments(logComments);
		
		return this.save(logComment);
	}
	
	/**
	 * 根据日志id取出这篇日志对应的日志评论内容
	 * @param logId
	 * @return
	 */
	public String loadCommentList(int logId){
		//List<CommentListJson> commentListJsonList = new ArrayList<CommentListJson>();
		List<CommentListData> commentListJsonList = new ArrayList<CommentListData>();
		Set<CommentListData> commentListDataSet = new HashSet<CommentListData>();
		//CommentListJson commentListJson = null;
		
		List<LogComment> logCommList = null;
		
		logInfo = logInfoServiceImpl.findById(logId);
		//System.out.println(logInfo);
		logInfo.setId(logId);
		//String hql = "from LogComment where LogInfo=?";
		//paginationSupport = this.findPageByQuery(hql, PaginationSupport.PAGESIZE, 0, new Object[]{logInfo});
		//logCommList = new ArrayList<LogComment>(logInfo.getLogComments());
		//logCommList = this.sort(logInfo.getLogComments());
		logCommList = new ArrayList<LogComment>(logInfo.getLogComments());
		///取得评论内容
		if(null != logCommList && !logCommList.isEmpty()){
			for(LogComment comment : logCommList){
				//commentListJson = new CommentListJson();\
				
				commentListDataSet.add(getCommentListData(comment));
			}
			
		}
		//取得回复内容
		if(null!=logInfo.getLogCommentReplys() && !logInfo.getLogCommentReplys().isEmpty()){
			for(LogCommentReply logCommentReply : logInfo.getLogCommentReplys()){
				commentListDataSet.add(logCommentReplyServiceImpl.getCommentListData(logCommentReply.getId()));
			}
			
		}
		
		commentListJsonList = this.sortCommentListData(commentListDataSet);
		
		return JsonUtil.toJson(commentListJsonList);
	}
	
	public CommentListData getCommentListData(LogComment comment){
		String username;
		CommentListData commentListData = null;
		commentListData = new CommentListData();
		username = comment.getUsername();
		commentListData.setCommentUsername(username);
		commentListData.setHeadimgUrl(userHeadImgServiceImpl.getHeadImgUrl(username));
		commentListData.setReplyUsername("");
		commentListData.setId(comment.getId());
		commentListData.setConten(comment.getCommentContent());
		commentListData.setTime(comment.getCommentTime());
		return commentListData;
	}
	
	/**
	 * 对HashSet中的内容排序,按照最后评论的时间进行排序
	 */
	public List<LogComment> sort(Set<LogComment> logCommentSet){
		List<LogComment> logCommentList = new ArrayList<LogComment>(logCommentSet);
		Collections.sort(logCommentList, new Comparator(){

			public int compare(Object o1, Object o2) {
				
				return -((LogComment)o1).getCommentTime().compareTo(((LogComment)o2).getCommentTime());
			}});
 		return logCommentList;
	}
	
	/**
	 * 对HashSet中的内容排序,按照最后评论的时间进行排序
	 */
	public List<LogComment> descSort(Set<LogComment> logCommentSet){
		List<LogComment> logCommentList = new ArrayList<LogComment>(logCommentSet);
		Collections.sort(logCommentList, new Comparator(){

			public int compare(Object o1, Object o2) {
				
				return ((LogComment)o1).getCommentTime().compareTo(((LogComment)o2).getCommentTime());
			}});
 		return logCommentList;
	}
	
	
	/**
	 * 对CommentListData按时间进行排序
	 * @param commentListDataSet
	 * @return
	 */
	public List<CommentListData> sortCommentListData(Set<CommentListData> commentListDataSet){
		List<CommentListData> commentListDataList = new ArrayList<CommentListData>(commentListDataSet);
		Collections.sort(commentListDataList, new Comparator(){

			public int compare(Object o1, Object o2) {
				
				return -((CommentListData)o1).getTime().compareTo(((CommentListData)o2).getTime());
			}});
 		return commentListDataList;
	}
}

