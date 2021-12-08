package com.dddd.doctorpatientrest.application.services.service_impls;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.PatientService;
import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.database.entities.FullInfo;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.database.repositories.DoctorRepository;
import com.dddd.doctorpatientrest.database.repositories.PatientRepository;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.PatientMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Log4j2
@Service
@Transactional
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;

	private final DoctorRepository doctorRepository;

	private final PatientMapper patientMapper;

	public PatientServiceImpl(PatientRepository patientRepository,
							  DoctorRepository doctorRepository,
							  PatientMapper patientMapper) {
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
		this.patientMapper = patientMapper;
	}

	@Override
	public PatientDto addDoctorToPatient(long doctorId, PatientDto patientDto) {
		Doctor doctor;
		Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
		if (optionalDoctor.isPresent()) {
			doctor = optionalDoctor.get();
		} else {
			throw new ResourceNotFoundException(Constants.DOCTOR_NOT_FOUND, doctorId);
		}
		findById(patientDto.getId());
		Patient patient = patientMapper.patientDtoToPatient(patientDto);
		if (doctor.getPatients() == null) {
			doctor.setPatients(new ArrayList<>());
		}
		doctor.getPatients().add(patient);
		patient.setDoctor(doctor);
		doctorRepository.save(doctor);
		return patientMapper.patientToPatientDto(patientRepository.save(patient));
	}


	@Override
	public List<PatientDto> findAll() {
		return patientMapper.patientListToPatientDtoList(patientRepository.findAll());
	}

	@Override
	public ResponseEntity<Map<String, Object>> findAllFiltered(String fNameLName, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));
		Page<Patient> patients;
		String[] arr = fNameLName.split(" ", 2);
		String fName;
		String lName = "";
		if (arr.length == 2) {
			fName = arr[0];
			lName = arr[1];
		} else {
			fName = arr[0];
		}
		patients = patientRepository.findAllByFirstNameContainingAndLastNameContaining(fName, lName, pageRequest);
		Map<String, Object> response = new HashMap<>();
		response.put("data", patientMapper.patientListToPatientDtoList(patients.getContent()));
		response.put("currentPage", patients.getNumber());
		response.put("totalItems", patients.getTotalElements());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public PatientDto findById(long id) {
		Optional<Patient> patient = patientRepository.findById(id);
		return patient.map(patientMapper::patientToPatientDto)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.PATIENT_NOT_FOUND, id));
	}

	@Override
	public PatientDto create(PatientDto patientDto) {
		if (patientDto.getId() != 0 && patientRepository.findById(patientDto.getId()).isPresent()) {
			throw new ResourceAlreadyExistsException(Constants.PATIENT_ALREADY_EXISTS, patientDto.getId());
		}
		Patient patient = patientMapper.patientDtoToPatient(patientDto);
		if (patient.getFullInfo() == null) {
			patient.setFullInfo(new FullInfo());
		}
		FullInfo fullInfo = patient.getFullInfo();
		fullInfo.setPatient(patient);
		return patientMapper.patientToPatientDto(patientRepository.save(patient));
	}

	@Override
	public PatientDto update(PatientDto patientDto) {
		Optional<Patient> patient = patientRepository.findById(patientDto.getId());
		return patient.map(value -> patientMapper.patientToPatientDto(patientRepository
						.save(patientMapper.patientDtoToPatient(patientDto))))
				.orElseThrow(() -> new ResourceNotFoundException(Constants.PATIENT_NOT_FOUND, patientDto.getId()));
	}

	@Override
	public void deleteById(long id) {
		patientRepository.deleteById(id);
	}
}
