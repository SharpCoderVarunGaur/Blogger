package com.example.Enveu.Bloggers.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Enveu.Bloggers.entities.Category;
import com.example.Enveu.Bloggers.entities.User;
import com.example.Enveu.Bloggers.exception.ResourceNotFoundException;
import com.example.Enveu.Bloggers.payloads.CategoryDto;
import com.example.Enveu.Bloggers.payloads.UserDto;
import com.example.Enveu.Bloggers.repositry.CategoryRepo;
import com.example.Enveu.Bloggers.services.CategoryService;
@Component
public class CategoryServiceImpl implements CategoryService {
 
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
	   Category category=this.dtoToCategory(categoryDto);
	   Category categoryResponse=this.categoryRepo.save(category);  
		return this.categoryToDto(categoryResponse);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> category=this.categoryRepo.findAll();
		 List<CategoryDto> categoryDto=category.stream().map(c->this.categoryToDto(c)).collect(Collectors.toList());
		return categoryDto ;
	}

	@Override
	public CategoryDto getCategoryById(int catId) {
		Optional<Category> cat=this.categoryRepo.findById(catId); 
		
	     if(!cat.isPresent()) {
	    	 throw new ResourceNotFoundException("Category Not Found","Cat Id",catId);
	     }
	     return this.categoryToDto(cat.get());
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int catId) {
		 Optional<Category> c =this.categoryRepo.findById(catId);
	     if(!c.isPresent()) {
	    	 throw new ResourceNotFoundException("Category Not Found","Cat Id",catId);
	     }  
	     Category category=c.get();
	     category.setCategoryTitle(categoryDto.getCategoryTitle());
	     category.setCategoryDescription(categoryDto.getCategoryDescription());
	     Category updatecategory=this.categoryRepo.save(category);
	   return this.categoryToDto(updatecategory);
	}

	@Override
	public void deleteCategory(int catId) {
		Optional<Category>category=this.categoryRepo.findById(catId);
		
	     if(!category.isPresent()) {
	    	 throw new ResourceNotFoundException("Category not found","userId",catId);
	     }
	   
	     this.categoryRepo.delete(category.get());
		
	}
	
	private Category dtoToCategory(CategoryDto categoryDto) {
		  Category category=this.mapper.map(categoryDto, Category.class);
	return category;
	}
	
	private CategoryDto categoryToDto (Category category) {
		CategoryDto categoryDto=this.mapper.map(category, CategoryDto.class);
		  return categoryDto;
	}

}
