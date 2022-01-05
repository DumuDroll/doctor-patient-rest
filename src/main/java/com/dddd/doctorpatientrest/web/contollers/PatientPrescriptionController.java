package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.general.services.PatientDrugService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/patientPrescriptions/")
public class PatientPrescriptionController {

	private final PatientDrugService patientPrescriptionService;

	public PatientPrescriptionController(PatientDrugService patientPrescriptionService) {
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
