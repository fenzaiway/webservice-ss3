package com.lutongnet.test.spring.util;

/**
 * File Name   : PaginationBean.java
 * Package     : org.com.lutongnet.jfl.util
 * Description : TODO
 * Author      : zhangfj
 * Date        : 2012-9-13
 * Version     : V1.0       
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author 凌剑东
 */
public class PaginationBean {
    private int         current;
    private int         pageCount;
    private int         rowCount;
    private int         pageSize;
    private int         next;
    private int         prev;
    private int         first;
    private int         last;
    private int         start;
    private int         multiDisplayCount;
    private List<?> dataList;

    public PaginationBean ( int current , int rowCount , int pageSize , int multiDisplayCount ) {
        this.current = current;
        this.rowCount = rowCount;
        this.pageSize = pageSize;
        this.multiDisplayCount = multiDisplayCount;
        init ( );
    }

    public PaginationBean ( int current , int rowCount , int pageSize ) {
        this ( current , rowCount , pageSize , 5 );
    }

    private void init ( ) {
        if ( rowCount > 0 ){
            pageCount = ( rowCount - 1 ) / pageSize + 1;
            first = 1;
            last = pageCount;
            current = current < 1 ? 1 : current;
            current = current > pageCount ? pageCount : current;
            prev = current - 1;
            prev = prev < 1 ? 1 : prev;
            next = current + 1;
            next = next > pageCount ? pageCount : next;
            start = ( current - 1 ) * pageSize;
        }else{
            current = 0;
        }
    }

    public int getCurrent ( ) {
        return current;
    }

    public void setCurrent ( int current ) {
        this.current = current;
    }

    public int getPageCount ( ) {
        return pageCount;
    }

    public void setPageCount ( int pageCount ) {
        this.pageCount = pageCount;
    }

    public int getRowCount ( ) {
        return rowCount;
    }

    public void setRowCount ( int rowCount ) {
        this.rowCount = rowCount;
    }

    public int getPageSize ( ) {
        return pageSize;
    }

    public void setPageSize ( int pageSize ) {
        this.pageSize = pageSize;
    }

    public int getNext ( ) {
        return next;
    }

    public void setNext ( int next ) {
        this.next = next;
    }

    public int getPrev ( ) {
        return prev;
    }

    public void setPrev ( int prev ) {
        this.prev = prev;
    }

    public int getFirst ( ) {
        return first;
    }

    public void setFirst ( int first ) {
        this.first = first;
    }

    public int getLast ( ) {
        return last;
    }

    public void setLast ( int last ) {
        this.last = last;
    }

    public List<?> getDataList ( ) {
        return dataList;
    }

    public void setDataList ( List<?> dataList ) {
        this.dataList = dataList;
    }

    public String getShortDisplay ( ) {
        if ( rowCount == 0 ){
            return "没有找到合适的数据！";
        }

        int _rowCount = pageCount;
        int _current = ( current - 1 ) / multiDisplayCount + 1;
        int start = ( _current - 1 ) * multiDisplayCount + 1;
        int end = start + multiDisplayCount - 1;
        end = end > _rowCount ? _rowCount : end;
        String line = "\r\n";
        String html = "<div class=\"pagination pagination-centered\">" + line;
        html += "<ul>" + line;
        if ( current == 1 ){
            html += "<li class=\"disabled\"><a href=\"#\">首页</a></li>" + line;
            html += "<li class=\"disabled\"><a href=\"#\">上页</a></li>" + line;
        }else{
            html += "<li page=\"" + first + "\"><a href=\"#\">首页</a></li>" + line;
            html += "<li page=\"" + prev + "\"><a href=\"#\">上页</a></li>" + line;
        }
        for ( int i = start ; i <= end ; ++i ){
            if ( i == current ){
                html += "<li page=\"" + i + "\" class=\"active\"><a href=\"#\">" + i + "</a></li>" + line;
            }else{
                html += "<li page=\"" + i + "\"><a href=\"#\">" + i + "</a></li>" + line;
            }
        }
        if ( current == pageCount ){
            html += "<li class=\"disabled\"><a href=\"#\">下页</a></li>" + line;
            html += "<li class=\"disabled\"><a href=\"#\">尾页</a></li>" + line;
        }else{
            html += "<li page=\"" + next + "\"><a href=\"#\">下页</a></li>" + line;
            html += "<li page=\"" + last + "\"><a href=\"#\">尾页</a></li>" + line;
        }
        html += "</ul>" + line;
        html += "</div>" + line;

        return html;
    }

    public String getFullDisplay ( ) {
        if ( rowCount == 0 ){
            return "没有找到合适的数据！";
        }

        int _rowCount = pageCount;
        int _current = ( current - 1 ) / multiDisplayCount + 1;
        int start = ( _current - 1 ) * multiDisplayCount + 1;
        int end = start + multiDisplayCount - 1;
        end = end > _rowCount ? _rowCount : end;
        String line = "\r\n";
        String html = "<div class=\"pagination pull-left\">" + line;
        html += "<ul>" + line;
        if ( current == 1 ){
            html += "<li class=\"disabled\"><a href=\"#\">首页</a></li>" + line;
            html += "<li class=\"disabled\"><a href=\"#\">上页</a></li>" + line;
        }else{
            html += "<li page=\"" + first + "\"><a href=\"#\">首页</a></li>" + line;
            html += "<li page=\"" + prev + "\"><a href=\"#\">上页</a></li>" + line;
        }
        for ( int i = start ; i <= end ; ++i ){
            if ( i == current ){
                html += "<li page=\"" + i + "\" class=\"active\"><a href=\"#\">" + i + "</a></li>" + line;
            }else{
                html += "<li page=\"" + i + "\"><a href=\"#\">" + i + "</a></li>" + line;
            }
        }
        if ( current == pageCount ){
            html += "<li class=\"disabled\"><a href=\"#\">下页</a></li>" + line;
            html += "<li class=\"disabled\"><a href=\"#\">尾页</a></li>" + line;
        }else{
            html += "<li page=\"" + next + "\"><a href=\"#\">下页</a></li>" + line;
            html += "<li page=\"" + last + "\"><a href=\"#\">尾页</a></li>" + line;
        }
        html += "</ul>" + line;
        html += "</div>" + line;

        html += "<div class=\"input-prepend input-append pull-right\">" + line;
        html += "<span class=\"add-on\">第" + current + "/" + pageCount + "页，每页显示</span>" + line;
        html += "<input type=\"text\" value=\"" + pageSize + "\" class=\"input-mini pageSize\" style=\"text-align:center\"/>" + line;
        html += "<span class=\"add-on\">条，转到第</span>" + line;
        html += "<input type=\"text\" class=\"input-mini pageNumber\" style=\"text-align:center\"/>" + line;
        html += "<span class=\"add-on\">页</span>" + line;
        html += "<button type=\"button\" class=\"btn gotoPage\">确定</button>" + line;
        html += "</div>" + line;

        return html;
    }

    public int getStart ( ) {
        return start;
    }

    public void setStart ( int start ) {
        this.start = start;
    }

    @Override
    public int hashCode ( ) {
        return super.hashCode ( );
    }

    @Override
    public String toString ( ) {
        String s = "[first:%d, prev:%d, current:%d, next:%d, last:%d, pageCount:%d, rowCount:%d, start:%d, dataList:%s]";
        return String.format ( s , first , prev , current , next , last , pageCount , rowCount , start , dataList );
    }

    public static void main ( String [ ] args ) {
        PaginationBean pb = new PaginationBean ( 14 , 101 , 5 , 5 );
        List<Object> dataList = new ArrayList<Object> ( );
        dataList.add ( "a" );
        dataList.add ( new java.util.Date ( ) );
        pb.setDataList ( dataList );
        System.out.println ( pb );
        System.out.println ( pb.getFullDisplay ( ) );
    }
}