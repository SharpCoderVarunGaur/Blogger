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
import org.springframework.web.bind.annotation.RestController;

import com.example.Enveu.Bloggers.exception.ApiResponse;
import com.example.Enveu.Bloggers.payloads.CategoryDto;
import com.example.Enveu.Bloggers.serviceImpl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
  
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto categoryDto2=this.categoryServiceImpl.createCategory(categoryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto2);
	}
	
	@GetMapping("{catId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int catId){
		CategoryDto categoryDto2=this.categoryServiceImpl.getCategoryById(catId);
		return ResponseEntity.ok(categoryDto2);
	}
	@GetMapping()
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> categoryDto2=this.categoryServiceImpl.getAllCategory();
		return ResponseEntity.ok(categoryDto2);
	}
	@PutMapping("{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable int catId){
		CategoryDto categoryDto2=this.categoryServiceImpl.updateCategory(categoryDto, catId);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto2);
	}
	   @DeleteMapping("{id}")
	     public ResponseEntity<?> deleteCategory(@PathVariable int id){
	    	 this.categoryServiceImpl.deleteCategory(id);
	    	 return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
	    	 
	     }
	     
}
