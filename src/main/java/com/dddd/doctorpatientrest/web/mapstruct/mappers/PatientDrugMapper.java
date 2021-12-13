package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.PatientDrug;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDrugDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PatientDrugMapper {

	PatientDrugDto patientDrugToPatientDrugDto(PatientDrug patientDrug);

	PatientDrug patientDrugDtoToPatientDrug(PatientDrugDto patientDrugDro);

	List<PatientDrugDto> patientDrugListToPatientDrugDtoList(List<PatientDrug> patientDrugList);
}
