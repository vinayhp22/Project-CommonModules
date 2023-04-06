package com.xworkz.vinayhp.service;

import com.xworkz.vinayhp.dto.UserDTO;

public interface CMSignInService {

	boolean authenticateUser(String userId, String password);
	
	default UserDTO findByUserId(String userId) {
		return null;
	};
	
	default void updateAttempts(String userId, int attempts) {};

	default void updateLock(String userId) {};
		
}