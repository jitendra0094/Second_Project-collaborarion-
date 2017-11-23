package com.colleboration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colleboration.dao.ProfilePictureDao;
import com.colleboration.model.ProfilePicture;

@Service
public class ProfilePictureServiceImpl implements ProfilePictureService {

	@Autowired
	private ProfilePictureDao profilePictureDao;

	public void saveProfilePicture(ProfilePicture profilePicture) {
		profilePictureDao.save(profilePicture);
		
	}

	public ProfilePicture getProfilePicture(String username) {
		 return profilePictureDao.getProfilePicture(username);
	}
}
