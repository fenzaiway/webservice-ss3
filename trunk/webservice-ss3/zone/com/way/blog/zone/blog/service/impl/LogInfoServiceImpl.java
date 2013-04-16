package com.way.blog.zone.blog.service.impl;

import java.io.Serializable;
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
import com.way.blog.base.entity.LogInfoData;
import com.way.blog.base.entity.LogInfoJson;
import com.way.blog.base.entity.ReturnStatus;
import com.way.blog.base.entity.SearchTagData;
import com.way.blog.base.service.BaseGenericService;
import com.way.blog.base.service.impl.MessageServiceImpl;
import com.way.blog.user.entity.UserHeadImg;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.user.service.impl.UserHeadImgServiceImpl;
import com.way.blog.user.service.impl.UserLoginServiceImpl;
import com.way.blog.util.JsonUtil;
import com.way.blog.util.MyFormatDate;
import com.way.blog.util.PaginationSupport;
import com.way.blog.util.RegexPatternUtil;
import com.way.blog.util.json.JSONUtil;
import com.way.blog.zone.entity.Attention;
import com.way.blog.zone.entity.BlogZone;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogTag;
import com.way.blog.zone.entity.LogType;

@Service("logInfoServiceImpl")
public class LogInfoServiceImpl extends BaseGenericService<LogInfo, Integer> {
	@Autowired private UserHeadImgServiceImpl userHeadImgServiceImpl;
	@Autowired private LogTagServiceImpl logTagServiceImpl;
	@Autowired private LogTypeServiceImpl logTypeServiceImpl;
	@Autowired private LogLikeServiceImpl logLikeServiceImpl;
	@Autowired private UserLoginServiceImpl userLoginServiceImpl;
	@Autowired private BlogZoneServiceImpl blogZoneServiceImpl;
	@Autowired private AttentionServiceImpl attentionServiceImpl;
	@Autowired private MessageServiceImpl messageServiceImpl;
	
	public static final String HQL = "from LogInfo where 1=1 ";
	
	PaginationSupport paginationSupport;
	
	
	@Autowired private LogTag logTag;
	@Autowired private LogInfo logInfo;
	@Autowired private LogType logType;
	@Autowired private UserLogin userLogin;
	@Autowired private BlogZone blogZone;
	List<Attention> attentionList = new ArrayList<Attention>();
	List<LogInfo> logInfoList = new ArrayList<LogInfo>();
	
	@Override
	@Resource(name="logInfoDao")
	public void setDao(IHibernateGenericDao<LogInfo, Serializable> dao) {
		super.setDao(dao);
	}

	/**
	 * 根据当前用户加载该用户关注的日志数据
	 * @param username
	 * @return
	 */
	public PaginationSupport loadLogInfoDate(String username, int pageSize, int startIndex,Object... values){
		/**
		 * 步骤：
		 * 1、取出用户关注的用户
		 * 2、拼接sql取出用户个人的数据和关注用户的数据（sql = and username=? or username=? or）
		 */
		String hql = HQL + " and username=? ";
		//1、取出用户关注的用户
		//blogZone = blogZoneServiceImpl.myFindByProperty("username", username);
		//attentionList = attentionServiceImpl.findByProperty("fromUserName", username);
		attentionList = attentionServiceImpl.find(AttentionServiceImpl.HQL+" and fromUserName=? and isAttention=1 ", username);
		List<String> usernameList = new ArrayList<String>();
		usernameList.add(username);
		StringBuffer sb = new StringBuffer();
		for(Attention attention : attentionList){
			sb.append(" or username=? ");
			usernameList.add(attention.getToUserName()); ///取得关注的用户
		}
		hql += sb.toString();
		hql += " order by logPublishTime desc"; ///根据发表的时间进行倒序输出
		
		paginationSupport = this.findPageByQuery(hql, pageSize, startIndex, usernameList.toArray());
		return paginationSupport;
	}
	
	
	/**
	 * 发布新日志
	 */
	@Override
	public int save(LogInfo logInfo) {
		
		logInfo.setLogPublishTime(MyFormatDate.getNowDate());
		logInfo.setLogToTop(0); //设置置顶
		logInfo.setLogIsOriginal(0); ///日志发表类型
		logInfo.setLogContentStatus(1);
		logInfo.setDeleteStatue(0);
		return super.save(logInfo);
	}
	
	/**
	 * 改变logText的长度，方便列表显示
	 * @param logInfoList
	 * @return
	 */
	public List<LogInfo> changeLogInfoText(List<LogInfo> logInfoList){
		List<LogInfo> myLogInfoList = new ArrayList<LogInfo>();
		for(LogInfo logInfo : logInfoList){
			String logText = RegexPatternUtil.Html2Text(logInfo.getLogText());
			//System.out.println(logText);
			///只有长度超过200个字符的才转换
			if(logText.length() > 200){
				logInfo.setLogText(logText.substring(0,200));
			}else{
				logInfo.setLogText(logText);
			}
			//System.out.println(logInfo.getLogText());
			//System.out.println("\n\n--------------------------------");
			myLogInfoList.add(logInfo);
		}
		return myLogInfoList;
	}
	
	/**
	 * 根据Id和日志所属用户取出该用户日志的上一篇日志
	 * @param username
	 * @param logid
	 * @return
	 */
	public LogInfo getPreLogInfo(String username, int logid){
		LogInfo loginfo = null;
		String hql = HQL+" and username=? and id<?";
		logInfoList = this.find(hql, new Object[]{username,logid});
		if(!this.isListTEmpty(logInfoList)){
			loginfo = logInfoList.get(logInfoList.size()-1);
		}
		return loginfo;
	}
	
	/**
	 * 根据Id和日志所属用户取出该用户日志的下一篇日志
	 * @param username
	 * @param logid
	 * @return
	 */
	public LogInfo getNextLogInfo(String username, int logid){
		LogInfo loginfo = null;
		String hql = HQL+" and username=? and id>?";
		logInfoList = this.find(hql, new Object[]{username,logid});
		if(!this.isListTEmpty(logInfoList)){
			loginfo = logInfoList.get(0);
		}
		return loginfo;
	}
	
	/**
	 * 根据日志ID取出原创日志
	 * @param id
	 * @return 原创日志
	 */
	public LogInfo findOriginalLogInfo(int id){
		LogInfo logInfo = null;
		logInfo = super.findById(id);
		if(null!=logInfo && 0!=logInfo.getId()){
			int sourceLogInfoId = logInfo.getSourceLogInfoId();
			if(0!=sourceLogInfoId){
				///不是原创日志，得再根据源日志ID取出原创日志
				logInfo = null;
				logInfo = super.findById(sourceLogInfoId);
			}
		}
		return logInfo;
	}

	@Override
	public void update(LogInfo logInfo) {
		logInfo.setLogPublishTime(MyFormatDate.getNowDate());
		logInfo.setLogToTop(0);
		logInfo.setLogContentStatus(1);
		super.update(logInfo);
	}
	
	
	
	/**
	 * 通过json返回用户请求数据
	 * @param username 哪个用户
	 * @param pageSize 每页请求多少条数据
	 * @param startIndex //数据开始点
	 * @param values //分页参数
	 * @return
	 */
	public String getUserAttentionData(String username, int pageSize, int startIndex,Object... values){
		paginationSupport = this.loadLogInfoDate(username, pageSize, startIndex, values);
		//List<LogInfoJson> logInfoJsonList = new ArrayList<LogInfoJson>();
		LogInfoJson logInfoJson = getLogInfoJson(paginationSupport,username);
		
		return JsonUtil.toJson(logInfoJson);
	}
	
	/**
	 * 通过分页组件获取LogInfoJson
	 * @param paginationSupport
	 * @return
	 */
	public LogInfoJson getLogInfoJson(PaginationSupport paginationSupport,String username){
		LogInfoJson logInfoJson = null;
		List<LogInfoData> logInfoDataList = new ArrayList<LogInfoData>();
		List<LogInfo> loginfoList  = paginationSupport.getItems();
		if(null!=loginfoList && !loginfoList.isEmpty()){
			loginfoList = this.changeLogInfoText(loginfoList);//转换字符长度
			logInfoJson = new LogInfoJson();
			logInfoJson.setLoadMore(paginationSupport.getLoadMore());
			if(paginationSupport.hasNextPage()){  ///如果还有下一页，
				logInfoJson.setHasNext(1);
				logInfoJson.setStartIndex(paginationSupport.getNextIndex());
			}else{
				logInfoJson.setHasNext(-1);
				logInfoJson.setStartIndex(0);
			}
			logInfoDataList = getLogInfoDataList(loginfoList,username);
			logInfoJson.setData(logInfoDataList);
		}
		return logInfoJson;
	}
	
	/**
	 * 根据用户点击标签查询对查询的数据进行封装，返回到前台显示
	 * @param paginationSupport ///传递过来的经过条件查询的分页对象
	 * @param username //用户名
	 * @return
	 */
	public SearchTagData getSearchTagData(PaginationSupport paginationSupport,String username){
		SearchTagData searchTagData = null;
		List<LogInfoData> logInfoDataList = new ArrayList<LogInfoData>();
		List<LogInfo> loginfoList  = paginationSupport.getItems();
		if(null!=loginfoList && !loginfoList.isEmpty()){
			loginfoList = this.changeLogInfoText(loginfoList);//转换字符长度
			searchTagData = new SearchTagData();
			////如果有下一页
			if(paginationSupport.hasNextPage()){
				searchTagData.setHasNext(1);
				searchTagData.setNextIndex(paginationSupport.getNextIndex());
			}else{
				searchTagData.setHasNext(0);
			}
			//如果有上一页
			if(paginationSupport.hasPreviousPage()){
				searchTagData.setHasPre(1);
				searchTagData.setPreIndex(paginationSupport.getPreviousIndex());
			}else{
				searchTagData.setHasPre(0);
			}
			logInfoDataList = getLogInfoDataList(loginfoList,username);
			searchTagData.setData(logInfoDataList);
		}
		return searchTagData;
	}
	
	/**
	 * 获取LogInfoData列表
	 * @return
	 */
	public List<LogInfoData> getLogInfoDataList(List<LogInfo> loginfoList,String username){
		List<LogInfoData> logInfoDataList = new ArrayList<LogInfoData>();
		
		
		for (LogInfo logInfo : loginfoList) {
			//LogInfo li = this.findById(logInfo.getId());
			logInfoDataList.add(getLogInfoData(logInfo, username));
		}
		return logInfoDataList;
	}
	
	/**
	 * 通过LogInfo获取LogInfoData
	 * @param logInfo
	 * @return
	 */
	public LogInfoData getLogInfoData(LogInfo logInfo,String username){
		LogInfoData logInfoData = null;
		LogInfo originalLogInfo = null;
		logInfoData = new LogInfoData();
		if(null!=logInfo.getLogTags() && !logInfo.getLogTags().isEmpty()){ ///如果这篇日志有标签的话，就设置标签
			logInfoData.setTags(this.getTags(logInfo));
		}
		if(logLikeServiceImpl.checkIsUserLike(username, logInfo) && null!=username){ ///用户喜欢
			logInfoData.setIsLike(1);
		}else{
			logInfoData.setIsLike(0);
		}
		String logusername = logInfo.getUsername();
		if(logusername.equals(username)){ ///如果当前日志的用户为用户自己
			logInfoData.setIsAttention(-1);
		}else{
			if(attentionServiceImpl.isUserAttention(username, logusername)){ ///判断用户是否关注
				logInfoData.setIsAttention(1);
			}else{
				logInfoData.setIsAttention(0);
			}
		}
		
		originalLogInfo = this.findOriginalLogInfo(logInfo.getId());
		logInfoData.setLogid(logInfo.getId());
		logInfoData.setHotNum(messageServiceImpl.getHotNum(logInfo));
		logInfoData.setUsername(logInfo.getUsername());
		logInfoData.setLogTitle(logInfo.getLogTitle());
		logInfoData.setLogContent(logInfo.getLogText());
		logInfoData.setPublishTime(logInfo.getLogPublishTime());
		logInfoData.setCommentNum((logInfo.getLogComments().size()+logInfo.getLogCommentReplys().size()));
		logInfoData.setLikeNum(originalLogInfo.getLogLikes().size());
		logInfoData.setReprintNum(originalLogInfo.getLogReprints().size());
		logInfoData.setHeadImgUrl(userHeadImgServiceImpl.getHeadImgUrl(logInfo.getUsername()));
		return logInfoData;
	}
	
	/**
	 * 
	 */
	public List<String> getTags(LogInfo logInfo){
		List<String> tags = new ArrayList<String>();
		for(LogTag tag : logInfo.getLogTags()){
			tags.add(tag.getTagName());
		}
		return tags;
	}
	
	/**
	 * 保存文章
	 * @param title 标题
	 * @param content 内容
	 * @param tags 标签
	 * @param username 用户名 
	 * @param visible 可见性
	 * @logTypeId 分类日志的Id,客户端调用的时候，使用0，表示发表到用户的个人日志分类目录下
	 * @return json格式数据，1表示成功，-1表示失败
	 */
	public String save(String title, String content, String tags, String username, int visible, int logTypeId){
		
		///判断logTypeId==0，如果是，则将用户发表的日志保存到用户的默认分类目录下
		if(0==logTypeId){
			logType = logTypeServiceImpl.getDefault(username);
		}else{
			logType = logTypeServiceImpl.findById(logTypeId);
		}
		
		///设置双向关联
		logInfo.setLogType(logType);
		Set<LogInfo> logInfos = new HashSet<LogInfo>();
		logInfos.add(logInfo);
		logType.setLogInfos(logInfos);
		
		logInfo.setLogTitle(title);
		logInfo.setLogText(content);
		logInfo.setUsername(username);
		logInfo.setLogAllowVisit(visible);
		int myid = this.save(logInfo);
		LogInfo li = this.findById(myid);
		logTagServiceImpl.saveTag(li, tags); //保存日志标签
		returnStatus = new ReturnStatus();
		returnStatus.setStatus(1); ///表示成功
		return JsonUtil.toJson(returnStatus);
	}
	
	/**
	 * 根据用户名获取用户的记录
	 * @param username
	 * @return
	 */
	public int getRecoreCount(String username){
		return this.findByProperty("username", username).size();
	}

	/**
	 * 根据tagName获取到这个tagName对应的经过分页的文章数据
	 * @param pageSize
	 * @param startIndex
	 * @param tagName
	 * @return
	 */
	public PaginationSupport findPageByQuery(int pageSize,
			int startIndex, String tagName) {
		
		String hql = HQL+" and id in("+logTagServiceImpl.getLogInfoIds(tagName)+")";
		
		return super.findPageByQuery(hql, pageSize, startIndex, new Object[]{});
	}
	
	public List<LogInfo> getLogInfoList(String username){
		String hql = HQL+" and username=?";
		return this.changeLogInfoText(find(hql, username));
	}
	
	/**
	 * 对List中的内容排序,按照最后评论的时间进行排序
	 */
	public List<LogInfo> sort(List<LogInfo> logInfoList){
		Collections.sort(logInfoList, new Comparator(){

			public int compare(Object o1, Object o2) {
				
				return -((LogInfo)o1).getLogPublishTime().compareTo(((LogInfo)o2).getLogPublishTime());
			}});
 		return logInfoList;
	}

	/**
	 * 根据日志Id返回日志json数据格式
	 * @param logId
	 * @return
	 */
	public String getLogInfoById(String logId) {
		logInfo = findById(Integer.parseInt(logId));
		//return JsonUtil.returnJsonByObjectOfExpose(logInfo);
		if(null != logInfo){
//			LogInfoData lid = new LogInfoData();
//			//lid = this.getLogInfoData(logInfo, logInfo.getUsername());
//			lid.setLogid(logInfo.getId());
//			lid.setLogTitle(logInfo.getLogTitle());
//			lid.setLogContent(logInfo.getLogText());
//			System.out.println(lid);
//			return JsonUtil.toJson(lid);
			return JsonUtil.returnJsonByObjectOfExpose(logInfo);
		}else{
			return "";
		}
		
	}
}
