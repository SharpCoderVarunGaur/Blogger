package com.example.Enveu.Bloggers.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Enveu.Bloggers.exception.ApiResponse;
import com.example.Enveu.Bloggers.payloads.UserDto;
import com.example.Enveu.Bloggers.serviceImpl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/User")
public class UserController {
    
	@Autowired
	private UserServiceImpl userServiceImpl;
	 
	
	private final Logger looger=LoggerFactory.getLogger(UserController.class);
     @PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
    	  looger.info("this is payload of create user:{}",userDto);
    	  looger.warn("this is payload of create user:{}",userDto);
    	 UserDto u=this.userServiceImpl.createUser(userDto); 
		System.out.println("Hello Create User we are their");
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
     @GetMapping("{id}")
     public ResponseEntity<UserDto> getById(@PathVariable int id){
    	 UserDto u=this.userServiceImpl.getUserById(id);
    	 return ResponseEntity.ok(u);
     }
     
     @PutMapping("{id}")
     public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto ,@PathVariable int id){
    	 UserDto updateUser=this.userServiceImpl.updateUSer(userDto, id);
    	 return ResponseEntity.ok(updateUser);
    	 
     }
     
     @GetMapping()
     public ResponseEntity<List<UserDto>> getAllUser(){
    	 List<UserDto> allUser =this.userServiceImpl.getAllUSer();
    	 return ResponseEntity.ok(allUser);
    	 
     }
     @DeleteMapping("{id}")
     public ResponseEntity<?> deleteUser(@PathVariable int id){
    	 this.userServiceImpl.deleteUser(id);
    	 return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    	 
     }
     
     
     
}
