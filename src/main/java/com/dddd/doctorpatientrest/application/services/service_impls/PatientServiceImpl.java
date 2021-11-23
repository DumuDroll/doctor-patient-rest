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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		List<PatientDto> patientDtoList =  patientMapper.patientListToPatientDtoList(patientRepository.findAll());
		if(patientDtoList.isEmpty()){
			throw new ResourceNotFoundException(Constants.NO_DATA_IN_DB, 404);
		}
		return patientDtoList;
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
		Optional<Patient> patient = patientRepository.findById(id);
		if (!patient.isPresent()) {
			throw new ResourceNotFoundException(Constants.PATIENT_NOT_FOUND, id);
		}
		patientRepository.deleteById(id);
	}
}
