package com.example.Enveu.Bloggers.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Enveu.Bloggers.entities.Category;
import com.example.Enveu.Bloggers.entities.Post;
import com.example.Enveu.Bloggers.entities.User;
import com.example.Enveu.Bloggers.exception.ResourceNotFoundException;
import com.example.Enveu.Bloggers.payloads.PostDto;
import com.example.Enveu.Bloggers.payloads.PostResponse;
import com.example.Enveu.Bloggers.repositry.CategoryRepo;
import com.example.Enveu.Bloggers.repositry.PostRepo;
import com.example.Enveu.Bloggers.repositry.UserRepo;
import com.example.Enveu.Bloggers.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
    
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto updatePost(PostDto dto, int postId) {
		Post p=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post is not bound ","postId",postId));
         p.setTitle(dto.getTitle());
         p.setContent(dto.getContent());
         p.setImageName(dto.getImageName());
         Post updatePost=this.postRepo.save(p);
         
         
		return this.modelMapper.map(updatePost,PostDto.class);
	}

	@Override
	public void deletePost(int postId) {
		Post p=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post is not bound ","postId",postId));
		 this.postRepo.delete(p);
	}

	@Override
	public PostResponse getAllPostByPage(int pageNumber,int pageSize,String sortBy) {
		Pageable page=PageRequest.of(pageNumber,pageSize,Sort.by(sortBy).descending());
		Page<Post> p= this.postRepo.findAll(page);
	    List<Post> p1=p.getContent();
	    List<PostDto>p2=p1.stream().map(s->this.modelMapper.map(s, PostDto.class)).collect(Collectors.toList());
	    
	    PostResponse postResponse=new PostResponse();
	    postResponse.setContent(p2);
	    postResponse.setPageNumber(p.getNumber());
	    postResponse.setLastPage(p.isLast());
	    postResponse.setPageSize(p.getSize());
	    postResponse.setTotalPages(p.getTotalPages());
	    postResponse.setTotalElement(p.getTotalElements());
		return postResponse;
	}

	@Override
	public PostDto getPostById(int postId) {
		Post p=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post is not bound ","postId",postId));
		return this.modelMapper.map(p, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(int categoryId) {
	    Category c=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category Not Found","Cat Id",categoryId));
         List<Post> findByCategory=this.postRepo.findByCategory(c);
         
         List<PostDto>p=findByCategory.stream().map(s-> this.modelMapper.map(s, PostDto.class)).collect(Collectors.toList());
      
		return p;
	}

	@Override
	public List<PostDto> getPostByUser(int userId) {
		User u=this.userRepo.findById(userId).orElseThrow(()-> new  ResourceNotFoundException("User not found","userId",userId));
		 List<Post> findByCategory=this.postRepo.findByUser(u);
		 List<PostDto>p=findByCategory.stream().map(s-> this.modelMapper.map(s, PostDto.class)).collect(Collectors.toList());
		return p;
	}

	@Override
	public PostDto createPost(PostDto postDto, int userId, int catId) {
	
		User u=this.userRepo.findById(userId).orElseThrow(()-> new  ResourceNotFoundException("User not found","userId",userId));
	    Category c=this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category Not Found","Cat Id",catId));
	    Post p=this.modelMapper.map(postDto, Post.class);
	    p.setImageName("default.png");
	    p.setAddedDate(new Date());
	    p.setUser(u);
	    p.setCategory(c);
	    Post pd=this.postRepo.save(p);
		return this.modelMapper.map(pd, PostDto.class);
	}


	@Override
	public List<PostDto> getAllPost() {

	    List<Post> p1=this.postRepo.findAll();
	    List<PostDto>p2=p1.stream().map(s->this.modelMapper.map(s, PostDto.class)).collect(Collectors.toList());
		return p2;
	}

	@Override
	public List<PostDto> searchPost(String keyWord) {
	  List<Post>p=this.postRepo.searchByTitle("%"+keyWord+"%");
	  List<PostDto>p2=p.stream().map(s->this.modelMapper.map(s, PostDto.class)).collect(Collectors.toList());
		return p2;
	}

}
