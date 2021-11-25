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
@CrossOrigin
@RequestMapping("/doctors/")
public class DoctorController {

	private final DoctorServiceImpl doctorService;

	public DoctorController(DoctorServiceImpl doctorService) {
		this.doctorService = doctorService;
	}

	@GetMapping
	public ResponseEntity<List<DoctorDto>> all() {
		return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto newDoctor) {
		return new ResponseEntity<>(doctorService.create(newDoctor), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<DoctorDto> replaceDoctor(@RequestBody DoctorDto doctorDto) {
		return new ResponseEntity<>(doctorService.update(doctorDto), HttpStatus.OK);
	}

	@GetMapping(Constants.DOCTOR_ID)
	public ResponseEntity<DoctorDto> getById(@PathVariable long doctorId) {
		return new ResponseEntity<>(doctorService.findById(doctorId), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Not Found")})
	@DeleteMapping(Constants.DOCTOR_ID)
	public ResponseEntity<List<DoctorDto>> deleteDoctor(@PathVariable long doctorId) {
		return new ResponseEntity<>(doctorService.deleteById(doctorId), HttpStatus.OK);
	}
}
