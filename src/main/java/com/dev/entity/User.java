package com.dev.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User implements Serializable{

	@Id
	@GeneratedValue
	private Long idUser;
	private String nomtest;
	private String nomUser;
	private String prenomUser;
	private int ageUser;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	///////////////////////////////////
	public User(){
		
	}
	public User(String nomUser, String prenomUser, int ageUser) {
		super();
		this.nomUser = nomUser;
		this.prenomUser = prenomUser;
		this.ageUser = ageUser;
	}
	
	///////////////////////////////////
	
	public Long getIdUser() {
		return idUser;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	public String getPrenomUser() {
		return prenomUser;
	}
	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}
	public int getAgeUser() {
		return ageUser;
	}
	public void setAgeUser(int ageUser) {
		this.ageUser = ageUser;
	}
	
	
}
