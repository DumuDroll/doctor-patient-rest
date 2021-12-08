package com.dddd.doctorpatientrest.application.services;

import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PatientService extends GenericService<PatientDto>{

	PatientDto addDoctorToPatient(long doctorId, PatientDto patientDto);

	ResponseEntity<Map<String, Object>> findAllFiltered(String fNameLName, int page, int size);

}
