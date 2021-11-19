package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.services.service_impls.PatientServiceImpl;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients/")
public class PatientController {

	private final PatientServiceImpl patientService;

	public PatientController(PatientServiceImpl patientService) {
		this.patientService = patientService;
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403") })
	@GetMapping
	public ResponseEntity<List<PatientDto>> all() {
		return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404"),
			@ApiResponse(responseCode = "409") })
	@PostMapping
	public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
		return new ResponseEntity<>(patientService.create(patientDto), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404") })
	@PutMapping
	public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto) {
		return new ResponseEntity<>(patientService.update(patientDto), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404") })
	@PatchMapping(Constants.DOCTOR_ID)
	public ResponseEntity<PatientDto> addDoctorToPatient(@PathVariable long doctorId,
														 @RequestBody PatientDto patientDto) {
		return new ResponseEntity<>(patientService.addDoctorToPatient(doctorId, patientDto), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404") })
	@GetMapping(Constants.PATIENT_ID)
	public ResponseEntity<PatientDto> getById(@PathVariable long patientId) {
		return new ResponseEntity<>(patientService.findById(patientId), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404") })
	@DeleteMapping(Constants.PATIENT_ID)
	public ResponseEntity<Object> deletePatient(@PathVariable long patientId) {
		patientService.deleteById(patientService.findById(patientId).getId());
		return ResponseEntity.ok().build();
	}
}
