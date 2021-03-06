package com.way.blog.base.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.base.dao.IHibernateGenericDao;
import com.way.blog.base.entity.Message;
import com.way.blog.base.entity.MessageData;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.user.service.impl.UserHeadImgServiceImpl;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.util.MyFormatDate;
import com.way.blog.util.PaginationSupport;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.entity.LogInfo;

@Service("messageServiceImpl")
public class MessageServiceImpl extends BaseGenericService<Message, Integer> {

	@Override
	@Resource(name="messageDao")
	public void setDao(IHibernateGenericDao<Message, Serializable> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}
	
	@Autowired private Message message;
	@Autowired private UserHeadImgServiceImpl userHeadImgServiceImpl;
	@Autowired private UserLoginServiceImpl userLoginServiceImpl;
	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	
	private static final String HQL = "from Message where 1=1 ";
	
	List<Message> messageList = new ArrayList<Message>();
	
	/**
	 * 获取消息列表
	 * @param username
	 * @return
	 */
	public List<Message> getNewMessage(String username){
		
		String hql = HQL+" and (username=? and msgStatus=0 ) or ( msgtype=5 and username=?  and msgStatus=0)";
		messageList = this.find(hql, new String[]{username,username});
		return messageList;
	}
	
	/**
	 * 更新消息
	 * @param username
	 */
	public void updateMessage(String username){
		List<Message> msgList = this.getNewMessage(username);
		if(null!=msgList && !msgList.isEmpty()){
			for(Message msg : msgList){
				msg.setMsgStatus(1);
				this.update(msg);
			}
		}
 	}
	
	/**
	 * 根据消息类型、哪篇日志、消息内，发起用户 保存消息
	 * 如果消息类型为 5：公告消息时，则需要传递消息内容，而不需要传递LogInfo
	 * 如果消息类型为其他类型时，不需要传递内容，但需要传递LogInfo
	 * @param fromusername
	 * @param msgType 消息类型
	 * @param logInfo 哪篇日志
	 * @param content 消息内容
	 * @param username 消息属于哪个用户
	 * 
	 * @return
	 */
	public int saveMessage(String fromusername,int msgType, LogInfo logInfo, String content,String username){
			if(5 == msgType){
				List<Message> messageList = new ArrayList<Message>();
				for(String str : userLoginServiceImpl.getNickNameList()){
				//.out.println("nickName=" + str);
					message = getMessage(fromusername,content,msgType);
					message.setUsername(str);
					messageList.add(message);
					//this.save(message);
				}
				this.saveAll(messageList);
				return 0;
			}else if(6==msgType)
			{
				message = getMessage(fromusername,msgType,username);
			}else if(7==msgType){
				if(!fromusername.equals(username))//当回复的不是自己评论的时候
				//this.saveOriginalLogInfoMessage(logInfo,fromusername,msgType);
				message = getMessage(msgType,logInfo,fromusername);
				message.setUsername(username);
			}else{
				if(!fromusername.equals(logInfo.getUsername())){ //当评论用户评论的不是自己的内容的时候，才会触发
						//this.saveOriginalLogInfoMessage(logInfo,fromusername,msgType);
						message = getMessage(msgType,logInfo,fromusername);
					
				}else{
					return 0; ///如果是自己评论、like自己的内容，那么就不会触发保存
				}
			}
			System.out.println("0----");
			return this.save(message);
		
	}
	
	/**
	 * 评论的时候，如果是原创文章，则消息也会提醒原创文章用户
	 * @param liTemp
	 * @param fromusername
	 * @param msgType
	 */
	public void saveOriginalLogInfoMessage(LogInfo liTemp,String fromusername,int msgType){
		if(0!=liTemp.getSourceLogInfoId()){ ////如果这篇日志不是原创的时候，同时通知原创用户和转载用户
			LogInfo li = logInfoServiceImpl.findOriginalLogInfo((liTemp.getId()));
			Message ms = getMessage(msgType,li,fromusername);
			this.save(ms);
		}
	}
	
	/**
	 * 获取用户关注空间消息
	 * @param fromusername
	 * @param msgType
	 * @return
	 */
	public Message getMessage(String fromusername,int msgType,String toUsername){
		String userzoneUrl = "<a href='zone/"+fromusername+"'>"+fromusername+"</a>";
		//表示评论
		StringBuffer sb = new StringBuffer();
		sb.append(userzoneUrl).append("关注了您的空间");
		Message msg = new Message();
		msg.setFromusername(fromusername);
		msg.setMsgContent(sb.toString());
		msg.setMsgStatus(0);
		msg.setTriggerTime(MyFormatDate.getNowDate());
		msg.setMsgtype(msgType);
		msg.setUsername(toUsername);
		return msg;
	}
	
	/**
	 * 根据消息类型、日志，发起用户 生成一条消息
	 * @param msgType 消息类型
	 * @param logInfo 日志
	 * @param fromusername 发起用户
	 * @return
	 */
	public Message getMessage(int msgType, LogInfo logInfo, String fromusername){
		Message msg = new Message();
		msg.setFromusername(fromusername);
		msg.setMsgContent(createContentByMsgType(msgType,logInfo,fromusername));
		
		msg.setUsername(logInfo.getUsername());
		msg.setTriggerTime(MyFormatDate.getNowDate());
		msg.setMsgtype(msgType);
		msg.setMsgStatus(0);
		msg.setLogInfo(logInfo);
		if(null!=logInfo.getMessageSet() && !logInfo.getMessageSet().isEmpty()){
			logInfo.getMessageSet().add(msg);
		}else{
			Set<Message> messageSet = new HashSet<Message>();
			messageSet.add(msg);
			logInfo.setMessageSet(messageSet);
		}
		return msg;
	}
	
	/**
	 * 根据发起用户、消息内容产生一条消息
	 * @param fromusername 
	 * @param content 内容
	 * @param msgType 消息类型
	 * @return
	 */
	public Message getMessage(String fromusername, String content,int msgType){
		Message msg = new Message();
		msg.setFromusername(fromusername);
		msg.setMsgtype(msgType);
		msg.setTriggerTime(MyFormatDate.getNowDate());
		msg.setMsgStatus(0);
		msg.setUsername("");
		msg.setMsgContent(content);
		return msg;
	}
	
	/**
	 * 生成消息内容
	 * @param msgType
	 * @param logInfo
	 * @param fromusername
	 * @return
	 */
	public String createContentByMsgType(int msgType, LogInfo logInfo, String fromusername){
		/**
		 * 消息类型
		 * 1、表示评论消息 xxx评论了哪篇文章
		 * 2、表示转载消息 xxx转载了哪篇文章
		 * 3、表示收藏消息 
		 * 4、表示like消息
		 * 5、表示公告消息
		 * 6、表示关注信息 
		 * 7、表示回复
		 */
		int logid = logInfo.getId();
		String logTitle = logInfo.getLogTitle();
		String url="loginfo/viewmore.do?logInfoid="+logid;
		String userzoneUrl = "<a href='zone/"+fromusername+"'>"+fromusername+"</a>";
		StringBuffer sb = new StringBuffer();
		if(1 == msgType){
			//表示评论
			sb.append(userzoneUrl).append("评论了您的").append("<a href='").append(url).append("'>").append(logTitle).append("</a>");
		}
		if(4 == msgType){
			//表示评论
			sb.append(userzoneUrl).append("喜欢了您的").append("<a href='").append(url).append("'>").append(logTitle).append("</a>");
		}
		if(7==msgType){
			sb.append(userzoneUrl).append("回复了您的评论").append("<a href='").append(url).append("'>").append(logTitle).append("</a>");
		}
		
		return sb.toString();
	}
	
	/**
	 * 根据日志取出该篇日志的热度值列表
	 * @param logInfo
	 * @return
	 */
	public List<Message> getMessageByLogInfo(LogInfo logInfo){
		String hql = HQL+" and logInfo=?";
		List<Message> messageList = this.find(hql, logInfo);
		return messageList;
	}
	
	/**
	 * 根据日志取出该篇日志的热度值
	 * @param logInfo
	 * @return
	 */
	public int getHotNum(LogInfo logInfo){
		List<Message> messageList = this.getMessageByLogInfo(logInfo);
		int hotNum = 0;
		if(null!=messageList && !messageList.isEmpty()){
			hotNum = messageList.size();
		}
		return hotNum;
	}
	
	/**
	 * 根据日志取出该篇日志的热度值数据列表
	 * @param logInfo
	 * @return
	 */
	public List<MessageData> getMessageData(LogInfo logInfo){
		
		List<Message> messageList = this.getMessageByLogInfo(logInfo);
		List<MessageData> messageDataList = new ArrayList<MessageData>();
		MessageData messageData = null;
		for(Message msg: messageList){
			messageData = new MessageData();
			messageData.setId(msg.getId());
			messageData.setFromUserName(msg.getFromusername());
			messageData.setHeadImgUrl(userHeadImgServiceImpl.getHeadImgUrl(msg.getFromusername()));
			messageData.setContent(getMessageContentByMessageType(msg,logInfo));
			messageDataList.add(messageData);
		}
		
		return messageDataList;
	}
	
	/**
	 * 根据消息类型生成对应类型的内容
	 * @param msgType
	 * @return
	 */
	public String getMessageContentByMessageType(Message msg, LogInfo logInfo){
		/**
		 * 消息类型
		 * 1、表示评论消息 xxx评论了哪篇文章
		 * 2、表示转载消息 xxx转载了哪篇文章
		 * 3、表示收藏消息 
		 * 4、表示like消息
		 * 5、表示公告消息
		 * 6、表示关注信息 
		 * 7、表示回复
		 */
		int msgType = msg.getMsgtype();
		String fromUserName = msg.getFromusername();
		String userzoneUrl = "<a href='zone/"+fromUserName+"'>"+fromUserName+"</a>";
		StringBuffer sb = new StringBuffer();
		
		if(1 == msgType){
			sb.append(userzoneUrl).append("评论了这篇文章");
		}
		
		if(2 == msgType){
			sb.append(userzoneUrl).append("转载了这篇文章");
		}
		
		if(3 == msgType){
			sb.append(userzoneUrl).append("收藏了这篇文章");
		}
		
		if(4 == msgType){
			sb.append(userzoneUrl).append("很喜欢这篇文章");
		}
		
		
		return sb.toString();
	}
	
	/**
	 * 获取公告列表
	 * @return
	 */
	public PaginationSupport getPaginationSupport(int pageSize, int startIndex){
		String hql = HQL+" and msgtype=5";
		return this.findPageByQuery(hql, pageSize, startIndex, new Object[]{});
	}
	
	/**
	 * 根据用户名获取用户的消息列表
	 * @param username
	 * @return
	 */
	public List<Message> messageList(String username){
		this.updateMessage(username); ///在获取用户消息之前先更新消息为已读状态
		return this.findByProperty("username", username);
	}
}
