package com.dddd.doctorpatientrest.general.services.service_impls;

import com.dddd.doctorpatientrest.general.services.PatientDrugService;
import com.dddd.doctorpatientrest.database.entities.PatientDrug;
import com.dddd.doctorpatientrest.database.entities.PatientDrugId;
import com.dddd.doctorpatientrest.database.repositories.PatientDrugRepository;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.PatientMapper;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PatientDrugServiceImpl implements PatientDrugService {

	private final PatientDrugRepository patientDrugRepository;

	private final PatientMapper patientMapper;

	public PatientDrugServiceImpl(PatientDrugRepository patientDrugRepository,
								  PatientMapper patientMapper) {
		this.patientDrugRepository = patientDrugRepository;
		this.patientMapper = patientMapper;
	}

	@Override
	public ResponseEntity<Map<String, Object>> findAllFiltered(String patientId, String dateFrom, String dateTO, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));
		Page<PatientDrug> patientDrugPage;

		ExampleMatcher matchingAny = ExampleMatcher.matchingAny();
		PatientDrug patientDrug = new PatientDrug();
		if (!patientId.equals("")) {
			PatientDrugId patientDrugId = new PatientDrugId();
			patientDrugId.setPatientId(Long.parseLong(patientId));
			patientDrug.setId(patientDrugId);
		}
		patientDrugPage = patientDrugRepository.findAll(Example.of(patientDrug, matchingAny), pageRequest);
		List<PatientDrug> patientDrugList = patientDrugPage.getContent();

		List<PatientDrug> patientDrugListFiltered;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		boolean dateFromValid = true;
		boolean dateToValid = true;
		try {
			LocalDate.parse(dateFrom, formatter);
		} catch (DateTimeParseException e) {
			dateFromValid = false;
		}
		try {
			LocalDate.parse(dateTO, formatter);
		} catch (DateTimeParseException e) {
			dateToValid = false;
		}
		if (dateFromValid && dateToValid) {
			patientDrugListFiltered = patientDrugList.stream().filter(item -> LocalDate.parse(dateFrom, formatter)
					.isBefore(item.getPrescriptionStartDate()) && LocalDate.parse(dateTO, formatter)
					.isAfter(item.getPrescriptionEndDate())).collect(Collectors.toList());
		} else if (dateFromValid) {
			patientDrugListFiltered = patientDrugList.stream().filter(item -> LocalDate.parse(dateFrom, formatter)
					.isBefore(item.getPrescriptionStartDate())).collect(Collectors.toList());
		} else if (dateToValid) {
			patientDrugListFiltered = patientDrugList.stream().filter(item -> LocalDate.parse(dateTO, formatter)
					.isAfter(item.getPrescriptionEndDate())).collect(Collectors.toList());
		} else {
			patientDrugListFiltered = patientDrugList;
		}

		Map<String, Object> response = new HashMap<>();
		response.put("data", patientMapper.patientDrugListToPatientDrugDtoList(patientDrugListFiltered));
		response.put("currentPage", patientDrugPage.getNumber());
		response.put("totalItems", patientDrugPage.getTotalElements());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
