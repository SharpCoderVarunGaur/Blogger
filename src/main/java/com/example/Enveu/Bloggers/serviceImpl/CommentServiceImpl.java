package com.example.Enveu.Bloggers.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Enveu.Bloggers.entities.Comment;
import com.example.Enveu.Bloggers.entities.Post;
import com.example.Enveu.Bloggers.exception.ResourceNotFoundException;
import com.example.Enveu.Bloggers.payloads.CommentDto;
import com.example.Enveu.Bloggers.repositry.CommentRepo;
import com.example.Enveu.Bloggers.repositry.PostRepo;
import com.example.Enveu.Bloggers.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
   
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto,int postId) {
		Post p=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post is not bound ","postId",postId));
		Comment c=this.modelMapper.map(commentDto, Comment.class);
		c.setPost(p);
		Comment savResult=this.commentRepo.save(c);
		System.out.println(savResult.getPost().getPostId());
		return this.modelMapper.map(savResult, CommentDto.class);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, int cId) {
		Comment c=this.commentRepo.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Comment is not found","commentId",cId));
	    
		c.setContent(commentDto.getContent());
		
		Comment updatedComment=this.commentRepo.save(c);
		
		
		// TODO Auto-generated method stub
		return this.modelMapper.map(c, CommentDto.class);
	}

	@Override
	public List<CommentDto> getAllComment() {
		List<Comment> l=this.commentRepo.findAll();
		
		List<CommentDto> l2=l.stream().map(e->this.modelMapper.map(e, CommentDto.class)).collect(Collectors.toList());
		
		return l2;
	}

	@Override
	public void deleteComment(int cId) {
		Comment c=this.commentRepo.findById(cId).orElseThrow(()-> new ResourceNotFoundException("Comment is not found","commentId",cId));
		this.commentRepo.delete(c);
	}

}
