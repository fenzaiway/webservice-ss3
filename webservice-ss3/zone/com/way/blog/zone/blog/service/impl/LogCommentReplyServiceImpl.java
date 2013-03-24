package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.entity.CommentListData;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.user.service.impl.UserHeadImgServiceImpl;
import com.way.blog.util.MyFormatDate;
import com.way.blog.zone.entity.LogComment;
import com.way.blog.zone.entity.LogCommentReply;
import com.way.blog.zone.entity.LogInfo;

@Service
public class LogCommentReplyServiceImpl extends BaseGenericService<LogCommentReply, Integer> {

	@Override
	@Resource(name="logCommentReplyDao")
	public void setDao(IHibernateGenericDao<LogCommentReply, Serializable> dao) {
		super.setDao(dao);
	}

	public static final String HQL = "from LogCommentReply and 1=1 ";
	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	@Autowired private LogCommentServiceImpl logCommentServiceImpl;
	@Autowired private UserHeadImgServiceImpl userHeadImgServiceImpl;
	@Autowired private LogInfo logInfo;
	@Autowired private LogCommentReply logCommentReply;
	@Autowired private LogComment logComment;
	
	@Override
	public int save(LogCommentReply logCommentReply) {
		logCommentReply.setReplyTime(MyFormatDate.getNowDate());
		return super.save(logCommentReply);
	}

	/**
	 * 保存日志评论回复
	 * @param logid
	 * @param commentid
	 * @param fromusername
	 * @param tousername
	 * @param content
	 * @return
	 */
	public int saveReplay(int logid, int commentid, String fromusername, String tousername, String content){
		logInfo = logInfoServiceImpl.findById(logid);
		logComment = logCommentServiceImpl.findById(commentid);
		logCommentReply.setUsername(fromusername);
		logCommentReply.setToUserName(tousername);
		logCommentReply.setReplyContent(content);
		logCommentReply.setReplyTime(MyFormatDate.getNowDate());
		
		////设置双向关联关系
		Set<LogCommentReply> lcSet = null;
		///日志与回复的关联
		logCommentReply.setLogInfo(logInfo);
		if(null!=logInfo.getLogCommentReplys() && !logInfo.getLogCommentReplys().isEmpty()){
			logInfo.getLogCommentReplys().add(logCommentReply);
		}else{
			lcSet = new HashSet<LogCommentReply>();
			logInfo.setLogCommentReplys(lcSet);
		}
		//评论与回复的关系
		logCommentReply.setLogComment(logComment);
		if(null!=logComment.getLogCommentReplys() && !logComment.getLogCommentReplys().isEmpty()){
			logComment.getLogCommentReplys().add(logCommentReply);
		}else{
			lcSet = new HashSet<LogCommentReply>();
			logComment.setLogCommentReplys(lcSet);
		}
		
		return this.save(logCommentReply);
	}
	
	public CommentListData getCommentListData(int replayid){
		CommentListData commentListData = new CommentListData();
		logCommentReply = this.findById(replayid);
		commentListData.setId(replayid);
		commentListData.setCommentUsername(logCommentReply.getToUserName());
		commentListData.setReplyUsername(logCommentReply.getUsername());
		commentListData.setConten(logCommentReply.getReplyContent());
		commentListData.setHeadimgUrl(userHeadImgServiceImpl.getHeadImgUrl(logCommentReply.getUsername()));
		commentListData.setTime(logCommentReply.getReplyTime());
		return commentListData;
	}
	
}
