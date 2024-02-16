package com.example.Enveu.Bloggers.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {
	
   private int id;
   
   @NotNull
   private String name;
   @Email
   private String email;
   @NotNull
   private String password;
   
   private String about;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAbout() {
	return about;
}
public void setAbout(String about) {
	this.about = about;
}
public UserDto(int id, String name, String email, String password, String about) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.password = password;
	this.about = about;
}
public UserDto() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
			+ ", getId()=" + getId() + ", getName()=" + getName() + ", getEmail()=" + getEmail() + ", getPassword()="
			+ getPassword() + ", getAbout()=" + getAbout() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
			+ ", toString()=" + super.toString() + "]";
}
   
}
