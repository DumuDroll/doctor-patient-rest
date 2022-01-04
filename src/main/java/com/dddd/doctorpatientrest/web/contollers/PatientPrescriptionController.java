package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.general.services.service_impls.PatientDrugServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/patientPrescriptions/")
public class PatientPrescriptionController {

	private final PatientDrugServiceImpl patientPrescriptionService;

	public PatientPrescriptionController(PatientDrugServiceImpl patientPrescriptionService) {
		this.patientPrescriptionService = patientPrescriptionService;
	}

	@GetMapping("filtered/")
	public ResponseEntity<Map<String, Object>> allFiltered(@RequestParam(defaultValue = "") String dateFrom,
														   @RequestParam(defaultValue = "") String dateTo,
														   @RequestParam(defaultValue = "0") int page,
														   @RequestParam(defaultValue = "5") int size,
														   @RequestParam(defaultValue = "") String patientId) {
		return patientPrescriptionService.findAllFiltered(patientId, dateFrom, dateTo, page, size);
	}
}
