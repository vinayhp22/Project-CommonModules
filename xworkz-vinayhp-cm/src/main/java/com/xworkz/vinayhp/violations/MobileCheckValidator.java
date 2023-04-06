package com.xworkz.vinayhp.violations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.xworkz.vinayhp.service.CMSignUpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MobileCheckValidator implements ConstraintValidator<MobileCheck, Long> {

	private CMSignUpService service;
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		log.info("calling isValid, mobile : "+value);
		Long countByMobile;
		try {
			countByMobile = this.service.countByMobile(value);
			if(countByMobile == 0) {
				return true;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;
	}

}
