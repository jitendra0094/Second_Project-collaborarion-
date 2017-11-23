package com.colleboration.service;

import com.colleboration.model.User;

public interface UserService {
	
	public	boolean isUsernameValid(String username);
	   
	   public boolean isEmailValid(String email);
	   
	   public	boolean registerUser(User user);
		
	   public User login(User user);
		 
		 // online status
	   public void update(User user);
		 
		 
	   public User getUserByUsername(String username);

	   public boolean isUpdatedEmailValid(String email, String username);



}
