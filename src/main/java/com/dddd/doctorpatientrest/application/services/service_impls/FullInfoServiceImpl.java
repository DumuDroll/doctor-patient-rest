package com.dddd.doctorpatientrest.application.services.service_impls;

import com.dddd.doctorpatientrest.application.exceptions.FullInfoAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.FullInfoNotFoundException;
import com.dddd.doctorpatientrest.application.services.FullInfoService;
import com.dddd.doctorpatientrest.database.entities.FullInfo;
import com.dddd.doctorpatientrest.database.repositories.FullInfoRepository;
import com.dddd.doctorpatientrest.web.mapstruct.dto.FullInfoDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.FullInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FullInfoServiceImpl implements FullInfoService {

	private final FullInfoRepository fullInfoRepository;

	private final FullInfoMapper fullInfoMapper;

	public FullInfoServiceImpl(FullInfoRepository fullInfoRepository,
							   FullInfoMapper fullInfoMapper) {
		this.fullInfoRepository = fullInfoRepository;
		this.fullInfoMapper = fullInfoMapper;
	}


	@Override
	public List<FullInfoDto> findAll() {
		return fullInfoMapper.fullInfoListToFullInfoDtoList(fullInfoRepository.findAll());
	}

	@Override
	public FullInfoDto findById(long id) {
		Optional<FullInfo> fullInfo = fullInfoRepository.findById(id);
		return fullInfo.map(fullInfoMapper::fullInfoToFullInfoDto)
				.orElseThrow(() -> new FullInfoNotFoundException(id));
	}

	@Override
	public FullInfoDto create(FullInfoDto fullInfoDto) {
		if (fullInfoDto.getId() != 0 && !fullInfoRepository.findById(fullInfoDto.getId()).isPresent()) {
			throw new FullInfoAlreadyExistsException(fullInfoDto.getId());
		}
		return fullInfoMapper.fullInfoToFullInfoDto(fullInfoRepository.save(fullInfoMapper.fullInfoDtoToFullInfo(fullInfoDto)));
	}

	@Override
	public FullInfoDto update(FullInfoDto fullInfoDto) {
		Optional<FullInfo> fullInfo = fullInfoRepository.findById(fullInfoDto.getId());
		return fullInfo.map(value -> fullInfoMapper.fullInfoToFullInfoDto(fullInfoRepository
				.save(fullInfoMapper.fullInfoDtoToFullInfo(fullInfoDto))))
				.orElseThrow(() -> new FullInfoNotFoundException(fullInfoDto.getId()));
	}

	@Override
	public void deleteById(long id) {
		fullInfoRepository.deleteById(id);
	}
}
