package com.example.Enveu.Bloggers.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Enveu.Bloggers.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
