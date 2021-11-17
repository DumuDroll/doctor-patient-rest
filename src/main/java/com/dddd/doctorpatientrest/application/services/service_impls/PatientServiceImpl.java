package com.dddd.doctorpatientrest.application.services.service_impls;

import com.dddd.doctorpatientrest.application.exceptions.DoctorNotFoundException;
import com.dddd.doctorpatientrest.application.exceptions.PatientAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.PatientNotFoundException;
import com.dddd.doctorpatientrest.application.services.PatientService;
import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.database.entities.FullInfo;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.database.repositories.DoctorRepository;
import com.dddd.doctorpatientrest.database.repositories.PatientRepository;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.PatientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
			throw new DoctorNotFoundException(doctorId);
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
	public PatientDto findById(long id) {
		Optional<Patient> patient = patientRepository.findById(id);
		return patient.map(patientMapper::patientToPatientDto)
				.orElseThrow(() -> new PatientNotFoundException(id));
	}

	@Override
	public PatientDto create(PatientDto patientDto) {
		if (patientDto.getId() != 0 && patientRepository.findById(patientDto.getId()).isPresent()) {
			throw new PatientAlreadyExistsException(patientDto.getId());
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
				.orElseThrow(() -> new PatientNotFoundException(patientDto.getId()));
	}

	@Override
	public void deleteById(long id) {
		patientRepository.deleteById(id);
	}
}
