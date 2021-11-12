package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.application.services.service_impls.DoctorServiceImpl;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.MapStructMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DoctorController {

	private final MapStructMapper mapStructMapper;

	private final DoctorServiceImpl doctorService;

	public DoctorController(MapStructMapper mapStructMapper, DoctorServiceImpl doctorService) {
		this.mapStructMapper = mapStructMapper;
		this.doctorService = doctorService;
	}

	@GetMapping("/doctors")
	void all() {

	}

	@PostMapping("/doctors")
	Doctor newDoctor(@RequestBody Doctor newDoctor) {
		return doctorService.save(newDoctor);
	}



}
