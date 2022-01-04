package com.dddd.doctorpatientrest.general.services;

import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DoctorService extends GenericService<DoctorDto> {

	ResponseEntity<Map<String, Object>> findAllFiltered(String name, int page, int size);

}
