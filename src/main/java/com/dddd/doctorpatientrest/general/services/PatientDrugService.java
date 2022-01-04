package com.dddd.doctorpatientrest.general.services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PatientDrugService{

	ResponseEntity<Map<String, Object>> findAllFiltered(String patientId, String dateFrom, String dateTo, int page, int size);

}
