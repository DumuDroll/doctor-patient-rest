package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.application.services.service_impls.PatientPrescriptionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/patientPrescriptions/")
public class PatientPrescriptionController {

	private final PatientPrescriptionServiceImpl patientPrescriptionService;

	public PatientPrescriptionController(PatientPrescriptionServiceImpl patientPrescriptionService) {
		this.patientPrescriptionService = patientPrescriptionService;
	}

	@GetMapping("filtered/")
	public ResponseEntity<Map<String, Object>> allFiltered(@RequestParam(defaultValue = "") String dateFrom,
														   @RequestParam(defaultValue = "") String dateTo,
														   @RequestParam(defaultValue = "0") int page,
														   @RequestParam(defaultValue = "5") int size) {
		return patientPrescriptionService.findAllFiltered(dateFrom, dateTo, page, size);
	}
}
