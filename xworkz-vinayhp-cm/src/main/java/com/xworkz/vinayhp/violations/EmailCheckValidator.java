package com.xworkz.vinayhp.violations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.xworkz.vinayhp.service.CMSignUpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailCheckValidator implements ConstraintValidator<EmailCheck, String> {

	private CMSignUpService service;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		log.info("calling isValid, email : "+value);
		Long countByEmail;
		try {
			countByEmail = this.service.countByEmail(value);
			if(countByEmail == 0) {
				return true;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;
	}

}
