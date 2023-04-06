package com.xworkz.vinayhp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xworkz.vinayhp.service.CMSignUpService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
@EnableWebMvc
public class CMSignInResources {

	@Autowired
	private CMSignUpService service;

	public CMSignInResources() {
		log.info("Created :" + this.getClass());
	}

	@PostMapping(value = "/userId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkUser(@PathVariable String userId) {
		log.info("checkUser(String userId) " +userId);
		Long countByUserId = this.service.countByUserId(userId);
		log.error(""+countByUserId);
		if(countByUserId==0) {
			log.error("Not found similar userId in db, try another..");
			return "User Id is not exist, try another..";
		}else {
			log.info("Found similar userId in db");
			return "";
		}
	}

}
