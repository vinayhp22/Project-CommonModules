package com.xworkz.vinayhp.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.vinayhp.dto.UserDTO;
import com.xworkz.vinayhp.entity.UserEntity;
import com.xworkz.vinayhp.repository.CMSignInRepo;
import com.xworkz.vinayhp.repository.CMSignUpRepo;
import com.xworkz.vinayhp.repository.CMUpdateProfileRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CMUpdateProfileServiceImpl implements CMUpdateProfileService {

	@Autowired
	private CMUpdateProfileRepo repo;

	@Autowired
	private CMSignUpRepo signUpRepo;

	@Autowired
	private CMSignInRepo signInRepo;

	@Override
	public Set<ConstraintViolation<UserDTO>> validateAndUpdate(UserDTO dto) {
		log.info("validateAndUpdate in CMSignUpServiceImpl" + dto);

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		UserEntity dtoByEmail = this.signInRepo.findByEmail(dto.getEmail());
		if (!dto.getMobile().equals(dtoByEmail.getMobile()) && !dto.getUserId().equals(dtoByEmail.getUserId())) {
			Set<ConstraintViolation<UserDTO>> violations = validator.validate(dto);

			if (violations != null && !violations.isEmpty()) {
				log.info("Violations in dto" + dto);
				return violations;
			}
		}
		log.info("There is no violations in dto, can update" + dto);
		dto.setUpdatedBy(dto.getUserId());
		dto.setUpdatedOn(LocalDateTime.now());
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(dto, entity);

		boolean saved = this.repo.update(entity);
		log.info("Entity" + entity + "; saved : " + saved);
		return Collections.emptySet();
	}

}
