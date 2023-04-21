package com.xworkz.vinayhp.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.xworkz.vinayhp.dto.TechnolgyListDTO;
import com.xworkz.vinayhp.dto.UserDTO;
import com.xworkz.vinayhp.service.CMSignInService;
import com.xworkz.vinayhp.service.TechnologyService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
@EnableWebMvc
public class TechnologyController {

	@Autowired
	private TechnologyService service;
	
	@Autowired
	private CMSignInService signInService;

	public TechnologyController() {
		log.info("Created :" + this.getClass());
	}

	@GetMapping("/addtechnology")
	public String addTechnology(UserDTO dto, Integer id, Model model) {
		log.info("@GetMapping(/addtechnology), id = " + id);
		model.addAttribute("id", id);
		return "AddTechnology";
	}

	@PostMapping("/addtechnology")
	public String addTechnology(TechnolgyListDTO dto, Integer id, Model model) {
		log.info("@PostMapping(/addtechnology)" + dto + " id = " + id);
		dto.setId(id);
		Set<ConstraintViolation<TechnolgyListDTO>> violations = this.service.validateAndAdd(dto);
		if (violations.isEmpty()) {
			log.info("There is no violations can add a technology");
			model.addAttribute("id", id);
			model.addAttribute("addTechSuccess", "Successfully added the technology : " + dto.getName());
			return "AddTechnology";
		}
		log.info("Violations in the technology, can't add it");
		model.addAttribute("errors", violations);
		model.addAttribute("id", id);
		return "AddTechnology";
	}
	
	@SuppressWarnings("null")
	@PostMapping("/listtechnology")
	public String listTechnology(Integer id, Model model) {
		log.info("listTechnology "+id);
		UserDTO dto = this.signInService.findById(id);
		model.addAttribute("dto",dto);
		model.addAttribute("id", id);
		List<TechnolgyListDTO> dtos = this.service.findById(id);
		if(dtos.isEmpty()) {
			log.info("There is no technologies added");
			model.addAttribute("emptyTechnologies","There is no technologies added");
			return "SignInSuccessPage";
		}
		log.info("dtos.size()"+dtos.size());
		model.addAttribute("dtos", dtos);
		return "TechnologiesList";
	}

}
