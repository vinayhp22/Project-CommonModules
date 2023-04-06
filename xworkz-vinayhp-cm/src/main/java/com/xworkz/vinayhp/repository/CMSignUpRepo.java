package com.xworkz.vinayhp.repository;

import com.xworkz.vinayhp.entity.UserEntity;

public interface CMSignUpRepo {

	boolean save(UserEntity entity);
	
	default Long countByUserId(String userId) {
		return null;
	}
	
	default Long countByEmail(String email) {
		return null;
	}
	
	default Long countByMobile(Long mobile) {
		return null;
	}
}
