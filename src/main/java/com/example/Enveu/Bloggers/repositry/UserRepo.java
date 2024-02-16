package com.example.Enveu.Bloggers.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Enveu.Bloggers.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

}
