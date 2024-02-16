package com.example.Enveu.Bloggers.services;

import java.util.List;

import com.example.Enveu.Bloggers.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	List<CategoryDto> getAllCategory();
	
	CategoryDto getCategoryById(int catId);
	
	CategoryDto updateCategory(CategoryDto categoryDto,int catId);
	
	void deleteCategory(int catId);
	
}
