package com.way.blog.util;

import java.util.List;

import org.springframework.stereotype.Component;

/** *//**

* 分页类，参考自JavaEye及SpringSide

*/

@SuppressWarnings("unchecked")
@Component
public class PaginationSupport{
	
    public final static int PAGESIZE = 5; //每页显示数据数
 
    private int pageSize = PAGESIZE;

    private List items;  //查询列表

    private int totalCount = 0; //总记录数

    private int startIndex = 0;
    
    private int nextIndex = 0;
    
    private int previousIndex = 0;
    
    private int totalPageCount = 0;
    
    private int currentPageNo = 0;
    
    private boolean hasNextPage = false;
    
    private boolean hasPreviousPage = false;
    
    private int lastIndex = 0;
    
    private String pager; //ajax分页条
    
    private String url; ////分页路径
    
    private String pageToolBar; ///普通分页
    
    private String loadMore;///加载更过分页方式
    
    public PaginationSupport(){}; //默认构造函数
    
    public StringBuffer stringBuffer = new StringBuffer();
    
    public StringBuffer getStringBuffer(){
    	
    	StringBuffer sbf = new StringBuffer();
    	sbf.append("&nbsp;&nbsp;转到<input type='text' style='width:35px; hight:20px;' id='pagego' name='pagego'/>")
  	  	.append("<input type='button' onclick='gotopage();' value='确定'/>")
	  	  .append("<script type='text/javascript'>function gotopage()")
	  	  .append("{var pagego=0; pagego=document.getElementsByName('pagego')[0].value; ")
	  	  .append("if(pagego == \"\"){alert('请输入页码'); return false;}")
	  	  .append("var reg = /^[1-9]*[1-9][0-9]*$/;")
	  	  .append("if(!reg.test(pagego)){alert('页码位数字,请正确填写页码'); return false;}")
	  	  .append("var myStartIndex=0;myStartIndex=(pagego*").append(getPageSize()).append("-").append(getPageSize())
	  	  .append("); if(pagego < 1 || myStartIndex > ").append(getTotalCount()).append("){alert('页码范围不正确，请正确填写页码'); return false;}");
    	
    	return sbf;
    }
    
    public PaginationSupport(List items, int totalCount){

        setPageSize(PAGESIZE);

        setTotalCount(totalCount);

        setItems(items);

        setStartIndex(0);
        
        this.init();
    }
    
    public PaginationSupport(List items, int totalCount, int startIndex){

        setPageSize(PAGESIZE);

        setTotalCount(totalCount);

        setItems(items);

        setStartIndex(startIndex);
        
        this.init();
    }

    public PaginationSupport(List items, int totalCount, int pageSize, int startIndex){
        setPageSize(pageSize);

        setTotalCount(totalCount);

        setItems(items);

        setStartIndex(startIndex);
        
        this.init();
    }

    /**
     * 初始化变量，分页
     */
    public void init(){
//    	currentPageNo = getCurrentPageNo();
//        
//        lastIndex = getLastIndex();
//        
//        pageSize = getPageSize();
//        
//        nextIndex = getNextIndex();
//        
//        hasNextPage = hasNextPage();
//        
//        hasPreviousPage = hasPreviousPage();
//        
//        totalPageCount = getTotalCount();
//        
//        previousIndex = getPreviousIndex();
//        
//        startIndex = getStartIndex();
        
    	stringBuffer = getStringBuffer();
        pager = getPager();
        pageToolBar = getPageToolBar();
        loadMore = getLoadMore();
    }
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 判断是否还有下一页，有的话，提供加载更过按钮让用户通过加载更过按钮加载下一页的数据
	 */
	public String getLoadMore(){
		StringBuffer sb = new StringBuffer();
		if(this.hasNextPage){
			sb.append("<button value='' id='loadmore' onclick='loadmore("+this.getNextIndex()+")'>加载更多</button>");
		}else{
			sb.append("精彩内容到此为止！<a href=''>发现关注更过内容</a>");
		}
		return sb.toString();
	}
	
	
	/**
     * ajax分页
     * @return
     */
    public String getPager(){
    	StringBuffer sb = new StringBuffer();
    	sb.append("总页数：").append(getTotalPageCount()).append("&nbsp;&nbsp;&nbsp;总记录数：").append(getTotalCount());
    	sb.append("&nbsp;&nbsp;&nbsp;当前页：").append(getCurrentPageNo()).append("&nbsp;&nbsp;&nbsp;每页显示").append(getPageSize());
    	if(0 != getTotalCount()){
    		sb.append("&nbsp;&nbsp;&nbsp;<a href='javascript:indexPage(0)'>首页</a>");
    	}else{
    		sb.append("&nbsp;&nbsp;&nbsp;首页");
    	}
    	if(hasPreviousPage()){
    		sb.append("&nbsp;&nbsp;&nbsp;<a href='javascript:prePage(").append(getPreviousIndex()).append(")'>[上一页]</a>");
    	}else{
    		sb.append("&nbsp;&nbsp;&nbsp;[上一页]");
    	}
    	if(hasNextPage()){
    		sb.append("&nbsp;&nbsp;&nbsp;<a href='javascript:nextPage(").append(getNextIndex()).append(")'>[下一页]</a>");
    	}else{
    		sb.append("&nbsp;&nbsp;&nbsp;[下一页]");
    	}
    	if(0 != totalPageCount){
    		sb.append("&nbsp;&nbsp;&nbsp;<a href='javascript:endPage(").append(getLastIndex()).append(")'>尾页</a>");
    	}else{
    		sb.append("&nbsp;&nbsp;&nbsp;尾页");
    	}
    	sb.append(stringBuffer.toString()).append("myRequest(myStartIndex);}</script>");
    	return sb.toString();
    }
    
    /**
     * 普通分页
     * @return
     */
    public String getPageToolBar() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("总页数：").append(getTotalPageCount()).append("&nbsp;&nbsp;&nbsp;总记录数：").append(getTotalCount());
    	sb.append("&nbsp;&nbsp;当前页：").append(getCurrentPageNo()).append("&nbsp;&nbsp;每页显示").append(getPageSize());
    	if(0 != getTotalCount()){
    		sb.append("&nbsp;&nbsp;<a href='"+ url +"?startIndex=0'>首页</a>");
    	}else{
    		sb.append("&nbsp;&nbsp;首页");
    	}
    	if(hasPreviousPage()){
    		sb.append("&nbsp;&nbsp;<a href='"+ url +"?startIndex="+getPreviousIndex()+"'>[上一页]</a>");
    	}else{
    		sb.append("&nbsp;&nbsp;[上一页]");
    	}
    	if(hasNextPage()){
    		sb.append("&nbsp;&nbsp;<a href='"+ url +"?startIndex="+getNextIndex()+"'>[下一页]</a>");
    	}else{
    		sb.append("&nbsp;&nbsp;[下一页]");
    	}
    	if(0 != totalPageCount){
    		sb.append("&nbsp;&nbsp;<a href='"+ url +"?startIndex="+getLastIndex()+"'>尾页</a>");
    	}else{
    		sb.append("&nbsp;&nbsp;尾页");
    	}
    	/////跳转到第几页js代码
    	
    	  sb.append(stringBuffer.toString()).append("window.location.href='").append(getUrl()).append("?startIndex='+myStartIndex;").append("}</script>");
    	return sb.toString();
	}

	/** *//**

     * 将页码转换为列表的startIndex，页大小为默认大小

     */

    public static int convertFromPageToStartIndex(int pageNo){

        return (pageNo - 1) * PAGESIZE;

    }

   

    /** *//**

     * 将页码转换为列表的startIndex

     */

    public static int convertFromPageToStartIndex(int pageNo, int pageSize){

        return (pageNo - 1) * pageSize;

    }

    public List getItems(){

        return items;

    }

    public void setItems(List items){

        this.items = items;

    }

    public int getPageSize(){

        return pageSize;

    }

    public void setPageSize(int pageSize){

        this.pageSize = pageSize;

    }

    public int getTotalCount(){

        return totalCount;

    }

    /** *//**

     * 设置数据总数，并计算各页起始位置
	？？？？？会不会影响性能？ask by fenzaiway
     */

    public void setTotalCount(int totalCount){

        if (totalCount > 0){

            this.totalCount = totalCount;

            int count = totalCount / pageSize;

            if (totalCount % pageSize > 0)

                count++;

        } else{

            this.totalCount = 0;

        }

    }

    public int getStartIndex(){
    	
        return startIndex;

    }

    /** *//**

     * 设置当前起始位置

     */

    public void setStartIndex(int startIndex){
        if (totalCount <= 0)

            this.startIndex = 0;

        else if (startIndex >= totalCount)

            this.startIndex = this.getLastIndex();

        else if (startIndex < 0)

            this.startIndex = 0;

        else{

            this.startIndex = startIndex;

        }

    }

    /** *//**

     * 获得下页起始位置

     */

    public int getNextIndex(){

        nextIndex = getStartIndex() + pageSize;

        if (nextIndex >= totalCount)
        	nextIndex = getStartIndex();
            //return getStartIndex();

        //else

            return nextIndex;

    }

    /** *//**

     * 获得上页起始位置

     */

    public int getPreviousIndex(){

        previousIndex = getStartIndex() - pageSize;

        if (previousIndex < 0)
        	previousIndex = 0;
           // return 0;

       // else

            return previousIndex;

    }

    /** *//**

     * 取总页数.

     */

    public int getTotalPageCount(){
    	
        if (totalCount % pageSize == 0)
        	totalPageCount = totalCount / pageSize;
           // return totalCount / pageSize;

        else
        	totalPageCount = totalCount / pageSize + 1;
           // return totalCount / pageSize + 1;
        	return totalPageCount;

    }

    /** *//**

     * 取该页当前页码,页码从1开始.

     */

    public int getCurrentPageNo(){
    	currentPageNo = startIndex / pageSize + 1;
        return currentPageNo;

    }

    /** *//**

     * 该页是否有下一页.

     */

    public boolean hasNextPage(){
    	hasNextPage = this.getCurrentPageNo() <= this.getTotalPageCount() - 1;
        return hasNextPage;

    }

    /** *//**

     * 该页是否有上一页.

     */

    public boolean hasPreviousPage(){
    	hasPreviousPage = this.getCurrentPageNo() > 1;
        return hasPreviousPage;

    }

    /**
     * 
     */
    
    public int getLastIndex(){
    	lastIndex = (this.getTotalPageCount()-1)*this.getPageSize();
    	return lastIndex;
    }

    
}