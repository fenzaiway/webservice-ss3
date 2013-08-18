/**
 * File	Name   : ActionLog.java
 * Package     : org.com.lutongnet.jfl.entity
 * Description : TODO
 * Author      : zhangfj
 * Date        : 2012-9-19
 * Version     : V1.0		
 */
package com.lutongnet.test.spring.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * @author 凌剑�?
 */
@Entity
@Table ( name = "t_action_log" )
public class ActionLog implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -603867518103501502L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Integer						id;

	private String						type;

	private String						actor;

	private String						content;

	private String						uri;

	private String						param;

	@Type ( type = "org.joda.time.contrib.hibernate.PersistentDateTime" )
	@Column ( name = "add_date" )
	private DateTime					addDate;

	@OneToOne ( cascade = CascadeType.ALL , fetch = FetchType.LAZY )
	@JoinColumn ( name = "detail_id" )
	private ActionLogDetail		detail;

	public ActionLog ( ) {
		super ( );
	}

	public Integer getId ( ) {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public String getType ( ) {
		return type;
	}

	public void setType ( String type ) {
		this.type = type;
	}

	public String getContent ( ) {
		return content;
	}

	public void setContent ( String content ) {
		this.content = content;
	}

	public DateTime getAddDate ( ) {
		return addDate;
	}

	public void setAddDate ( DateTime addDate ) {
		this.addDate = addDate;
	}

	public ActionLogDetail getDetail ( ) {
		return detail;
	}

	public void setDetail ( ActionLogDetail detail ) {
		this.detail = detail;
	}

	public String getActor ( ) {
		return actor;
	}

	public void setActor ( String actor ) {
		this.actor = actor;
	}

	public String getUri ( ) {
		return uri;
	}

	public void setUri ( String uri ) {
		this.uri = uri;
	}

	public String getParam ( ) {
		return param;
	}

	public void setParam ( String param ) {
		this.param = param;
	}
}
