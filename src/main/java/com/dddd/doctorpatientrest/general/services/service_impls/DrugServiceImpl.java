package com.dddd.doctorpatientrest.general.services.service_impls;

import com.dddd.doctorpatientrest.database.entities.Drug;
import com.dddd.doctorpatientrest.database.repositories.DrugRepository;
import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.general.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.general.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.general.services.DrugService;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.DrugMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Transactional
public class DrugServiceImpl implements DrugService {

	private final DrugRepository drugRepository;

	private final DrugMapper drugMapper;

	private final SenderService senderService;

	public DrugServiceImpl(DrugRepository drugRepository,
						   DrugMapper drugMapper,
						   SenderService senderService) {
		this.drugRepository = drugRepository;
		this.drugMapper = drugMapper;
		this.senderService = senderService;
	}

	@Override
	public List<DrugDto> findAll() {
		return drugMapper.drugListToDrugDtoList(drugRepository.findAll());
	}

	@Override
	public ResponseEntity<Map<String, Object>> findAllFiltered(String name, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));
		Page<Drug> drugs;
		drugs = drugRepository.findAllByNameContaining(name, pageRequest);
		Map<String, Object> response = new HashMap<>();
		response.put("data", drugMapper.drugListToDrugDtoList(drugs.getContent()));
		response.put("currentPage", drugs.getNumber());
		response.put("pageSize", drugs.getSize());
		response.put("totalItems", drugs.getTotalElements());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public DrugDto findById(long id) {
		return drugRepository.findById(id).map(drugMapper::drugToDrugDto)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.DRUG_NOT_FOUND, id));
	}

	@Override
	public DrugDto create(DrugDto drugDto) {
		if (drugDto.getId() != 0 && drugRepository.findById(drugDto.getId()).isPresent()) {
			throw new ResourceAlreadyExistsException(Constants.DRUG_ALREADY_EXISTS, drugDto.getId());
		}
		drugDto = drugMapper.drugToDrugDto(drugRepository.save(drugMapper.drugDtoToDrug(drugDto)));
		senderService.sendSavedDrug(drugMapper.drugDtoToDrugRabbitDto(drugDto));

		return drugDto;
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
		senderService.sendDeletedDrug(id);
	}

}