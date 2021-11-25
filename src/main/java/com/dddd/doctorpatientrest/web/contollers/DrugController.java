package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.services.service_impls.DrugServiceImpl;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/drugs/")
public class DrugController {

	private final DrugServiceImpl drugService;

	public DrugController(DrugServiceImpl drugService) {
		this.drugService = drugService;
	}

	@GetMapping
	public ResponseEntity<List<DrugDto>> all() {
		return new ResponseEntity<>(drugService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<DrugDto> createDrug(@RequestBody DrugDto drugDto) {
		return new ResponseEntity<>(drugService.create(drugDto), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<DrugDto> updateDrug(@RequestBody DrugDto drugDto) {
		return new ResponseEntity<>(drugService.update(drugDto), HttpStatus.OK);
	}

	@PatchMapping(Constants.PATIENT_ID)
	public ResponseEntity<DrugDto> addPatientToDrug(@PathVariable long patientId,
													@RequestBody DrugDto drugDto) {
		return new ResponseEntity<>(drugService.addPatientToDrug(patientId, drugDto), HttpStatus.OK);
	}

	@GetMapping(Constants.DRUG_ID)
	public ResponseEntity<DrugDto> getById(@PathVariable long drugId) {
		return new ResponseEntity<>(drugService.findById(drugId), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Not Found")})
	@DeleteMapping(Constants.DRUG_ID)
	public ResponseEntity<List<DrugDto>> deleteDrug(@PathVariable long drugId) {
		return new ResponseEntity<>(drugService.deleteById(drugId), HttpStatus.OK);
	}
}
