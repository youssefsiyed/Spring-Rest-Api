package com.dev.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="POST")
//@JsonIgnoreProperties(value="{idPost","nomPost}")
@ApiModel(description="All about Posts Information")
public class Post implements Serializable {
	
	@Id
	@GeneratedValue
	private Long idPost;
	
	@NotNull
	@Size(min=3,max=10,message="the name should be between 1 and 10 caractere !!")
	@Column(name="NomPost",length=10)
	private String nomPost;
	
	@ApiModelProperty(notes="the description is not requirred")
	@Column(name="DescriptionPost")
	private String descrptionPost;
	
	//@JsonIgnore
	@ApiModelProperty(notes="the date most be correct format")
	@Column(name="DatePost")
	private Date datePost;
	
	@ApiModelProperty(notes="the user most be use")
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	//////////////////////////////////////////////////////////////
	public Post () {
		
	}
	/////////////////////////////////////////////////////////
	public Post(String nomPost, String descrptionPost, Date datePost) {
		super();
		this.nomPost = nomPost;
		this.descrptionPost = descrptionPost;
		this.datePost = datePost;
		//this.user = user;
	}
	/////////////////////////////////////////////////////////////
	
	public Long getIdPost() {
		return idPost;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}
	public String getNomPost() {
		return nomPost;
	}
	public void setNomPost(String nomPost) {
		this.nomPost = nomPost;
	}
	public String getDescrptionPost() {
		return descrptionPost;
	}
	public void setDescrptionPost(String descrptionPost) {
		this.descrptionPost = descrptionPost;
	}
	public Date getDatePost() {
		return datePost;
	}
	public void setDatePost(Date datePost) {
		this.datePost = datePost;
	}
	
	//////////////////////////////////////////////////////
	
	
}
