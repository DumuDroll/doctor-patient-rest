package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.general.services.service_impls.DoctorServiceImpl;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

	@GetMapping("filtered/")
	public ResponseEntity<Map<String, Object>> allFiltered(@RequestParam(defaultValue = "") String name,
													  @RequestParam(defaultValue = "0") int page,
													  @RequestParam(defaultValue = "5") int size) {
		return doctorService.findAllFiltered(name, page, size);
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

	@DeleteMapping(Constants.DOCTOR_ID)
	public ResponseEntity<Object> deleteDoctor(@PathVariable long doctorId) {
		doctorService.deleteById(doctorId);
		return ResponseEntity.ok().build();
	}
}
