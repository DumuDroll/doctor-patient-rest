package com.dddd.doctorpatientrest.application.services.service_impls;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.DrugService;
import com.dddd.doctorpatientrest.database.entities.Drug;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.database.repositories.DrugRepository;
import com.dddd.doctorpatientrest.database.repositories.PatientRepository;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.DrugMapper;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.PatientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DrugServiceImpl implements DrugService {

	private final DrugRepository drugRepository;

	private final PatientRepository patientRepository;

	private final PatientServiceImpl patientService;

	private final DrugMapper drugMapper;

	private final PatientMapper patientMapper;

	public DrugServiceImpl(DrugRepository drugRepository,
						   PatientRepository patientRepository, PatientServiceImpl patientService,
						   DrugMapper drugMapper,
						   PatientMapper patientMapper) {
		this.drugRepository = drugRepository;
		this.patientRepository = patientRepository;
		this.patientService = patientService;
		this.drugMapper = drugMapper;
		this.patientMapper = patientMapper;
	}

	@Override
	public List<DrugDto> findAll() {
		return drugMapper.drugListToDrugDtoList(drugRepository.findAll());
	}

	@Override
	public DrugDto findById(long id) {
		Optional<Drug> drug = drugRepository.findById(id);
		return drug.map(drugMapper::drugToDrugDto)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.DRUG_NOT_FOUND, id));
	}

	@Override
	public DrugDto create(DrugDto drugDto) {
		if (drugDto.getId() != 0 && drugRepository.findById(drugDto.getId()).isPresent()) {
			throw new ResourceAlreadyExistsException(Constants.DRUG_ALREADY_EXISTS, drugDto.getId());
		}
		return drugMapper.drugToDrugDto(drugRepository.save(drugMapper.drugDtoToDrug(drugDto)));
	}

	@Override
	public DrugDto update(DrugDto drugDto) {
		Optional<Drug> drug = drugRepository.findById(drugDto.getId());
		return drug.map(value -> drugMapper.drugToDrugDto(drugRepository
						.save(drugMapper.drugDtoToDrug(drugDto))))
				.orElseThrow(() -> new ResourceNotFoundException(Constants.DRUG_NOT_FOUND, drugDto.getId()));
	}

	@Override
	public void deleteById(long id) {
		drugRepository.deleteById(id);
	}

	@Override
	public DrugDto addPatientToDrug(long patientId, DrugDto drugDto) {
		Patient patient = patientMapper.patientDtoToPatient(patientService.findById(patientId));
		findById(drugDto.getId());
		Drug drug = drugMapper.drugDtoToDrug(drugDto);
		if (patient.getDrugs() == null) {
			patient.setDrugs(new ArrayList<>());
		}
		patient.getDrugs().forEach(value -> {
			if (value.getId().equals(drugDto.getId())) {
				throw new ResourceAlreadyExistsException("This drug is already prescribed to a patient with id: ", patientId);
			}});
		patient.getDrugs().add(drug);
		if (drug.getPatients() == null) {
			drug.setPatients(new ArrayList<>());
		}
		drug.getPatients().add(patient);
		patientRepository.save(patient);
		return drugMapper.drugToDrugDto(drugRepository.save(drug));
	}
}
