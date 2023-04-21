package com.xworkz.vinayhp.service;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.xworkz.vinayhp.dto.UserDTO;

public interface CMUpdateProfileService {

	Set<ConstraintViolation<UserDTO>> validateAndUpdate(UserDTO dto);
}
