/**
 * File	Name   : ActionLogDetail.java
 * Package     : org.com.lutongnet.jfl.entity
 * Description : TODO
 * Author      : zhangfj
 * Date        : 2012-9-19
 * Version     : V1.0		
 */
package com.lutongnet.test.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author 凌剑�?
 */
@Entity
@Table ( name = "t_action_log_detail" )
public class ActionLogDetail implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -2355356038018521523L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Integer						id;

	@Lob
	@Column
	private String						content;

	public Integer getId ( ) {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public String getContent ( ) {
		return content;
	}

	public void setContent ( String content ) {
		this.content = content;
	}
}
