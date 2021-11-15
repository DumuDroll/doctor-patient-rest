package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exception.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.service_impls.DoctorServiceImpl;
import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.MapStructMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DoctorController {

	private final MapStructMapper mapStructMapper;

	private final DoctorServiceImpl doctorService;

	public DoctorController(MapStructMapper mapStructMapper, DoctorServiceImpl doctorService) {
		this.mapStructMapper = mapStructMapper;
		this.doctorService = doctorService;
	}

	@GetMapping("/doctors")
	public ResponseEntity<List<DoctorDto>> all() {
		return new ResponseEntity<>(mapStructMapper.doctorListToDoctorDtoList(doctorService.findAll()),
				HttpStatus.OK);
	}

	@PostMapping("/doctors")
	public DoctorDto createDoctor(@RequestBody DoctorDto newDoctor) {
		return mapStructMapper.doctorToDoctorDto(doctorService.save(mapStructMapper.doctorDtoToDoctor(newDoctor)));
	}

	@GetMapping("/doctors/{id}")
	public ResponseEntity<DoctorDto> getById(@PathVariable(value = "id") long id) {
		Optional<Doctor> doctor = doctorService.findById(id);
		return doctor.map(value -> new ResponseEntity<>(mapStructMapper.doctorToDoctorDto(value),
				HttpStatus.OK)).orElseThrow(() -> new ResourceNotFoundException(Constants.DOCTOR_NOT_FOUND + id));
	}

	@PatchMapping("/doctors/{id}")
	public ResponseEntity<DoctorDto> replaceDoctor(@PathVariable(value = "id") long id,
												   @RequestBody DoctorDto doctorDto) {
		Doctor newDoctor = mapStructMapper.doctorDtoToDoctor(doctorDto);
		Optional<Doctor> oldDoctor = doctorService.findById(id);
		return oldDoctor.map(value -> {
			value.setName(newDoctor.getName());
			return new ResponseEntity<>(mapStructMapper.doctorToDoctorDto(doctorService.save(value)), HttpStatus.OK);
		}).orElseThrow(() -> new ResourceNotFoundException(Constants.DOCTOR_NOT_FOUND + id));
	}

	@DeleteMapping("/doctors/{id}")
	public ResponseEntity<Object> deleteDoctor(@PathVariable long id) {
		return doctorService.findById(id).map(drug -> {
			doctorService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(Constants.DRUG_NOT_FOUND + id));
	}
}
