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
@CrossOrigin
@RequestMapping("/patients/")
public class PatientController {

	private final PatientServiceImpl patientService;

	public PatientController(PatientServiceImpl patientService) {
		this.patientService = patientService;
	}

	@GetMapping
	public ResponseEntity<List<PatientDto>> all() {
		return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
		return new ResponseEntity<>(patientService.create(patientDto), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto) {
		return new ResponseEntity<>(patientService.update(patientDto), HttpStatus.OK);
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

	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Not Found")})
	@DeleteMapping(Constants.PATIENT_ID)
	public ResponseEntity<List<PatientDto>> deletePatient(@PathVariable long patientId) {
		return new ResponseEntity<>(patientService.deleteById(patientId), HttpStatus.OK);
	}
}
