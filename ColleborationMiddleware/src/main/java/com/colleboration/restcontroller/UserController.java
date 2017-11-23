package com.colleboration.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.colleboration.model.Error;
import com.colleboration.model.User;
import com.colleboration.service.UserService;
@RestController
public class UserController {

	
@Autowired	
private UserService userService;	
  public UserController() {
	  
	
	  	  System.out.println("USERCONTROLLER INSTANTIATED");
	    }
	  	
	      /*
	       *  ? - Any type of data can be returned
	       */



	  @PostMapping(value="/registeruser")
	  public ResponseEntity<?>registeruser(@RequestBody User user)
	  {     //validate username - unique
	  /*	
	  	if(!userService.isUsernameValid(user.getUsername())) { //username is duplicate
	  		Error error=new Error(2, "Username already exists....please enter different username");
	  		return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
	  	}
	  	if(!userService.isEmailValid(user.getEmail())) {
	  		Error error=new Error(3,"Email address already exists....please enter different email");
	  		return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
	  	}
	  	*/
	  	boolean result=userService.registerUser(user);
	   	if(result)
	   	{
	  	return new ResponseEntity<User>(user, HttpStatus.OK);   //200-299
	  	} else
	  	{
	  		Error error=new Error(1,"Unable to register user details");
	  	return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR); //500

	  	}
	  }


	  //Each user unique HttpSession obj will get created
	  @PostMapping(value="/login")
	  public ResponseEntity<?>login(@RequestBody User user,HttpSession session)
	  {
	  	User validUser=userService.login(user);
	   	if(validUser==null) // invalid username/password
	   	{
	   		Error error=new Error(4,"Invalid Username/Password....");
	   	return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED); //401  error 2nd callback function
	  	  //response.data=error and .status=error
	  	}
	   	
	   	System.out.println("ONLINE STATUS BEFORE UPDATE" + validUser); //false
	   	 // update the online status to true
	  		validUser.setOnline(true);
	  		try
	  		{ 
	  		userService.update(validUser);
	  		}
	  		catch(Exception e)
	  		{
	  			Error error=new Error(6,"Unable to update online status");
	  			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	  		}
	  		System.out.println("ONLINE STATUS AFTER UPDATE" + validUser); //true	
	  		session.setAttribute("username", validUser.getUsername());
	  		//username of logged-in user to an attribute 'username'
	  		System.out.println("Username");
	  		
	  	 return new ResponseEntity<User>(validUser, HttpStatus.OK); //success 1st callback function
	        //response.data=validUser,,,,,response.status=200
	  	
	  }  


	  @PutMapping(value="/logout")
	  public ResponseEntity<?> logout(HttpSession session) {
	  	String username=(String)session.getAttribute("username");
	  	System.out.println("Name of the user is" + username);
	  	if(username==null)
	  	{
	  		Error error=new Error(5,"Unauthorized access....plaese login...");
	  		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	  	}
	  	User user=userService.getUserByUsername(username);
	  	user.setOnline(false);
	  	userService.update(user); //online Status is false
	  	session.removeAttribute("username");
	  	System.out.println("logout");
	  	session.invalidate();
	  	return new ResponseEntity<User>(user,HttpStatus.OK);
	  }

	  @GetMapping(value="/getuser")
	  public ResponseEntity<?> getUser(HttpSession session) {
	  	String username=(String)session.getAttribute("username");
	  	if(username==null)
	  	{
	  		Error error=new Error(5,"Unauthorized access....plaese login...");
	  		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	  	}
	  	User user=userService.getUserByUsername(username);
	  	return new ResponseEntity<User>(user,HttpStatus.OK);

	  }


	  @PutMapping(value="/updateuser")
	  public ResponseEntity<?> updateUser(@RequestBody User user,HttpSession session) 
	  {  
	  	String username=(String)session.getAttribute("username");
	  	if(username==null)
	  	{
	  		Error error=new Error(5,"Unauthorized access....plaese login...");
	  		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	  	}
	  	if(!userService.isUpdatedEmailValid(user.getEmail(),user.getUsername())) 
	  	{
	  		Error error=new Error(3,"Email address already exists....please enter different email");
	  		return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
	  	}
	   
	  	try {
	  		userService.update(user);
	  		return new ResponseEntity<User>(user,HttpStatus.OK);
	  	} catch(Exception e) {
	  		
	  		Error error=new Error(4,"Unable to update user details");
	  		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	  	}
	  	}


	  	
	  	
	    @GetMapping("/user")
	    public ResponseEntity<String>testMethosd()
	    {
	  	  
	  	   return new ResponseEntity<String>("Create a User Table", HttpStatus.OK);
	    }

  }
	


