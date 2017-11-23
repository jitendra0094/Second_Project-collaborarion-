package com.colleboration.dao;

import com.colleboration.model.ProfilePicture;

public interface ProfilePictureDao {
	

	void save(ProfilePicture profilePicture);
	
	ProfilePicture getProfilePicture(String username);


}
