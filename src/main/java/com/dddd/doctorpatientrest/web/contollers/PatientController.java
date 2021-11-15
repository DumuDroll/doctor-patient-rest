package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exception.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.service_impls.DoctorServiceImpl;
import com.dddd.doctorpatientrest.application.services.service_impls.PatientServiceImpl;
import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.MapStructMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

	private final MapStructMapper mapStructMapper;

	private final DoctorServiceImpl doctorService;

	private final PatientServiceImpl patientService;

	public PatientController(MapStructMapper mapStructMapper, DoctorServiceImpl doctorService,
							 PatientServiceImpl patientService) {
		this.mapStructMapper = mapStructMapper;
		this.doctorService = doctorService;
		this.patientService = patientService;
	}

	@GetMapping("/doctors/{doctorId}/patients")
	public ResponseEntity<List<PatientDto>> getAllPatientsByDoctorId(@PathVariable long doctorId) {
		List<Patient> patients;
		Optional<Doctor> doctor = doctorService.findById(doctorId);
		if (doctor.isPresent()) {
			patients = doctor.get().getPatients();
		} else {
			throw new ResourceNotFoundException(Constants.DOCTOR_NOT_FOUND + doctorId);
		}
		return new ResponseEntity<>(mapStructMapper.patientListToPatientDtoList(patients), HttpStatus.OK);
	}

	@PostMapping("/doctors/{doctorId}/patients")
	public ResponseEntity<PatientDto> createPatient(@PathVariable long doctorId, @RequestBody PatientDto patientDto) {
		Optional<Doctor> doctor = doctorService.findById(doctorId);
		if (doctor.isPresent()) {
			Patient patient = mapStructMapper.patientDtoToPatient(patientDto);
			patient.setDoctor(doctor.get());
			return new ResponseEntity<>(mapStructMapper.patientToPatientDto(patientService.save(patient)),
					HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException(Constants.DOCTOR_NOT_FOUND + doctorId);
		}
	}

	@PutMapping("/patients/{patientId}")
	public ResponseEntity<PatientDto> updatePatient(@PathVariable long patientId,
													@RequestBody PatientDto patientDto) {
		Optional<Patient> patient = patientService.findById(patientId);
		if (patient.isPresent()) {
			patient.get().setFirstName(patientDto.getFirstName());
			patient.get().setLastName(patientDto.getLastName());
			return new ResponseEntity<>(mapStructMapper.patientToPatientDto(patientService.save(patient.get())),
					HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException(Constants.PATIENT_NOT_FOUND + patientId);
		}
	}

	@DeleteMapping("/patients/{id}")
	public ResponseEntity<Object> deletePatient(@PathVariable long id) {
		return patientService.findById(id).map(drug -> {
			patientService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(Constants.PATIENT_NOT_FOUND + id));
	}
}
