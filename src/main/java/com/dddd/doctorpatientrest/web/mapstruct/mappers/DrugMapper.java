package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.Drug;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DrugMapper {

	DrugDto drugToDrugDto(Drug drug);

	Drug drugDtoToDrug(DrugDto drugDto);

	List<Drug> drugDtoListToDrugList(List<DrugDto> drugDtoList);

	List<DrugDto> drugListToDrugDtoList(List<Drug> drugs);

}
