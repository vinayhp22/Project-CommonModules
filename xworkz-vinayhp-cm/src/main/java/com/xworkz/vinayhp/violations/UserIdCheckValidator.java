package com.xworkz.vinayhp.violations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.xworkz.vinayhp.service.CMSignUpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserIdCheckValidator implements ConstraintValidator<UserIdCheck, String> {

	private CMSignUpService service;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		log.info("calling isValid, userId : "+value);
		Long countByUserId;
		try {
			countByUserId = service.countByUserId(value);
			if(countByUserId == 0) {
				return true;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;
	}

}
