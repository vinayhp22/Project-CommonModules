package com.xworkz.vinayhp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xworkz.vinayhp.dto.UserDTO;
import com.xworkz.vinayhp.service.CMSignInService;
import com.xworkz.vinayhp.service.CMSignUpService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
@EnableWebMvc
public class CMSignInController {

	@Autowired
	private CMSignInService service;

	public CMSignInController() {
		log.info("Created " + this.getClass());
	}

	@GetMapping("/signIn")
	public String onSignIn() {
		log.info("onSignIn() getMapping");
		return "SignIn";
	}

	@PostMapping("/signIn")
	public String onSignIn(String userId, String password, Model model) {
		log.info("onSignIn() postMapping");
		UserDTO user = service.findByUserId(userId);
		log.info("Attempts : "+user.getAttempts() +" Locked : "+ user.isLocked());
		if (user != null && user.getAttempts() < 3 && !user.isLocked()) {
			user.setAttempts(1);
			if (service.authenticateUser(userId, password)) {
				// correct credentials
				model.addAttribute("userId", userId);
				model.addAttribute("success", "Successfully logged in");
				return "SignIn";
			} else {
				// wrong password
				int attempts = user.getAttempts() + 1;
				service.updateAttempts(userId, attempts);
				model.addAttribute("errors", "Invalid password. You have " + (4 - attempts) + " attempts left.");
			}
		} else if (user != null && user.getAttempts() > 3) {
			// account locked
			service.updateLock(userId);
			model.addAttribute("errors", "Your account has been locked. Please contact the administrator.");
		} else {
			// user not found
			model.addAttribute("errors", "Invalid username.");
		}
		return "SignIn";
	}

}
