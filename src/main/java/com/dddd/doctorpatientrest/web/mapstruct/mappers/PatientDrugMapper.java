package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.PatientDrug;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDrugDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PatientDrugMapper {

	PatientDrugDto patientDrugToPatientDrugDto(PatientDrug patientDrug);

	PatientDrug patientDrugDtoToPatientDrug(PatientDrugDto patientDrugDro);

	default List<PatientDrugDto> patientDrugListToPatientDrugDtoList(List<PatientDrug> patientDrugList) {
		List<PatientDrugDto> patientDrugDtoList = new ArrayList<>();
		if (patientDrugList != null) {
			patientDrugList.forEach(patientDrug -> {
				PatientDrugDto patientDrugDto = new PatientDrugDto();
				patientDrugDto.setPatientId(patientDrug.getId().getPatientId());
				patientDrugDto.setDrugId(patientDrug.getId().getDrugId());
				patientDrugDto.setPrescriptionEndDate(patientDrug.getPrescriptionEndDate());
				patientDrugDto.setPrescriptionStartDate(patientDrug.getPrescriptionStartDate());
				patientDrugDto.setDrugName(patientDrug.getDrug().getName());
				patientDrugDtoList.add(patientDrugDto);
			});
		}
		return patientDrugDtoList;
	}
}
