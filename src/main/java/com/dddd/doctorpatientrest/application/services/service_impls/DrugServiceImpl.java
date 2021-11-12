package com.dddd.doctorpatientrest.application.services.service_impls;

import com.dddd.doctorpatientrest.database.entities.Drug;
import com.dddd.doctorpatientrest.database.repositories.DrugRepository;
import com.dddd.doctorpatientrest.application.services.DrugService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DrugServiceImpl implements DrugService {

	private final DrugRepository drugRepository;

	public DrugServiceImpl(DrugRepository drugRepository) {
		this.drugRepository = drugRepository;
	}

	@Override
	public List<Drug> findAll() {
		return drugRepository.findAll();
	}

	@Override
	public Optional<Drug> findById(long id) {
		return drugRepository.findById(id);
	}

	@Override
	public Drug save(Drug entity) {
		return drugRepository.save(entity);
	}

	@Override
	public void deleteById(long id) {
		drugRepository.deleteById(id);
	}
}
