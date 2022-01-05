package com.dddd.doctorpatientrest.general.services;

import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.database.repositories.DoctorRepository;
import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.general.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.general.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.DoctorMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
@Transactional
public class DoctorService {

	private final DoctorRepository doctorRepository;

	private final DoctorMapper doctorMapper;

	private final SenderService senderService;

	public DoctorService(DoctorRepository doctorRepository,
						 DoctorMapper doctorMapper, SenderService senderService) {
		this.doctorRepository = doctorRepository;
		this.doctorMapper = doctorMapper;
		this.senderService = senderService;
	}

	public List<DoctorDto> findAll() {
		return doctorMapper.doctorListToDoctorDtoList(doctorRepository.findAll());
	}

	public ResponseEntity<Map<String, Object>> findAllFiltered(String name, int page, int size) {

		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));
		Page<Doctor> doctors = doctorRepository.findAllByNameContaining(name, pageRequest);
		Map<String, Object> response = new HashMap<>();
		response.put("data", doctorMapper.doctorListToDoctorDtoList(doctors.getContent()));
		response.put("currentPage", doctors.getNumber());
		response.put("pageSize", doctors.getSize());
		response.put("totalItems", doctors.getTotalElements());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public DoctorDto findById(long id) {
		return doctorRepository.findById(id).map(doctorMapper::doctorToDoctorDto)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.DOCTOR_NOT_FOUND, id));
	}

	public DoctorDto create(DoctorDto doctorDto) {
		if (doctorDto.getId() != 0 && doctorRepository.findById(doctorDto.getId()).isPresent()) {
			throw new ResourceAlreadyExistsException(Constants.DOCTOR_ALREADY_EXISTS, doctorDto.getId());
		}
		DoctorDto result = doctorMapper.doctorToDoctorDto(doctorRepository.save(doctorMapper
				.doctorDtoToDoctor(doctorDto)));
		senderService.sendSavedDoctor(doctorMapper.doctorDtoToDoctorRabbit(result));

		return result;
	}

	public DoctorDto update(DoctorDto doctorDto) {
		Optional<Doctor> doctor = doctorRepository.findById(doctorDto.getId());
		return doctor.map(value -> doctorMapper.doctorToDoctorDto(doctorRepository
						.save(doctorMapper.doctorDtoToDoctor(doctorDto))))
				.orElseThrow(() -> new ResourceNotFoundException(Constants.DOCTOR_NOT_FOUND, doctorDto.getId()));
	}

	public void deleteById(long id) {
		doctorRepository.deleteById(id);
		senderService.sendDeletedDoctor(id);
	}

}
