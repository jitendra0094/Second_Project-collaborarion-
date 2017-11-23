package com.colleboration.dao;

import com.colleboration.model.User;

public interface UserDao {
	
	 
    boolean isUsernameValid(String username);	
    
    boolean isEmailValid(String email);
	
	 boolean registerUser(User user);
	 
	 User login(User user);
	 
	 void update(User user);
	 
	 User getUserByUsername(String username);
	 
	 boolean isUpdatedEmailValid(String email, String username);


}
