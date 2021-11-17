package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.services.service_impls.PatientServiceImpl;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

	private final PatientServiceImpl patientService;

	public PatientController(PatientServiceImpl patientService) {
		this.patientService = patientService;
	}

	@GetMapping
	public ResponseEntity<List<PatientDto>> all() {
		return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
	}

	@GetMapping(Constants.DOCTOR_ID)
	public ResponseEntity<List<PatientDto>> getAllPatientsByDoctorId(@PathVariable long doctorId) {
		return null;
	}

	@PostMapping
	public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
		return new ResponseEntity<>(patientService.create(patientDto), HttpStatus.OK);
	}

	@PatchMapping(Constants.DOCTOR_ID)
	public ResponseEntity<PatientDto> addDoctorToPatient(@PathVariable long doctorId,
														 @RequestBody PatientDto patientDto) {
		return new ResponseEntity<>(patientService.addDoctorToPatient(doctorId, patientDto), HttpStatus.OK);
	}

	@GetMapping(Constants.PATIENT_ID)
	public ResponseEntity<PatientDto> getById(@PathVariable long patientId) {
		return new ResponseEntity<>(patientService.findById(patientId), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto) {
		return new ResponseEntity<>(patientService.update(patientDto), HttpStatus.OK);
	}

	@DeleteMapping(Constants.PATIENT_ID)
	public ResponseEntity<Object> deletePatient(@PathVariable long patientId) {
		patientService.deleteById(patientService.findById(patientId).getId());
		return ResponseEntity.ok().build();
	}
}
