package com.dddd.doctorpatientrest.application.services.service_impls;

import com.dddd.doctorpatientrest.application.services.PatientPrescriptionService;
import com.dddd.doctorpatientrest.database.entities.PatientDrug;
import com.dddd.doctorpatientrest.database.repositories.PatientPrescriptionRepository;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.PatientDrugMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PatientPrescriptionServiceImpl implements PatientPrescriptionService{

	private final PatientPrescriptionRepository patientPrescriptionRepository;

	private final PatientDrugMapper patientDrugMapper;

	public PatientPrescriptionServiceImpl(PatientPrescriptionRepository patientPrescriptionRepository,
										  PatientDrugMapper patientDrugMapper) {
		this.patientPrescriptionRepository = patientPrescriptionRepository;
		this.patientDrugMapper = patientDrugMapper;
	}

	@Override
	public ResponseEntity<Map<String, Object>> findAllFiltered(String dateFrom, String dateTO, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));
		Page<PatientDrug> patientDrugs;
			patientDrugs = patientPrescriptionRepository.findAll(pageRequest);

		Map<String, Object> response = new HashMap<>();
		response.put("data", patientDrugMapper.patientDrugListToPatientDrugDtoList(patientDrugs.getContent()));
		response.put("currentPage", patientDrugs.getNumber());
		response.put("totalItems", patientDrugs.getTotalElements());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
