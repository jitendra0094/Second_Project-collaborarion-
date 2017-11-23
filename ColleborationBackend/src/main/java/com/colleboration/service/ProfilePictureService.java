package com.colleboration.service;

import com.colleboration.model.ProfilePicture;

public interface ProfilePictureService {

void saveProfilePicture(ProfilePicture profilePicture);
	
	ProfilePicture getProfilePicture(String username);


}
