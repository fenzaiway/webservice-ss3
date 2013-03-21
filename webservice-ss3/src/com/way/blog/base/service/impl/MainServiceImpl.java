package com.way.blog.base.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.way.blog.manager.admin.entity.Tag;
import com.way.blog.manager.admin.service.impl.TagClickCountServiceImpl;
import com.way.blog.manager.admin.service.impl.TagServiceImpl;
import com.way.blog.user.entity.UserLogin;
import com.way.blog.zone.blog.service.impl.LogInfoServiceImpl;
import com.way.blog.zone.blog.service.impl.LogTagServiceImpl;
import com.way.blog.zone.entity.LogInfo;
import com.way.blog.zone.entity.LogTag;

/**
 * 核心的服务类
 * @author fenzaiway
 *
 */
@Service("mainServiceImpl")
public class MainServiceImpl {

	@Autowired private TagServiceImpl tagServiceImpl;
	@Autowired private LogTagServiceImpl logTagServiceImpl;
	@Autowired private LogInfoServiceImpl logInfoServiceImpl;
	@Autowired private TagClickCountServiceImpl tagClickCountServiceImpl;
	
	/**
	 * 获取每个系统标签下热门的标签
	 * @param tag
	 * @return
	 */
	public List<LogTag> getHotLogTagListByTag(Tag tag){
		/**
		 * 思路：
		 * 循环系统标签下对应的日志标签
		 * 得到日志标签-权值列表
		 * 对列表进行排序，取出top-4
		 */
		List<LogTag> logTagList = new ArrayList<LogTag>();
		//List<Map<LogTag,Double>> mapWeightList = countLogTagListWeight((new ArrayList(tag.getLogTags())));
		Map<LogTag,Double> mapWeight = countLogTagListWeight((new ArrayList(tag.getLogTags())));
		//对Map按Value降序排序
		Map.Entry<LogTag,Double>[] mapEntrys = this.getSortedHashtableByValue(mapWeight);
		for(int i = 0; i<mapEntrys.length; i++){
			logTagList.add(mapEntrys[i].getKey());
			if(i == 3){ //取出top-4
				break;
			}
		}
		
		return logTagList;
	}
	
	/**
	 * 对Map value 降序排序
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public static Map.Entry[] getSortedHashtableByValue(Map map) {
        Set set = map.entrySet();
        Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
                .size()]);
        Arrays.sort(entries, new Comparator() {
            public int compare(Object arg0, Object arg1) {
                Long key1 = Long.valueOf(((Map.Entry) arg0).getValue()
                        .toString());
                Long key2 = Long.valueOf(((Map.Entry) arg1).getValue()
                        .toString());
                return key2.compareTo(key1);
            }
        });

        return entries;
    }
	
	/**
	 * 获取每一个标签的权值
	 * @param logTagList
	 * @return
	 */
	public Map<LogTag,Double> countLogTagListWeight(List<LogTag> logTagList){
		/**
		 * 思路：
		 * 1、获取标签的关注数量
		 * 2、获取标签的点击数量
		 * 3、获取标签对应的日志数量
		 * 将这些数量值相加，（暂时通过相加的方式）
		 */
		//List<Map<LogTag,Double>> logTagWeightList = new ArrayList<Map<LogTag,Double>>();
		Map<LogTag,Double> mapWeight = new HashMap<LogTag, Double>();
		
		for(LogTag logTag : logTagList){
			
			//mapWeight = new HashMap<LogTag, Double>();
			mapWeight.put(logTag, getLogTagWeight(logTag));
			//logTagWeightList.add(mapWeight);
		}
		return mapWeight;
		//return logTagWeightList;
	}
	
	/**
	 * 获取每个标签的权值
	 * @param logTag
	 * @return
	 */
	public double getLogTagWeight(LogTag logTag){
		DecimalFormat df=new DecimalFormat(".##");
		double weight = 0.0;
		int clickNum=0;
		int logInfoNum=0;
		int tagAttention=0;
		clickNum = tagClickCountServiceImpl.getTagClickNum(logTag.getTagName());
		logInfoNum = logTag.getLogInfos().size();
		tagAttention = tagServiceImpl.getTagAttentionNum(logTag.getTagName());
		weight = Double.parseDouble(df.format((clickNum+logInfoNum+tagAttention)/3.0));
		return weight;
	}
	
	/**
	 * 获取标签被订阅的数量
	 * @param logTag
	 * @return
	 */
	/*public int getLogTagAttentionNum(LogTag logTag){
		return 0;
	}*/
	
	/**
	 * 获取每一篇日志的权值
	 * @param logInfoList
	 * @return
	 */
	public Map<LogInfo,Double> countLogInfoListWeight(List<LogInfo> logInfoList){
		return null;
	}
	
	/**
	 * 获取某一篇日志的权值
	 * @param logInfo
	 * @return
	 */
	public double getLogInfoWeight(LogInfo logInfo){
		
		return 0.0;
	}
	
	/**
	 * 获取热门空间用户
	 * @param tag
	 * @return
	 */
	public List<UserLogin> getHotUserLoginList(Tag tag){
		
		return null;
	}
}
