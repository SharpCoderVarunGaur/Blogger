package com.example.Enveu.Bloggers.payloads;

import com.example.Enveu.Bloggers.entities.Post;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class CommentDto {
	
	private int cId;
	
	private String content;
	
//    private PostDto  post;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

//	public PostDto getPost() {
//		return post;
//	}
//
//	public void setPost(PostDto post) {
//		this.post = post;
//	}

//	public CommentDto(int cId, String content, PostDto post) {
//		super();
//		this.cId = cId;
//		this.content = content;
//		this.post = post;
//	}

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

public CommentDto(int cId, String content) {
	super();
	this.cId = cId;
	this.content = content;
}
    
}
