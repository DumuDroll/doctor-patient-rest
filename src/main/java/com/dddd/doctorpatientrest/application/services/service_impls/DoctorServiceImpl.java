package com.dddd.doctorpatientrest.application.services.service_impls;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exception.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.DoctorService;
import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.database.repositories.DoctorRepository;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.DoctorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

	private final DoctorRepository doctorRepository;

	private final DoctorMapper doctorMapper;

	public DoctorServiceImpl(DoctorRepository doctorRepository,
							 DoctorMapper doctorMapper) {
		this.doctorRepository = doctorRepository;
		this.doctorMapper = doctorMapper;
	}

	@Override
	public DoctorDto save(Doctor doctor) {
		return doctorMapper.doctorToDoctorDto(doctorRepository.save(doctor));
	}

	@Override
	public DoctorDto update(Doctor doctor) {
		return null;
	}

	@Override
	public List<DoctorDto> findAll() {
		return doctorMapper.doctorListToDoctorDtoList(doctorRepository.findAll());
	}

	@Override
	public DoctorDto findById(long id) {
		Optional<Doctor> doctor = doctorRepository.findById(id);
		return doctor.map(doctorMapper::doctorToDoctorDto)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.DOCTOR_NOT_FOUND + id));
	}

	@Override
	public void deleteById(long id) {
		doctorRepository.deleteById(id);
	}

}
