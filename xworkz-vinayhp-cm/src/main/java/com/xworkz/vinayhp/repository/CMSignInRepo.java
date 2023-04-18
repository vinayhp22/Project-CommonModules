package com.xworkz.vinayhp.repository;

import com.xworkz.vinayhp.dto.UserDTO;
import com.xworkz.vinayhp.entity.UserEntity;

public interface CMSignInRepo {

	UserEntity findByUserId(String userId);
	
	default boolean updateAttempts(String userId, int attempts) {
		return false;
	};
	
	default boolean updateLock(String userId) {
		return false;
	};
	
	default UserEntity findByEmail(String email) {
		return null;
	}
	
	default boolean updateRandomPassword(UserEntity entity){
		return false;
	}
	
	default boolean updatePassword(UserEntity entity){
		return false;
	}

}
