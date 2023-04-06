package com.xworkz.vinayhp.controller;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.xworkz.vinayhp.dto.UserDTO;
import com.xworkz.vinayhp.service.CMSignUpService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
@EnableWebMvc
public class CMSignUpController {

	@Autowired
	private CMSignUpService service;

	public CMSignUpController() {
		log.info("Created " + this.getClass());
	}

	@GetMapping("/register")
	public String onSave() {
		log.info("@GetMapping onSave in CMSignUpController");
		return "SignUp";
	}

	@PostMapping("/register")
	public String onSave(UserDTO dto, Model model) {
		log.info("@PostMapping onSave in CMSignUpController" + dto);
		dto.setCreatedBy(dto.getUserId());
		dto.setCreatedOn(LocalDateTime.now());
		Set<ConstraintViolation<UserDTO>> violations = this.service.validateAndSave(dto);
		if (violations.isEmpty()) {
			log.info("No violations in dto, going to save in database" + dto);
			
			// To send mail
			boolean sendMail = this.service.sendEmail(dto);
			log.info("sendMail : "+sendMail);
			
			dto.setPassword(null);
			model.addAttribute("dto", dto);
			model.addAttribute("success", "Successfully Registered");
			if(sendMail) {
			model.addAttribute("emailConfirmation", "an confirmation email is send to : "+dto.getEmail());
			}
			return "SignUp";
		}
		log.info("Violations in onSave, the dto  : " + dto);
		model.addAttribute("dto", dto);
		model.addAttribute("errors", violations);
		return "SignUp";
	}

}
