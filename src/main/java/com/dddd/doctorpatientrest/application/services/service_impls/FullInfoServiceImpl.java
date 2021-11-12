package com.dddd.doctorpatientrest.application.services.service_impls;

import com.dddd.doctorpatientrest.database.entities.FullInfo;
import com.dddd.doctorpatientrest.database.repositories.FullInfoRepository;
import com.dddd.doctorpatientrest.application.services.FullInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FullInfoServiceImpl implements FullInfoService {

	private final FullInfoRepository fullInfoRepository;

	public FullInfoServiceImpl(FullInfoRepository fullInfoRepository) {
		this.fullInfoRepository = fullInfoRepository;
	}


	@Override
	public List<FullInfo> findAll() {
		return fullInfoRepository.findAll();
	}

	@Override
	public Optional<FullInfo> findById(long id) {
		return fullInfoRepository.findById(id);
	}

	@Override
	public FullInfo save(FullInfo entity) {
		return fullInfoRepository.save(entity);
	}

	@Override
	public void deleteById(long id) {
		fullInfoRepository.deleteById(id);
	}
}
