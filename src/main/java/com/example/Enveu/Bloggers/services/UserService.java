package com.example.Enveu.Bloggers.services;

import java.util.List;

import com.example.Enveu.Bloggers.payloads.UserDto;

public interface UserService {
   UserDto createUser(UserDto userDto) ;
   UserDto updateUSer(UserDto userDto,int id);
   UserDto getUserById(int userId);
   List<UserDto> getAllUSer();
   void deleteUser(int userId);
}
