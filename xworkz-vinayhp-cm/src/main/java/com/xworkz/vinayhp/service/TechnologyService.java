package com.xworkz.vinayhp.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import com.xworkz.vinayhp.dto.TechnologyListDTO;

public interface TechnologyService {

	Set<ConstraintViolation<TechnologyListDTO>> validateAndAdd(TechnologyListDTO dto);

	default List<TechnologyListDTO> listById(int id){
		return Collections.emptyList();
	}
}
