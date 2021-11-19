package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.services.service_impls.DoctorServiceImpl;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors/")
public class DoctorController {

	private final DoctorServiceImpl doctorService;

	public DoctorController(DoctorServiceImpl doctorService) {
		this.doctorService = doctorService;
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403") })
	@GetMapping
	public ResponseEntity<List<DoctorDto>> all() {
		return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404"),
			@ApiResponse(responseCode = "409") })
	@PostMapping
	public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto newDoctor) {
		return new ResponseEntity<>(doctorService.create(newDoctor), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404") })
	@PutMapping
	public ResponseEntity<DoctorDto> replaceDoctor(@RequestBody DoctorDto doctorDto) {
		return new ResponseEntity<>(doctorService.update(doctorDto), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404") })
	@GetMapping(Constants.DOCTOR_ID)
	public ResponseEntity<DoctorDto> getById(@PathVariable long doctorId) {
		return new ResponseEntity<>(doctorService.findById(doctorId), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404") })
	@DeleteMapping(Constants.DOCTOR_ID)
	public ResponseEntity<Object> deleteDoctor(@PathVariable long doctorId) {
		doctorService.deleteById(doctorService.findById(doctorId).getId());
		return ResponseEntity.ok().build();
	}
}
