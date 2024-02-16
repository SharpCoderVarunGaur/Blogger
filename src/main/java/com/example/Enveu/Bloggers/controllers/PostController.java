package com.example.Enveu.Bloggers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Enveu.Bloggers.exception.ApiResponse;
import com.example.Enveu.Bloggers.payloads.PostDto;
import com.example.Enveu.Bloggers.payloads.PostResponse;
import com.example.Enveu.Bloggers.serviceImpl.PostServiceImpl;

@RestController
@RequestMapping("/api/Post")
public class PostController {
   
	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@PostMapping("user/{userId}/category/{catId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,@PathVariable int userId,@PathVariable int catId){
           PostDto p=this.postServiceImpl.createPost(postDto, userId, catId);
           return new ResponseEntity<PostDto>(p,HttpStatus.CREATED);
	}
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId){
		List<PostDto> p=this.postServiceImpl.getPostByUser(userId);
		return ResponseEntity.ok(p);
	}
	@GetMapping("category/{catId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int catId){
		List<PostDto> p=this.postServiceImpl.getPostByCategory(catId);
		return ResponseEntity.ok(p);
	}
	@GetMapping("/page")
	public ResponseEntity<PostResponse> getAllPostByPage(@RequestParam(value="pageNumber",defaultValue = "0",required = false) int pageNumber,@RequestParam(value="pageSize",defaultValue ="10",required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue="postId",required=false)String sortBy ){
		PostResponse p=this.postServiceImpl.getAllPostByPage(pageNumber,pageSize,sortBy);
		return ResponseEntity.ok(p);
	}
	@GetMapping()
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto> p=this.postServiceImpl.getAllPost();
		return ResponseEntity.ok(p);
	}
	@GetMapping("{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int postId){
           PostDto p=this.postServiceImpl.getPostById(postId);
           return ResponseEntity.ok(p);
	}
	@DeleteMapping("{postId}")
	public ResponseEntity<?> deletePost(@PathVariable int postId){
           this.postServiceImpl.deletePost(postId);
           return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
      	 
	}
	@PutMapping("{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable int postId){
           PostDto p=this.postServiceImpl.updatePost(postDto, postId);
           return new ResponseEntity<PostDto>(p,HttpStatus.CREATED);
	}
	@GetMapping("/search/{keyWord}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyWord){
		List<PostDto> p=this.postServiceImpl.searchPost(keyWord);
		return new ResponseEntity<List<PostDto>>(p,HttpStatus.OK);
	}
}
