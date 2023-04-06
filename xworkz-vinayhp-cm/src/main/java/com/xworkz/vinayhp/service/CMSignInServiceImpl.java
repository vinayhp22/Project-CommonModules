package com.xworkz.vinayhp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xworkz.vinayhp.dto.UserDTO;
import com.xworkz.vinayhp.entity.UserEntity;
import com.xworkz.vinayhp.repository.CMSignInRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CMSignInServiceImpl implements CMSignInService {

	@Autowired
	private CMSignInRepo repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	int attemptCount = 0;

	@Override
	public boolean authenticateUser(String userId, String password) {
		log.info("authenticateUser(String userId, String password) userId:" + userId + " Password:  " + password);
		UserEntity user = this.repo.findByUserId(userId);

		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public UserDTO findByUserId(String userId) {
		UserEntity entity = repo.findByUserId(userId);
		if (entity != null) {
			UserDTO dto = new UserDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return CMSignInService.super.findByUserId(userId);
	}

	@Override
	public void updateAttempts(String userId, int attempts) {
		repo.updateAttempts(userId, attempts);
	}
	
	@Override
	public void updateLock(String userId) {
		repo.updateLock(userId);
	}

}
