package com.colleboration.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.colleboration.model.Error;
import com.colleboration.model.Friend;
import com.colleboration.model.User;
import com.colleboration.service.FriendService;

@RestController
public class FriendController {
	
	@Autowired
	private FriendService friendService;
	@RequestMapping(value="/getSuggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?> getSuggestedUsers(HttpSession session){
		String username=(String)session.getAttribute("username");
		if(username==null){
			Error error= new Error(5,"Unauthorised access");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
			
		}
		List<User> suggestedUsers=friendService.listOfSuggestedUsers(username);
		return new ResponseEntity<List<User>>(suggestedUsers, HttpStatus.OK);
	}
	
	public ResponseEntity<?> friendRequest(@PathVariable String toId,HttpSession session){
		String username= (String )session.getAttribute("username");
		if(username==null){
			Error error= new Error(5,"Unauthorised access");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
			
	}
		Friend friend= new Friend();
		friend.setFromId(username);
		friend.setToId(toId);
		friend.setStatus('P');
		friendService.friendRequest(friend);//insert into friend table
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);

}
	@RequestMapping(value="/pendingrequest",method=RequestMethod.GET)
	public ResponseEntity<?> pendingRequest(HttpSession session){
		String username= (String)session.getAttribute("username");
		if(username==null){
			Error error= new Error(5,"Unauthorised access");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		}
		
		List<Friend> pendingRequest=friendService.pendingRequest(username);
		return new ResponseEntity<List<Friend>>(pendingRequest,HttpStatus.OK);
		
	}
	

	@PutMapping(value="/updatependingrequest")
	public ResponseEntity<?>updatePendingRequest(@RequestBody Friend friend, HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		System.out.println(friend.getFromId() + " " + friend.getStatus());
		friendService.updateFriendRequest(friend);   //update status to A or Delete the record.....
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		
	}	
	
	
	@RequestMapping(value="/friendslist",method=RequestMethod.GET)
	public ResponseEntity<?>listOfFriends(HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<String> friends=friendService.listOfFriends(username);
		return new ResponseEntity<List<String>>(friends, HttpStatus.OK);
	}
	
	
	

}
