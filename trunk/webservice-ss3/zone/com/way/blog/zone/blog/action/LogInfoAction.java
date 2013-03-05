package com.way.blog.zone.blog.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.way.blog.base.action.BaseAction;
import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.manager.admin.service.impl.TagServiceImpl;
import com.way.blog.util.MyFormatDate;
import com.way.blog.util.PaginationSupport;
import com.way.blog.util.RegexPatternUtil;
import com.way.blog.zone.blog.service.impl.LogCommentServiceImpl;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.blog.service.impl.LogLikeServiceImpl;
import com.way.blog.zone.blog.service.impl.LogReprintServiceImpl;
import com.way.blog.zone.blog.service.impl.LogTagServiceImpl;
import com.way.blog.zone.blog.service.impl.LogTypeServiceImpl;
import com.way.blog.zone.blog.service.impl.LogVisitServiceImpl;
import com.way.blog.zone.entity.LogComment;
import com.way.blog.zone.entity.LogCommentReply;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogLike;
import com.way.blog.zone.entity.LogReprint;
import com.way.blog.zone.entity.LogStore;
import com.way.blog.zone.entity.LogTag;
import com.way.blog.zone.entity.LogType;
import com.way.blog.zone.entity.LogVisit;
import com.way.blog.zone.entity.SimilarLogInfo;

/**
 * 
 * @author fenzaiway
 * 日志管理
 */
@Controller("logInfoAction")
@ParentPackage("interceptor")
@Namespace("/loginfo")
public class LogInfoAction extends BaseAction implements ModelDriven<LogInfo> {
	
	@Autowired
	private LogInfoServiceImpl logInfoServiceImpl;
	@Autowired
	private LogTypeServiceImpl logTypeServiceImpl;
	@Autowired
	private LogVisitServiceImpl logVisitServiceImpl;
	@Autowired 
	private LogReprintServiceImpl logReprintServiceImpl;
	@Autowired 
	private LogLikeServiceImpl logLikeServiceImpl;
	@Autowired
	private LogCommentServiceImpl logCommentServiceImpl;
	@Autowired
	private LogTagServiceImpl logTagServiceImpl;
	@Autowired
	private TagServiceImpl tagServiceImpl;
	
	
	private List<LogInfo> logInfoList = new ArrayList<LogInfo>();
	private List<LogType> logTypeList = new ArrayList<LogType>();
	private List<LogComment> logCommentList = new ArrayList<LogComment>();
	private List<LogReprint> logReprintList = new ArrayList<LogReprint>();
	private List<LogLike> logLikeList = new ArrayList<LogLike>();
	private List<LogTag> logTagList = new ArrayList<LogTag>();
	private List<SimilarLogInfo> similarLogInfoList = new ArrayList<SimilarLogInfo>();
	private List<LogStore> logStoreList = new ArrayList<LogStore>();
	private List<Tag> tagList = new ArrayList<Tag>();
	
	private LogInfo logInfo;
	private LogType logType;
	private LogLike logLike;
	private LogTag logTag;
	private int logTypeId;
	private String content;		////文章内容
	private int logInfoid;		////文章ID
	private int myLogInfoId;	////用与获取转载日志的ID，方便更新该日志的转载量
	private LogVisit logVisit;  ////日志访问
	private int sourceLogInfoId; ////源日志ID
	private int isLike;			/////如果like，则为1，否则为0
	private String myLogTags;		///日志关键字
	
	/**
	 * 保存
	 */
	@Action(value="save",results={
			@Result(name="success",location="/loginfo/gotologinfo.do?zoneuser=%{zoneuser}",type="redirect"),
			//@Result(name="success",location="/loginfo/newLogInfo.do",type="redirect"),
			@Result(name="input",location="/loginfo/newLogInfo.do",type="redirect")
	})
	public String save(){
		//根据分类ID取得分类记录
		logType = logTypeServiceImpl.findById(logTypeId);
		///设置双向关联
		logInfo.setLogType(logType);
		Set<LogInfo> logInfos = new HashSet<LogInfo>();
		logInfos.add(logInfo);
		logType.setLogInfos(logInfos);
		logInfo.setLogText(content);
		logInfo.setUsername(myusername);
		logInfo.setSourceLogInfoId(0); //默认日志为原创日志
		int myid = logInfoServiceImpl.save(logInfo);
		
		logInfo = logInfoServiceImpl.findById(myid);
		this.saveTag(logInfo);///保存日志的标签
		
		return SUCCESS;
	}
	
	/**
	 * 保存关键字
	 */
	public String saveTag(LogInfo logInfo){
		System.out.println("==============" + myLogTags);
	////保存关键字
		String[] tags = myLogTags.split(",");////根据，分隔
		for(int i=0; i<tags.length; i++){
			////先根据关键字判断该关键字是否在tag表中,
			//后期为了扩充关键字，改为like的方式，然后在判断全出来的关键字是不是相等，
			//如果相等的话，就更新，否则将该关键字添加保存，同时相似的关键字也保存文章信息
			System.out.println(tags[i]+"---------------------");
			logTag = logTagServiceImpl.myFindByProperty("tagName", tags[i]);
			if(null!=logTag && null != logTag.getTagName()){	////tag表中已经存在该关键字
				
				if(null !=logTag.getLogInfos() && !logTag.getLogInfos().isEmpty()){
					logTag.getLogInfos().add(logInfo);
					Set<LogTag> tagset = new HashSet<LogTag>();
					tagset.add(logTag);
					logInfo.setLogTags(tagset);
					logTagServiceImpl.save(logTag);
				}else{
					Set<LogInfo> logInfoSet = new HashSet<LogInfo>();
					logInfoSet.add(logInfo);
					logTag.setLogInfos(logInfoSet);
					Set<LogTag> tagset = new HashSet<LogTag>();
					tagset.add(logTag);
					logInfo.setLogTags(tagset);
					logTagServiceImpl.save(logTag);
				}
				
			}else{
				logTag = new LogTag();
				logTag.setTagCreateTime(MyFormatDate.getNowDate());
				logTag.setTagName(tags[i]);
				///设置双向关联
				Set<LogInfo> logInfoSet = new HashSet<LogInfo>();
				logInfoSet.add(logInfo);
				logTag.setLogInfos(logInfoSet);
				Set<LogTag> tagset = new HashSet<LogTag>();
				tagset.add(logTag);
				logInfo.setLogTags(tagset);
				logTagServiceImpl.save(logTag);
			}
			
		}
		return null;
	}
	
	////进入转载页面
	//进入日志管理页面
	@Action(value="turnToLogReprint",results={
			@Result(name="success",location="/WEB-INF/jsp/loginfo/logReprint.jsp"),
	})
	public String gotoReprint(){
		/////1、取出当前用户要转载的日志(已在拦截器中拦截用户是否登录)
		logInfo = logInfoServiceImpl.findById(logInfoid);
		//2、加载要转载日志用户的分类
		logTypeList = logTypeServiceImpl.findByProperty("username", myusername);
		return SUCCESS;
	}

	/**
	 * 保存后跳转到用户自己的空间，显示该篇日志
	 * 是否需要保存，还是只是加个关联？
	 * @return
	 */
	@Action(value="saveLogReprint",results={
			@Result(name="success",location="/loginfo/gotologinfo.do?zoneuser=%{zoneuser}",type="redirect"),
			@Result(name="input",location="/loginfo/newLogInfo.do",type="redirect")
	})
	public String saveReprint(){
		//根据分类ID取得分类记录
		logType = logTypeServiceImpl.findById(logTypeId);
		logInfo.setLogText(content);
		logInfo.setUsername(myusername);	////日志转载用户
		logInfo.setLogType(logType);
		
		Set<LogInfo> logInfos = new HashSet<LogInfo>();
		logInfos.add(logInfo);
		logType.setLogInfos(logInfos);
		
		///1、先更新该日志的转载量
		LogInfo li = logInfoServiceImpl.findById(myLogInfoId);	/////源头日志
		/////如果源日志ID不等于0，就表示这篇日志是转载的，得取出源日志，然后更新源日志的转载量
		sourceLogInfoId = li.getSourceLogInfoId();
		if(0 != sourceLogInfoId){
			li = null;
			li = logInfoServiceImpl.findById(sourceLogInfoId);
			logInfo.setSourceLogInfoId(sourceLogInfoId);
		}else{
			logInfo.setSourceLogInfoId(myLogInfoId);
		}
		LogReprint logReprint = new LogReprint();
		logReprint.setUrl(request.getRequestURI());
		logReprint.setUsername(myusername);
		logReprint.setSourceLogIngoUsername(li.getUsername());
		logReprint.setReprintTime(MyFormatDate.getNowDate());
		////设置双向关联
		logReprint.setLogInfo(li);
		if(null!=li.getLogReprints() && !li.getLogReprints().isEmpty()){
			li.getLogReprints().add(logReprint);
		}else{
			Set<LogReprint> logReprints = new HashSet<LogReprint>();
			logReprints.add(logReprint);
			li.setLogReprints(logReprints);
		}
		//2、保存该日志到用户的空间下
		logInfo.setReprintUsername(li.getUsername());
		int reprintId = logInfoServiceImpl.save(logInfo);
		logReprint.setReprintLogInfoId(reprintId);
		logReprintServiceImpl.save(logReprint);
		return SUCCESS;
	}
	
	/**
	 * 查看文章更多信息
	 */
	@Action(value="viewmore",results={
			@Result(name="success",location="/WEB-INF/jsp/loginfo/logInfoDetail.jsp"),
			@Result(name="404",location="/404.jsp")
	})
	public String viewMore(){
		logVisit = new LogVisit();
		logInfo = logInfoServiceImpl.findById(logInfoid);
		if(null == logInfo){
			return "404";
		}
		/////如果不是空间用户自己查看，则每次更新查看文章的次数
		if(!zoneuser.equals(myusername)){
			logVisit = new LogVisit();
			if(null == myusername || "".equals(myusername)){
				logVisit.setUsername("游客");
			}else{
				logVisit.setUsername(myusername);
			}
			///设置双向关联
			logVisit.setLogInfo(logInfo);
			Set<LogVisit> logVisits = new HashSet<LogVisit>();
			logVisits.add(logVisit);
			//logInfo.setLogVisits(logVisits);
			if(null!=logInfo.getLogVisits() && !logInfo.getLogVisits().isEmpty() && 0!=logInfo.getLogVisits().size()){
				logInfo.getLogVisits().add(logVisit);
			}else{
				logInfo.setLogVisits(logVisits);
			}
			logVisitServiceImpl.save(logVisit);
		}
		sourceLogInfoId = logInfo.getSourceLogInfoId();
		
		//////如果是原创用户查看空间的时候，直接根据双向关联取得到该篇日志的转载量
		if(0 == sourceLogInfoId){
			logReprintList = new ArrayList<LogReprint>(logInfo.getLogReprints());
		}else{
			LogInfo li = new LogInfo();
			li.setId(sourceLogInfoId);
			logReprintList = logReprintServiceImpl.findByProperty("logInfo", li);
		}
		
		logCommentList = new ArrayList<LogComment>(logInfo.getLogComments());
		logStoreList = new ArrayList<LogStore>(logInfo.getLogStores());
		logLikeList = logLikeServiceImpl.findByProperty("logInfo", logInfoServiceImpl.findOriginalLogInfo(logInfoid));
		////加载与这篇文章相似的文章
		logTagList = new ArrayList<LogTag>(logInfo.getLogTags());
		loadSimilarLogInfo(logInfo, logTagList);
		//logTagList = this.removeSaveLogInfo(logInfo, logTagList);
//		Set<LogInfo> logInfos = new HashSet<LogInfo>();
//		logInfos.add(logInfo);
//		logTagList = logTagServiceImpl.find("from LogTag where logInfos=?", new Object[]{logInfo});
		//先在这里修改为查看文章所属于的空间用户到session中,方便用户查看该篇日志所属用户的其他文章
		
		//session.removeAttribute("zoneuser");
		//session.setAttribute("zoneuser", logInfo.getUsername());
		return SUCCESS; 
	}
	
	////加载相似文章
	public void loadSimilarLogInfo(LogInfo logInfo,List<LogTag> logTagList){
		SimilarLogInfo similarLogInfo = null;
		int i=0;
		for (LogTag logTag : logTagList) {
			for (LogInfo logInfos : new ArrayList<LogInfo>(logTag.getLogInfos())) {
				if(logInfo.getId() != logInfos.getId()){
					similarLogInfo = new SimilarLogInfo();
					similarLogInfo.setId(i);
					similarLogInfo.setLogId(logInfos.getId());
					similarLogInfo.setLogTitle(logInfos.getLogTitle());
					similarLogInfo.setZoneuser(logInfos.getUsername());
					similarLogInfoList.add(similarLogInfo);
					i++;
				}
			}
		}
	}
	
	///移除相似文章中的当前文章
//	public List<LogTag> removeSaveLogInfo(LogInfo logInfo,List<LogTag> logTagList){
//		int length=logTagList.size();
//		List<LogTag> myLogTagList = new ArrayList<LogTag>();
//		for(int i=0; i<length; i++){
//			logTag = logTagList.get(i);
//			if(!(logTag.getLogInfos().contains(logInfo))){
//				myLogTagList.add(logTag);
//			}
//		}
//		return myLogTagList;
//	}
	
	//进入日志管理页面
	@Action(value="gotologinfo",results={
			@Result(name="success",location="/WEB-INF/jsp/loginfo/logManager.jsp"),
	})
	public String gotoLogManager(){
		////在进入管理页面前，先加载好所有的数据，现在数据少，先不分页
		/**
		 * 根据登录用户，取得相应的用户数据
		 */
		paginationSupport = logInfoServiceImpl.findPageByQuery("from LogInfo where username=? and deleteStatue=0", PaginationSupport.PAGESIZE, startIndex, zoneuser);
		paginationSupport.setUrl("loginfo/gotologinfo.do");
		logInfoList = paginationSupport.getItems();
		//logInfoList = logInfoServiceImpl.findByProperty("username", zoneuser);
		if(null != logInfoList){
			logInfoList = logInfoServiceImpl.changeLogInfoText(logInfoList);
		}
		logTypeList = logTypeServiceImpl.findByProperty("username", zoneuser);
		return SUCCESS;
	}

	
	//进入发表文字
	@Action(value="newLogInfo",results={
			@Result(name="success",location="/WEB-INF/jsp/loginfo/newLogInfo2.jsp"),
	})
	public String gotoNewLogInfo(){
		/**
		 * 根据登录用户，取得相应的用户数据
		 */
		logTypeList = logTypeServiceImpl.findByProperty("username", myusername);
		tagList = tagServiceImpl.loadAll();///加载系统标签分类
		logTagList = logInfoServiceImpl.getUserLogInfoTagList(myusername);
		return SUCCESS;
	}
	
	/**
	 * 根据当前用户和日志ID获取用户是否like(更新的是原创日志的like)
	 */
	@Action(value="isUserLike",results={
			@Result(type="json")
	})
	public String isUserLike(){
		//logInfo = new LogInfo();
		//logInfo.setId(myLogInfoId);
		////判断是否是原创日志
		logInfo = logInfoServiceImpl.findOriginalLogInfo(myLogInfoId);
		
		logLikeList = logLikeServiceImpl.find("from LogLike where username=? and logInfo=?", new Object[]{myusername,logInfo});
		if(null!=logLikeList && !logLikeList.isEmpty()){
			logLike = logLikeList.get(0);
			logLike.setLogInfo(null);
		}else{
			logLike = new LogLike();
			logLike.setUsername(myusername);
		}
//		logLikeList = null;
//		logLikeList = logLikeServiceImpl.findByProperty("logInfo", logInfo);
//		List<LogLike> myLogLikeList = new ArrayList<LogLike>();
//		myLogLikeList.add(logLike);
//		for(LogLike ll : logLikeList){
//			ll.setLogInfo(null);
//			myLogLikeList.add(ll);
//		}
//	
//		this.returnJsonString(myLogLikeList);
		this.returnJsonByObject(logLike);
		return null;
	}
	
	
	/**
	 * 异步更新like
	 * @return
	 */
	@Action(value="like",results={
			@Result(type="json")
	})
	public String updateLike(){
		///1、获取原创日志ID
		logInfo = logInfoServiceImpl.findOriginalLogInfo(myLogInfoId);
		//2、判断用户是否like
		if(0 == isLike){
			////用户unlike
			////根据用户取出该用户的like
			logLikeList = logLikeServiceImpl.find("from LogLike where username=? and logInfo=?", new Object[]{myusername,logInfo});
			if(null!=logLikeList && !logLikeList.isEmpty()){
				//logInfo.getLogLikes().remove(logLikeList.get(0));
				logLikeServiceImpl.delete(logLikeList.get(0));
			}
			
		}else if(1 == isLike){
			logLike = new LogLike();
			logLike.setLikeTime(MyFormatDate.getNowDate());
			logLike.setUsername(myusername);
			logLike.setLogInfo(logInfo);
			////用户like
			logLikeList = new ArrayList<LogLike>(logInfo.getLogLikes());
			if(null!=logLikeList && !logLikeList.isEmpty()){
				logLikeList.add(logLike);
			}else{
				Set<LogLike> logLikes = new HashSet<LogLike>();
				logLikes.add(logLike);
			}
			logLikeServiceImpl.save(logLike);
		}
		
		//////3、返回like数量
		logLikeList = logLikeServiceImpl.findByProperty("logInfo", logInfo);
		List<LogLike> myLogLikeList = new ArrayList<LogLike>(); 
		if(null!=logLikeList && !logLikeList.isEmpty()){
			for(LogLike logLike : logLikeList){
				logLike.setLogInfo(null);
				myLogLikeList.add(logLike);
			}
			
		}else{
			logLike = new LogLike();
			myLogLikeList.add(logLike);
			//this.returnJsonByObject(logLike);
		}
		this.returnJsonString(logLikeList);
		return null;
	}
	
	
//	/**
//	 * 测试异步分页，这里测试的是根据日志ID取出该篇日志的评论
//	 * @return
//	 */
//	@Action(value="pageTest",results={
//			@Result(type="json")
//	})
//	public String getPageByJquery(){
//		paginationSupport = logInfoServiceImpl.findPageByQuery("from LogInfo where username=?", PaginationSupport.PAGESIZE, startIndex, "fenzaiway");
//		this.pageClass2Json(paginationSupport);
//		return null;
//	}
//	
	//进入日志更新页面
	@Action(value="gotoLogInfoUpdate",results={
			@Result(name="success",location="/WEB-INF/jsp/loginfo/logInfoUpdate.jsp"),
	})
	public String gotoLogInfoUpdate(){
		logInfo = logInfoServiceImpl.findById(logInfoid);
		logTypeList = logTypeServiceImpl.find("from LogType where username=?", new String[]{zoneuser});
		return SUCCESS;
	}
	
	//更新日志
	@Action(value="update",results={
			@Result(name="success",location="/loginfo/gotologinfo.do?zoneuser=%{zoneuser}",type="redirect"),
	})
	public String update(){
		System.out.println(logInfo.getLogTitle()+"  id===" + logInfo.getId());
		LogInfo logInfoTemp = logInfoServiceImpl.findById(logInfo.getId());
		//根据分类ID取得分类记录
		logType = logTypeServiceImpl.findById(logTypeId);
		///设置双向关联
		logInfoTemp.setLogType(logType);
		Set<LogInfo> logInfos = new HashSet<LogInfo>();
		logInfos.add(logInfoTemp);
		logType.setLogInfos(logInfos);
		logInfoTemp.setLogTitle(logInfo.getLogTitle());
		logInfoTemp.setLogAllowVisit(logInfo.getLogAllowVisit());
		logInfoTemp.setLogIsOriginal(logInfo.getLogIsOriginal());
		logInfoTemp.setLogText(content);
		logInfoServiceImpl.update(logInfoTemp);
		return SUCCESS;
	}
	
	//删除日志
	/**
	 * 这里只是将删除状态设置为1，这样用户还可以通过回收站恢复日志
	 * 只有当用户到回收站删除的时候，这个时候才真正的将日志从表中删除
	 */
	@Action(value="delete",results={
			@Result(name="success",location="/loginfo/gotologinfo.do?zoneuser=%{zoneuser}",type="redirect"),
	})
	public String delete(){
		logInfo = logInfoServiceImpl.findById(logInfoid);
		//logInfoServiceImpl.delete(logInfo);
		logInfo.setDeleteStatue(1);
		logInfoServiceImpl.update(logInfo);
		return SUCCESS;
	}
	
	public List<LogInfo> getLogInfoList() {
		return logInfoList;
	}

	public void setLogInfoList(List<LogInfo> logInfoList) {
		this.logInfoList = logInfoList;
	}

	public List<LogType> getLogTypeList() {
		return logTypeList;
	}

	public List<LogStore> getLogStoreList() {
		return logStoreList;
	}

	public void setLogStoreList(List<LogStore> logStoreList) {
		this.logStoreList = logStoreList;
	}

	public void setLogTypeList(List<LogType> logTypeList) {
		this.logTypeList = logTypeList;
	}


	public LogInfo getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(LogInfo logInfo) {
		this.logInfo = logInfo;
	}

	public LogType getLogType() {
		return logType;
	}

	public void setLogType(LogType logType) {
		this.logType = logType;
	}

	public int getLogTypeId() {
		return logTypeId;
	}

	public void setLogTypeId(int logTypeId) {
		this.logTypeId = logTypeId;
	}

	public LogInfo getModel() {
		return logInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLogInfoid() {
		return logInfoid;
	}

	public void setLogInfoid(int logInfoid) {
		this.logInfoid = logInfoid;
	}

	public List<LogComment> getLogCommentList() {
		return logCommentList;
	}

	public void setLogCommentList(List<LogComment> logCommentList) {
		this.logCommentList = logCommentList;
	}

	public int getMyLogInfoId() {
		return myLogInfoId;
	}

	public void setMyLogInfoId(int myLogInfoId) {
		this.myLogInfoId = myLogInfoId;
	}

	public List<LogReprint> getLogReprintList() {
		return logReprintList;
	}

	public void setLogReprintList(List<LogReprint> logReprintList) {
		this.logReprintList = logReprintList;
	}


	public int getIsLike() {
		return isLike;
	}

	public void setIsLike(int isLike) {
		this.isLike = isLike;
	}

	public List<LogLike> getLogLikeList() {
		return logLikeList;
	}

	public void setLogLikeList(List<LogLike> logLikeList) {
		this.logLikeList = logLikeList;
	}

	public String getMyLogTags() {
		return myLogTags;
	}

	public void setMyLogTags(String myLogTags) {
		this.myLogTags = myLogTags;
	}



	public List<LogTag> getLogTagList() {
		return logTagList;
	}

	public void setLogTagList(List<LogTag> logTagList) {
		this.logTagList = logTagList;
	}

	public List<SimilarLogInfo> getSimilarLogInfoList() {
		return similarLogInfoList;
	}

	public void setSimilarLogInfoList(List<SimilarLogInfo> similarLogInfoList) {
		this.similarLogInfoList = similarLogInfoList;
	}

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}
	
	
}