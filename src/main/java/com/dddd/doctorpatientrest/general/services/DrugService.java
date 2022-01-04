package com.dddd.doctorpatientrest.general.services;

import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DrugService extends GenericService<DrugDto> {

	ResponseEntity<Map<String, Object>> findAllFiltered(String name, int page, int size);
}
