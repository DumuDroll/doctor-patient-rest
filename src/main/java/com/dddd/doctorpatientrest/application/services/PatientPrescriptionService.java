package com.dddd.doctorpatientrest.application.services;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PatientPrescriptionService{

	ResponseEntity<Map<String, Object>> findAllFiltered(String dateFrom, String dateTo, int page, int size);

}
