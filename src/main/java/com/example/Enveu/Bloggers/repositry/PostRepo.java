package com.example.Enveu.Bloggers.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Enveu.Bloggers.entities.Category;
import com.example.Enveu.Bloggers.entities.Post;
import com.example.Enveu.Bloggers.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	@Query(value="select * from post where title like :KeyWord",nativeQuery =true )
	List<Post> searchByTitle(@Param(value = "KeyWord") String keyWord);
}
