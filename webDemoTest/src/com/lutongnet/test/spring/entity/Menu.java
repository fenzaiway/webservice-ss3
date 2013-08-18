package com.lutongnet.test.spring.entity;

/**
 * File	Name   : Menu.java
 * Package     : org.com.lutongnet.jfl.entity
 * Description : TODO
 * Author      : zhangfj
 * Date        : 2012-9-19
 * Version     : V1.0		
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * @author 凌剑东
 */
@Entity
@Table ( name = "t_menu" )
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1479738306169531185L;

	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Integer						id;

	@ManyToOne
	@JoinColumn ( name = "pid" )
	@JsonBackReference
	private Menu							parent;

	@OneToMany ( cascade = CascadeType.ALL ,  orphanRemoval = true , mappedBy = "parent" )
	@OrderBy ( "position asc" )
	@JsonManagedReference
	private List<Menu>				children					= new ArrayList<Menu> ( );

	private String						name;

	private String						uri;

	private String						style							= "open";

	private int								position;

	private String						description;

	// 标识字段，当用户拥用该菜单的链接地址是值为true;
	@Transient
	private boolean						enable;

	public Integer getId ( ) {
		return id;
	}

	public void setId ( Integer id ) {
		this.id = id;
	}

	public String getName ( ) {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public String getStyle ( ) {
		return style;
	}

	public void setStyle ( String style ) {
		this.style = style;
	}

	public Menu getParent ( ) {
		return parent;
	}

	public void setParent ( Menu parent ) {
		this.parent = parent;
	}

	public String getUri ( ) {
		return uri;
	}

	public void setUri ( String uri ) {
		this.uri = uri;
	}

	public List<Menu> getChildren ( ) {
		return children;
	}

	public void setChildren ( List<Menu> children ) {
		this.children = children;
	}

	public int getPosition ( ) {
		return position;
	}

	public void setPosition ( int position ) {
		this.position = position;
	}

	public String getDescription ( ) {
		return description;
	}

	public void setDescription ( String description ) {
		this.description = description;
	}

	public boolean isEnable ( ) {
		return enable;
	}

	public void setEnable ( boolean enable ) {
		this.enable = enable;
	}
}
