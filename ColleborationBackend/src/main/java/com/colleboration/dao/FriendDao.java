package com.colleboration.dao;

import java.util.List;

import com.colleboration.model.Friend;
import com.colleboration.model.User;


public interface FriendDao {

	List<User> listofSuggestedUsers(String username);  //logged in username

	void friendRequest(Friend friend);

	List<Friend> pandingRequests(String toId);


	void updatePendingRequest(Friend friend);

	List<String> listOfFriends(String username);

}
