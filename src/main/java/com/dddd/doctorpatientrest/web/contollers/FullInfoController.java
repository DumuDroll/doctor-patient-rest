package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exception.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.service_impls.FullInfoServiceImpl;
import com.dddd.doctorpatientrest.application.services.service_impls.PatientServiceImpl;
import com.dddd.doctorpatientrest.database.entities.FullInfo;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.web.mapstruct.dto.FullInfoDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.MapStructMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FullInfoController {

	private final MapStructMapper mapStructMapper;

	private final FullInfoServiceImpl fullInfoService;

	private final PatientServiceImpl patientService;

	public FullInfoController(MapStructMapper mapStructMapper, FullInfoServiceImpl fullInfoService, PatientServiceImpl patientService) {
		this.mapStructMapper = mapStructMapper;
		this.fullInfoService = fullInfoService;
		this.patientService = patientService;
	}

	@PostMapping("/patients/{pId}/fullInfo")
	public ResponseEntity<PatientDto> addFullInfoToPatient(@PathVariable long pId, @RequestBody FullInfoDto fullInfoDto) {
		Optional<Patient> patient = patientService.findById(pId);
		if (patient.isPresent()) {
			FullInfo fullInfo = mapStructMapper.fullInfoDtoToFullInfo(fullInfoDto);
			fullInfo.setPatient(patient.get());
			patient.get().setFullInfo(fullInfo);
			return new ResponseEntity<>(mapStructMapper.patientToPatientDto(patientService.save(patient.get())),
					HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException(Constants.PATIENT_NOT_FOUND + pId);
		}
	}

	@PutMapping("/patients/{pId}/fullInfo")
	public ResponseEntity<PatientDto> updateFullInfo(@PathVariable long pId,
													 @RequestBody FullInfoDto fullInfoDto) {
		Optional<Patient> patient = patientService.findById(pId);
		if (patient.isPresent()) {
			FullInfo fullInfoFromDb = patient.get().getFullInfo();
			if (fullInfoFromDb != null) {
				fullInfoFromDb.setEmail(fullInfoDto.getEmail());
				fullInfoFromDb.setBirthDate(fullInfoDto.getBirthDate());
				fullInfoFromDb.setPhoneNumber(fullInfoDto.getPhoneNumber());
				fullInfoService.save(fullInfoFromDb);
			} else {
				throw new ResourceNotFoundException(Constants.FULL_INFO_NOT_FOUND);
			}
			patient.get().setFullInfo(fullInfoFromDb);
			return new ResponseEntity<>(mapStructMapper.patientToPatientDto(patientService.save(patient.get())),
					HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException(Constants.PATIENT_NOT_FOUND + pId);
		}
	}

	@DeleteMapping("/patients/{pId}/fullInfo")
	public ResponseEntity<Object> deleteFullInfo(@PathVariable long pId) {
		Optional<Patient> patient = patientService.findById(pId);
		if (patient.isPresent()) {
			FullInfo fullInfoFromDb = patient.get().getFullInfo();
			if (fullInfoFromDb != null) {
				fullInfoService.deleteById(fullInfoFromDb.getId());
				return ResponseEntity.ok().build();
			} else {
				throw new ResourceNotFoundException(Constants.FULL_INFO_NOT_FOUND);
			}
		} else {
			throw new ResourceNotFoundException(Constants.PATIENT_NOT_FOUND + pId);
		}
	}
}
