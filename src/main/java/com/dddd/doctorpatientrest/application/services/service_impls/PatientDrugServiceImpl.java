package com.dddd.doctorpatientrest.application.services.service_impls;

import com.dddd.doctorpatientrest.application.services.PatientDrugService;
import com.dddd.doctorpatientrest.database.entities.PatientDrug;
import com.dddd.doctorpatientrest.database.entities.PatientDrugId;
import com.dddd.doctorpatientrest.database.repositories.PatientDrugRepository;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.PatientDrugMapper;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class PatientDrugServiceImpl implements PatientDrugService {

	private final PatientDrugRepository patientDrugRepository;

	private final PatientDrugMapper patientDrugMapper;

	public PatientDrugServiceImpl(PatientDrugRepository patientDrugRepository,
								  PatientDrugMapper patientDrugMapper) {
		this.patientDrugRepository = patientDrugRepository;
		this.patientDrugMapper = patientDrugMapper;
	}

	@Override
	public ResponseEntity<Map<String, Object>> findAllFiltered(String patientId, String dateFrom, String dateTO, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));
		Page<PatientDrug> patientDrugs;

		ExampleMatcher matchingAny = ExampleMatcher.matchingAny();
		PatientDrug patientDrug = new PatientDrug();
		if (!patientId.equals("")) {
			PatientDrugId patientDrugId = new PatientDrugId();
			patientDrugId.setPatientId(Long.parseLong(patientId));
			patientDrug.setId(patientDrugId);
		}
		patientDrugs = patientDrugRepository.findAll(Example.of(patientDrug, matchingAny), pageRequest);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		if (!dateFrom.equals("")&&!dateFrom.equals("null")) {
			patientDrug.setPrescriptionStartDate(LocalDate.parse(dateFrom, formatter));
		}
		if (!dateTO.equals("")&&!dateTO.equals("null")) {
			patientDrug.setPrescriptionEndDate(LocalDate.parse(dateTO, formatter));
		}


		Map<String, Object> response = new HashMap<>();
		response.put("data", patientDrugMapper.patientDrugListToPatientDrugDtoList(patientDrugs.getContent()));
		response.put("currentPage", patientDrugs.getNumber());
		response.put("totalItems", patientDrugs.getTotalElements());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
