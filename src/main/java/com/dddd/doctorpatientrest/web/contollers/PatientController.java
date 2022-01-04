package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.general.services.service_impls.PatientServiceImpl;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDrugDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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


	@GetMapping("filtered/")
	public ResponseEntity<Map<String, Object>> allFiltered(@RequestParam(defaultValue = "") String fNameLName,
														   @RequestParam(defaultValue = "0") int page,
														   @RequestParam(defaultValue = "5") int size) {
		return patientService.findAllFiltered(fNameLName, page, size);
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

	@PatchMapping(Constants.PATIENT_ID)
	public ResponseEntity<PatientDto> addDrugToPatient(@PathVariable long patientId,
														 @RequestBody List<PatientDrugDto> patientDrugDtoList) {
		return new ResponseEntity<>(patientService.addDrugToPatient(patientDrugDtoList, patientId), HttpStatus.OK);
	}

	@GetMapping(Constants.PATIENT_ID)
	public ResponseEntity<PatientDto> getById(@PathVariable long patientId) {
		return new ResponseEntity<>(patientService.findById(patientId), HttpStatus.OK);
	}

	@DeleteMapping(Constants.PATIENT_ID)
	public ResponseEntity<Object> deletePatient(@PathVariable long patientId) {
		patientService.deleteById(patientId);
		return ResponseEntity.ok().build();
	}
}
