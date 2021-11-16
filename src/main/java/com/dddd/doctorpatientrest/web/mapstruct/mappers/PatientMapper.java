package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientSlimDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PatientMapper {

	PatientDto patientToPatientDto(Patient patient);

	Patient patientDtoToPatient(PatientDto patientDto);

	List<PatientDto> patientListToPatientDtoList(List<Patient> patients);

	List<PatientSlimDto> patientListToPatientSlimDtoList(List<Patient> patients);

//	List<Patient> patientSlimDtoList(List<PatientSlimDto> patientSlimDtoList);
//
//	List<Patient> patientDtoListToPatientList(List<PatientDto> patientDtoList);


}
