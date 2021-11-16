package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.services.service_impls.DoctorServiceImpl;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.DoctorMapper;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.DrugMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	private final DrugMapper drugMapper;

	private final DoctorMapper doctorMapper;

	private final DoctorServiceImpl doctorService;

	public DoctorController(DrugMapper drugMapper,
							DoctorMapper doctorMapper, DoctorServiceImpl doctorService) {
		this.drugMapper = drugMapper;
		this.doctorMapper = doctorMapper;
		this.doctorService = doctorService;
	}

	@GetMapping
	public ResponseEntity<List<DoctorDto>> all() {
		return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto newDoctor) {
		return new ResponseEntity<>(doctorService.save(doctorMapper.doctorDtoToDoctor(newDoctor)), HttpStatus.OK);
	}

	@GetMapping(Constants.DOCTOR_ID)
	public ResponseEntity<DoctorDto> getById(@PathVariable long doctorId) {
		return new ResponseEntity<>(doctorService.findById(doctorId), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<DoctorDto> replaceDoctor(@RequestBody DoctorDto doctorDto) {
		return null;
	}

	@DeleteMapping(Constants.DOCTOR_ID)
	public ResponseEntity<Object> deleteDoctor(@PathVariable long doctorId) {
		doctorService.deleteById(doctorService.findById(doctorId).getId());
		return ResponseEntity.ok().build();
	}
}
