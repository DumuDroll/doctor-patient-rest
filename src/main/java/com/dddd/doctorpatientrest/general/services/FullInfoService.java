package com.dddd.doctorpatientrest.general.services;

import com.dddd.doctorpatientrest.web.mapstruct.dto.FullInfoDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface FullInfoService extends GenericService<FullInfoDto> {

	ResponseEntity<Map<String, Object>> findAllFiltered(String name, int page, int size);

}
