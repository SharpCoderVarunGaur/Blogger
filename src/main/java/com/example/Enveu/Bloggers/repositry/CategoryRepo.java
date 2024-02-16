package com.example.Enveu.Bloggers.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Enveu.Bloggers.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
