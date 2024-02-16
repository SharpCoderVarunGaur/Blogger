package com.example.Enveu.Bloggers.services;

import java.util.List;

import com.example.Enveu.Bloggers.payloads.CommentDto;

public interface CommentService  {
 
	CommentDto createComment(CommentDto commentDto,int postId);
	
	CommentDto updateComment(CommentDto commentDto ,int cId);
	List<CommentDto> getAllComment();
	void deleteComment(int cId);
}
