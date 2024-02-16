package com.example.Enveu.Bloggers.services;

import java.util.List;

import com.example.Enveu.Bloggers.entities.Category;
import com.example.Enveu.Bloggers.entities.Post;
import com.example.Enveu.Bloggers.entities.User;
import com.example.Enveu.Bloggers.payloads.PostDto;
import com.example.Enveu.Bloggers.payloads.PostResponse;

public interface PostService {
  
     PostDto createPost(PostDto postDto,int userId,int catId);
	   
     PostDto updatePost(PostDto dto,int postId);
     
     void deletePost(int postId);
     
     PostResponse getAllPostByPage(int pageNumber,int pageSize,String sortBy);
     List<PostDto> getAllPost();
     PostDto getPostById(int postId);
     
     List<PostDto> getPostByCategory(int categoryId);
     
     List<PostDto> getPostByUser(int userId);
     
     List<PostDto> searchPost(String keyWord);
}
