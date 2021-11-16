package com.dddd.doctorpatientrest.application.services.service_impls;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exception.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.PatientService;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.database.repositories.PatientRepository;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.PatientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;

	private final PatientMapper patientMapper;

	public PatientServiceImpl(PatientRepository patientRepository,
							  PatientMapper patientMapper) {
		this.patientRepository = patientRepository;
		this.patientMapper = patientMapper;
	}

	@Override
	public List<PatientDto> findAll() {
		return patientMapper.patientListToPatientDtoList(patientRepository.findAll());
	}

	@Override
	public PatientDto findById(long id) {
		Optional<Patient> patient = patientRepository.findById(id);
		return patient.map(patientMapper::patientToPatientDto)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.PATIENT_NOT_FOUND + id));
	}

	@Override
	public PatientDto save(PatientDto patientDto) {
		return patientMapper.patientToPatientDto(patientRepository.save(patientMapper.patientDtoToPatient(patientDto)));
	}

	@Override
	public PatientDto update(PatientDto patientDto) {
		return save(patientDto);
	}

	@Override
	public void deleteById(long id) {
		patientRepository.deleteById(id);
	}
}
