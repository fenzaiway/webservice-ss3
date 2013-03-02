package com.way.blog.ajax.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.way.blog.base.action.BaseAction;

import demo1.ReturnData;
import demo1.ReturnJsonTest;

/**
 * 测试自动下拉补齐插件
 * @author fenzaiway
 *
 */
@Controller("completeAction")
@ParentPackage("json-default")
@Namespace("/ajax/complete")
public class CompleteAction extends BaseAction {
	
	private String[] ReturnData={"中国好声音","中国移动网上营业厅","中国好声音第三期","中国银行","中国好声音第三期"};
	private String[] strs={"sas","bb"};
	
	private String keyword;
	
	@Action(value="data",results={
			@Result(name="success",type="json")
	})
	public String getData(){
		String data="";
		ReturnJsonTest returnJsonTest = new ReturnJsonTest();
		//returnJsonTest
		List<ReturnData> dataList=new ArrayList<ReturnData>();
		ReturnData returnData = null;
		if("中".equals(keyword)||"中国".equals(keyword)){
			//data ="{\"data\":[{\"title\":\"中国好声音\",\"result\":{\"ff\":\"qq\"}},{\"title\":\"中国移动网上营业厅\"},{\"title\":\"中国银行\"},{\"title\":\"中国移动\"},{\"title\":\"中国好声音第三期\"},{\"title\":\"中国好声音 第一期\"},{\"title\":\"中国电信网上营业厅\"},{\"title\":\"中国工商银行\"},{\"title\":\"中国好声音第二期\"},{\"title\":\"中国地图\"}]}";
			
			for(int i =0; i<ReturnData.length;i++){
				returnData = new ReturnData();
				returnData.setTitle(ReturnData[i]);
				dataList.add(returnData);
			}
		}else{
			//data="{\"data\":\"\"}";
			for(int i =0; i<strs.length;i++){
				returnData = new ReturnData();
				returnData.setTitle(strs[i]);
				dataList.add(returnData);
			}
		}
		returnJsonTest.setData(dataList);
		this.returnJsonByObject(returnJsonTest);
		//this.returnJson(data);
		return null;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
/**
 * List data:
 *
 *Show
 * String title;
 * 
 * String result;
 * 
 * toJson(data);
 * 
 */
