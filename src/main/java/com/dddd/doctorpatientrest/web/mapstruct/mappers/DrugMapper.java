package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.Drug;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.rabbit_dto.DrugRabbitDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DrugMapper {

	Drug drugDtoToDrug(DrugDto drugDto);

	DrugDto drugToDrugDto(Drug drug);

	DrugRabbitDto drugDtoToDrugRabbitDto(DrugDto drugDto);

	List<DrugDto> drugListToDrugDtoList(List<Drug> drugs);

}
