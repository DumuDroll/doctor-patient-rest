package com.dddd.doctorpatientrest.application.services;

import com.dddd.doctorpatientrest.web.mapstruct.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

	ResponseEntity<Map<String, Object>> findAllFiltered(String email, int page, int size);

	List<UserDto> findAll();

	UserDto create(UserDto userDto);

	UserDto update(UserDto userDto);

	UserDto findById(long id);

}
