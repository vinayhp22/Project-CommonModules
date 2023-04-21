package com.xworkz.vinayhp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.vinayhp.dto.TechnolgyListDTO;
import com.xworkz.vinayhp.entity.TechnolgyListEntity;
import com.xworkz.vinayhp.repository.TechnologyRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TechnologyServiceImpl implements TechnologyService {

	@Autowired
	private TechnologyRepo repo;

	@Override
	public Set<ConstraintViolation<TechnolgyListDTO>> validateAndAdd(TechnolgyListDTO dto) {
		log.info("validateAndAdd in TechnologyServiceImpl");

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<TechnolgyListDTO>> violations = validator.validate(dto);
		if (violations != null && !violations.isEmpty()) {
			log.info("Violations in dto" + dto);
			return violations;
		}
		TechnolgyListEntity entity = new TechnolgyListEntity();
		BeanUtils.copyProperties(dto, entity);
		boolean add = this.repo.add(entity);
		if (add) {
			log.info("Technology added successfully");
		}
		return Collections.emptySet();
	}

	@Override
	public List<TechnolgyListDTO> findById(int id) {
		log.info("findById " + id);
		List<TechnolgyListEntity> entities = this.repo.listById(id);
		log.info("entities.size() : " + entities.size());
		List<TechnolgyListDTO> dtos = new ArrayList<>();
		if (!entities.isEmpty()) {
			for (TechnolgyListEntity entity : entities) {
				TechnolgyListDTO dto = new TechnolgyListDTO();
				BeanUtils.copyProperties(entity, dto);
				dtos.add(dto);
			}
			log.info("dtos.size() : " + dtos.size());
			return dtos;
		}
		return TechnologyService.super.findById(id);
	}
}
