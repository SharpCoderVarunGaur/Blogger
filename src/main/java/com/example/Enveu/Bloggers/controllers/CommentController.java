package com.example.Enveu.Bloggers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Enveu.Bloggers.exception.ApiResponse;
import com.example.Enveu.Bloggers.payloads.CommentDto;
import com.example.Enveu.Bloggers.serviceImpl.CommentServiceImpl;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	@PostMapping("post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable int postId){
	   CommentDto commDto2=this.commentServiceImpl.createComment(commentDto, postId);
	   return new ResponseEntity<CommentDto>(commDto2,HttpStatus.CREATED);
	}
	@GetMapping()
	public ResponseEntity<List<CommentDto>> getAllComment(){
	  List<CommentDto> commDto2=this.commentServiceImpl.getAllComment();
	   return ResponseEntity.ok(commDto2);
	}
	@DeleteMapping("{cId}")
	public ResponseEntity<?>deleteComment(@PathVariable int cId){
		this.commentServiceImpl.deleteComment(cId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment SuccesFully Deleted",true),HttpStatus.OK);
	}

}
