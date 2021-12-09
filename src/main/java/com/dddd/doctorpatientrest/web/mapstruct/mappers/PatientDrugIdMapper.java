package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.PatientDrugId;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDrugIdDto;

public interface PatientDrugIdMapper {

	PatientDrugIdDto patientDrugIdToPatientDrugDto(PatientDrugId patientDrugId);

	PatientDrugId patientDrugIdDtoToPatientDrugId(PatientDrugIdDto patientDrugIdDto);
}
