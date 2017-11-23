package com.colleboration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colleboration.dao.FriendDao;
import com.colleboration.model.Friend;
import com.colleboration.model.User;

@Service
public class FriendServiceImpl implements FriendService {
	@Autowired
	private FriendDao friendDao;

	public List<User> listOfSuggestedUsers(String username) {
		
		return friendDao.listofSuggestedUsers(username);
	}

	public void friendRequest(Friend friend) {
		friendDao.friendRequest(friend);
		
	}

	public List<Friend> pendingRequest(String username) {
		
		return friendDao.pandingRequests(username);
	}

	public void updateFriendRequest(Friend friend) {
		friendDao.updatePendingRequest(friend);
		
	}

	public List<String> listOfFriends(String username) {
		return friendDao.listOfFriends(username);
	}

}
