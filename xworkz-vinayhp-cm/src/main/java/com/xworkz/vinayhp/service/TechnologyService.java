package com.xworkz.vinayhp.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import com.xworkz.vinayhp.dto.TechnolgyListDTO;

public interface TechnologyService {

	Set<ConstraintViolation<TechnolgyListDTO>> validateAndAdd(TechnolgyListDTO dto);

	default List<TechnolgyListDTO> findById(int id){
		return Collections.emptyList();
	}
}
