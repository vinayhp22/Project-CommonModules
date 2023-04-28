package com.xworkz.vinayhp.violations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.xworkz.vinayhp.repository.CMSignUpRepo;
import com.xworkz.vinayhp.util.SpringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailCheckValidator implements ConstraintValidator<EmailCheck, String> {

	public EmailCheckValidator() {
		log.info("Created :" + this.getClass());
	}

	@Override
	public void initialize(EmailCheck constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		log.info("calling isValid, email : " + value);
		CMSignUpRepo repo = SpringUtil.getContext().getBean(CMSignUpRepo.class);
		if (repo != null) {
			Long countByEmail = repo.countByEmail(value);
			log.info("countByEmail : " + countByEmail);
			if (countByEmail == 0) {
				return true;
			}
		}
		return false;
	}

}
