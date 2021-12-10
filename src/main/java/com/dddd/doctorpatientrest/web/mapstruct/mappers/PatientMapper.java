package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.*;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDrugDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PatientMapper {

	@Mapping(source = "fullInfo", target = "email", qualifiedByName = "fullInfoToEmail")
	@Mapping(source = "doctor", target = "doctorName", qualifiedByName = "doctorToDoctorName")
	@Mapping(source = "drugs", target = "drugsNames", qualifiedByName = "drugsToDrugsNames")
	@Mapping(source = "drugs", target = "drugs", qualifiedByName = "patientDrugToPatientDrugDto")
	PatientDto patientToPatientDto(Patient patient);

	@Mapping(source = "drugs", target = "drugs", qualifiedByName = "patientDrugDtoToPatientDrug")
	Patient patientDtoToPatient(PatientDto patientDto);

	List<PatientDto> patientListToPatientDtoList(List<Patient> patients);

	@Named("fullInfoToEmail")
	default String fullInfoToEmail(FullInfo fullInfo) {
		return fullInfo.getEmail();
	}

	@Named("doctorToDoctorName")
	default String doctorToDoctorName(Doctor doctor) {
		if (doctor != null) {
			return doctor.getName();
		}
		return null;
	}

	@Named("drugsToDrugsNames")
	default List<String> drugsToDrugsNames(List<PatientDrug> drugs) {
		List<String> drugNames = new ArrayList<>();
		if (drugs != null) {
			drugs.forEach(drug -> drugNames.add(drug.getDrug().getName()));
		}
		return drugNames;
	}

	@Named("patientDrugDtoToPatientDrug")
	default List<PatientDrug> patientDrugDtoToPatientDrug(List<PatientDrugDto> patientDrugDtoList) {
		List<PatientDrug> patientDrugList = new ArrayList<>();
		if (patientDrugDtoList != null) {
			patientDrugDtoList.forEach(patientDrugDto -> {
				PatientDrug patientDrug = new PatientDrug();
				patientDrug.setId(new PatientDrugId(patientDrugDto.getPatientId(), patientDrugDto.getDrugId()));
				patientDrugList.add(patientDrug);
			});
		}
		return patientDrugList;
	}

	@Named("patientDrugToPatientDrugDto")
	default List<PatientDrugDto> patientDrugToPatientDrugDto(List<PatientDrug> patientDrugList) {
		List<PatientDrugDto> patientDrugDtoList = new ArrayList<>();
		if (patientDrugList != null) {
			patientDrugList.forEach(patientDrug -> {
				PatientDrugDto patientDrugDto = new PatientDrugDto();
				patientDrugDto.setPatientId(patientDrug.getId().getPatientId());
				patientDrugDto.setDrugId(patientDrug.getId().getDrugId());
				patientDrugDtoList.add(patientDrugDto);
			});
		}
		return patientDrugDtoList;
	}
}
