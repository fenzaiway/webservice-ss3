package com.way.blog.zone.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author fenzaiway
 * 空间关注表
 */
@Entity
@Table(name="tb_attention")
@Repository
public class Attention implements Serializable {
	
	private static final long serialVersionUID = 126287217338287779L;

	/**
	 * 关注表ID
	 */
	@Expose
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name="id",unique = true, nullable = false, insertable = true, updatable = true, length = 11)
	private int id;
	
	/**
	 * 是否关注
	 */
	@Expose
	@Column(name="att_is_attention",unique = false, nullable = false, insertable = true, updatable = true)
	private int isAttention;
	
	/**
	 * 关注时间
	 */
	@Expose
	@Column(name="att_attention_time",unique = false, nullable = true, insertable = true, updatable = true,length=25)
	private String attentionTime;
	
	/**
	 * 取消关注时间
	 */
	@Expose
	@Column(name="att_cancel_attention_time",unique = false, nullable = true, insertable = true, updatable = true,length=25)
	public String cancelAttentionTime;
	
	/**
	 * 关注的空间
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	private BlogZone blogZone;

	public Attention() {}

	public Attention(String attentionTime, BlogZone blogZone,
			String cancelAttentionTime, int id, int isAttention) {
		super();
		this.attentionTime = attentionTime;
		this.blogZone = blogZone;
		this.cancelAttentionTime = cancelAttentionTime;
		this.id = id;
		this.isAttention = isAttention;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(int isAttention) {
		this.isAttention = isAttention;
	}

	public String getAttentionTime() {
		return attentionTime;
	}

	public void setAttentionTime(String attentionTime) {
		this.attentionTime = attentionTime;
	}

	public BlogZone getBlogZone() {
		return blogZone;
	}

	public void setBlogZone(BlogZone blogZone) {
		this.blogZone = blogZone;
	}

	public String getCancelAttentionTime() {
		return cancelAttentionTime;
	}

	public void setCancelAttentionTime(String cancelAttentionTime) {
		this.cancelAttentionTime = cancelAttentionTime;
	}
	
}
