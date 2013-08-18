/**
 * File	Name   : LogService.java
 * Package     : org.com.lutongnet.jfl.service
 * Description : TODO
 * Author      : zhangfj
 * Date        : 2012-9-19
 * Version     : V1.0		
 */
package com.lutongnet.test.spring.service;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lutongnet.test.spring.entity.ActionLog;
import com.lutongnet.test.spring.req.LogReq;
import com.lutongnet.test.spring.util.PaginationBean;

/**
 * @author 凌剑�?
 */
@Service ( "logService" )
@Transactional
@Repository
public class LogService extends BaseService {

	/**
	 * 获取日志信息
	 * 
	 * @param id
	 * @return
	 */
	@Transactional ( propagation = Propagation.SUPPORTS )
	public ActionLog get ( Integer id ) {
		return em.createQuery ( "from ActionLog a left join fetch a.detail where a.id=" + id , ActionLog.class ).getSingleResult ( );
	}

	/**
	 * 添加日志
	 * 
	 * @param actionLog
	 */
	public void add ( ActionLog actionLog ) {
		em.persist ( actionLog );
	}

	/**
	 * 分页查询日志
	 * 
	 * @param req
	 * @return
	 */
	@Transactional ( propagation = Propagation.SUPPORTS )
	public PaginationBean list ( LogReq req ) {
		String countHQL = "select count(id) from ActionLog";
		String dataHQL = "from ActionLog";
		String condition = " where 1=1";
		Map<String, Object> params = new HashMap<String, Object> ( );
		String actor = req.getActor ( );
		String content = req.getContent ( );
		DateTime from = req.getFrom ( );
		DateTime to = req.getTo ( );
		String type = req.getType ( );

		if ( StringUtils.hasText ( actor ) ){
			condition += " and actor like :actor";
			params.put ( "actor" , "%" + actor + "%" );
		}
		if ( StringUtils.hasText ( content ) ){
			condition += " and content like :content";
			params.put ( "content" , "%" + content + "%" );
		}
		if ( from != null ){
			condition += " and addDate>=:from";
			params.put ( "from" , from );
		}
		if ( to != null ){
			condition += " and addDate<=:to";
			params.put ( "to" , to );
		}
		if ( StringUtils.hasText ( type ) ){
			condition += " and type=:type";
			params.put ( "type" , type );
		}
		return findByPage ( countHQL + condition , dataHQL + condition + " order by id desc" , params , req.getCurrent ( ) , req.getPageSize ( ) ,
				ActionLog.class );
	}
}
