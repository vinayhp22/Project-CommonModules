package com.xworkz.vinayhp.repository;

import com.xworkz.vinayhp.entity.UserEntity;

public interface CMSignInRepo {

	UserEntity findByUserId(String userId);
	
	default boolean updateAttempts(String userId, int attempts) {
		return false;
	};
	
	default boolean updateLock(String userId) {
		return false;
	};

}
