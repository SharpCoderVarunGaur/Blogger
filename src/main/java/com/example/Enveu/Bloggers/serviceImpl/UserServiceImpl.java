package com.example.Enveu.Bloggers.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Enveu.Bloggers.entities.User;
import com.example.Enveu.Bloggers.exception.ResourceNotFoundException;
import com.example.Enveu.Bloggers.payloads.UserDto;
import com.example.Enveu.Bloggers.repositry.UserRepo;
import com.example.Enveu.Bloggers.services.UserService;

@Service
public class UserServiceImpl implements UserService {
  
	@Autowired
	 private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		User user =this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUSer(UserDto userDto, int id) {
       Optional<User> u =this.userRepo.findById(id);
	     if(!u.isPresent()) {
	    	 throw new ResourceNotFoundException("User not found","userId",id);
	     }
	   
       User user=u.get();
       user.setName(userDto.getName());
       user.setEmail(userDto.getEmail());
       user.setAbout(userDto.getAbout());
       user.setPassword(userDto.getPassword());
       User updateUser=this.userRepo.save(user);
	   return this.userToDto(updateUser);
	}

	@Override
	public UserDto getUserById(int userId) {
		Optional<User>user=this.userRepo.findById(userId);
			     if(!user.isPresent()) {
			    	 System.out.println("Not Found");
		    	 throw new ResourceNotFoundException("User not found","userId",userId);
		     }
		   
		 
		return this.userToDto(user.get());
	}

	@Override
	public List<UserDto> getAllUSer() {
		List<User> user=this.userRepo.findAll();
		List<UserDto> userDto=user.stream().map(s-> this.userToDto(s)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(int userId) {
		
		Optional<User>user=this.userRepo.findById(userId);
	
		     if(!user.isPresent()) {
		    	 throw new ResourceNotFoundException("User not found","userId",userId);
		     }
		   
		this.userRepo.delete(user.get());
	}

	private User dtoToUser(UserDto userDto) {
		  User user=this.modelMapper.map(userDto, User.class);
	return user;
	}
	
	private UserDto userToDto (User user) {
		  UserDto userDto=this.modelMapper.map(user, UserDto.class);
		  return userDto;
	}
	

}
