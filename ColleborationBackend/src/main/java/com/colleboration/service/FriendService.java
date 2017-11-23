package com.colleboration.service;

import java.util.List;

import com.colleboration.model.Friend;
import com.colleboration.model.User;

public interface FriendService {
	
List<User> listOfSuggestedUsers(String username);
	
	void friendRequest(Friend friend);

	List<Friend> pendingRequest(String username);
	
	void updateFriendRequest(Friend friend);

	List<String> listOfFriends(String username);


}
