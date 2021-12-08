package com.dddd.doctorpatientrest.application.services;

import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface DrugService extends GenericService<DrugDto> {

	DrugDto addPatientToDrug(long patientId, DrugDto drugDto);

	ResponseEntity<Map<String, Object>> findAllFiltered(String name, int page, int size);
}
