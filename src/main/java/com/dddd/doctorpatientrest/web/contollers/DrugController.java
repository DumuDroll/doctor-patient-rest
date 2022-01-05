package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.general.services.DrugService;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/drugs/")
public class DrugController {

	private final DrugService drugService;

	public DrugController(DrugService drugService) {
		this.drugService = drugService;
	}

	@GetMapping
	public ResponseEntity<List<DrugDto>> all() {
		return new ResponseEntity<>(drugService.findAll(), HttpStatus.OK);
	}

	@GetMapping("filtered/")
	public ResponseEntity<Map<String, Object>> allFiltered(@RequestParam(defaultValue = "") String name,
														   @RequestParam(defaultValue = "0") int page,
														   @RequestParam(defaultValue = "5") int size) {
		return drugService.findAllFiltered(name, page, size);
	}

	@PostMapping
	public ResponseEntity<DrugDto> createDrug(@RequestBody DrugDto drugDto) {
		return new ResponseEntity<>(drugService.create(drugDto), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<DrugDto> updateDrug(@RequestBody DrugDto drugDto) {
		return new ResponseEntity<>(drugService.update(drugDto), HttpStatus.OK);
	}

	@GetMapping(Constants.DRUG_ID)
	public ResponseEntity<DrugDto> getById(@PathVariable long drugId) {
		return new ResponseEntity<>(drugService.findById(drugId), HttpStatus.OK);
	}

	@DeleteMapping(Constants.DRUG_ID)
	public ResponseEntity<Object> deleteDrug(@PathVariable long drugId) {
		drugService.deleteById(drugId);
		return ResponseEntity.ok().build();
	}
}
