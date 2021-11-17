package com.dddd.doctorpatientrest.application.services.service_impls;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
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
	public List<DoctorDto> findAll() {
		return doctorMapper.doctorListToDoctorDtoList(doctorRepository.findAll());
	}

	@Override
	public DoctorDto findById(long id) {
		Optional<Doctor> doctor = doctorRepository.findById(id);
		return doctor.map(doctorMapper::doctorToDoctorDto)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.DOCTOR_NOT_FOUND, id));
	}

	@Override
	public DoctorDto create(DoctorDto doctorDto) {
		if (doctorDto.getId() != 0 && doctorRepository.findById(doctorDto.getId()).isPresent()) {
			throw new ResourceAlreadyExistsException(Constants.DOCTOR_ALREADY_EXISTS, doctorDto.getId());
		}
		return doctorMapper.doctorToDoctorDto(doctorRepository.save(doctorMapper.doctorDtoToDoctor(doctorDto)));
	}

	@Override
	public DoctorDto update(DoctorDto doctorDto) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorDto.getId());
		return doctor.map(value -> doctorMapper.doctorToDoctorDto(doctorRepository
						.save(doctorMapper.doctorDtoToDoctor(doctorDto))))
				.orElseThrow(() -> new ResourceNotFoundException(Constants.DOCTOR_NOT_FOUND, doctorDto.getId()));
	}

	@Override
	public void deleteById(long id) {
		doctorRepository.deleteById(id);
	}

}
