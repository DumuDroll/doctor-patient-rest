package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exception.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.service_impls.DrugServiceImpl;
import com.dddd.doctorpatientrest.application.services.service_impls.PatientServiceImpl;
import com.dddd.doctorpatientrest.database.entities.Drug;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.MapStructMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DrugController {

	private final MapStructMapper mapStructMapper;

	private final DrugServiceImpl drugService;

	private final PatientServiceImpl patientService;


	public DrugController(MapStructMapper mapStructMapper, DrugServiceImpl drugService,
						  PatientServiceImpl patientService) {
		this.mapStructMapper = mapStructMapper;
		this.drugService = drugService;
		this.patientService = patientService;
	}

	@GetMapping("/drugs")
	public ResponseEntity<List<DrugDto>> all() {
		return new ResponseEntity<>(mapStructMapper.drugListToDrugDtoList(drugService.findAll()),
				HttpStatus.OK);
	}

	@PostMapping("/drugs")
	public DrugDto createDrug(@RequestBody DrugDto drugDto) {
		return mapStructMapper.drugToDrugDto(drugService.save(mapStructMapper.drugDtoToDrug(drugDto)));
	}

	@PostMapping("/patients/{pId}/drugs")
	public ResponseEntity<PatientDto> addDrugToPatient(@PathVariable long pId, @RequestBody DrugDto drugDto) {
		Optional<Patient> patient = patientService.findById(pId);
		if (patient.isPresent()) {
			if (patient.get().getDrugs() == null) {
				patient.get().setDrugs(new ArrayList<>());
			}
			Drug drug = mapStructMapper.drugDtoToDrug(drugDto);
			patient.get().getDrugs().add(drug);
			return new ResponseEntity<>(mapStructMapper.patientToPatientDto(patientService.save(patient.get())),
					HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException(Constants.PATIENT_NOT_FOUND + pId);
		}
	}


	@GetMapping("/drugs/{id}")
	public ResponseEntity<DrugDto> getById(@PathVariable(value = "id") long id) {
		Optional<Drug> drug = drugService.findById(id);
		return drug.map(value -> new ResponseEntity<>(mapStructMapper.drugToDrugDto(value),
				HttpStatus.OK)).orElseThrow(() -> new ResourceNotFoundException(Constants.DRUG_NOT_FOUND + id));
	}

	@PutMapping("/drugs/{id}")
	public ResponseEntity<DrugDto> updateDrug(@PathVariable(value = "id") long id,
											  @RequestBody DrugDto drugDto) {
		Drug newDrug = mapStructMapper.drugDtoToDrug(drugDto);
		Optional<Drug> drug = drugService.findById(id);
		return drug.map(value -> {
			value.setName(newDrug.getName());
			return new ResponseEntity<>(mapStructMapper.drugToDrugDto(drugService.save(value)), HttpStatus.OK);
		}).orElseThrow(() -> new ResourceNotFoundException(Constants.DRUG_NOT_FOUND + id));
	}

	@DeleteMapping("/drugs/{id}")
	public ResponseEntity<Object> deleteDrug(@PathVariable long id) {
		return drugService.findById(id).map(drug -> {
			drugService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(Constants.DRUG_NOT_FOUND + id));
	}
}
