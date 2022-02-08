package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.*;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDrugDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.rabbit_dto.PatientRabbitDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PatientMapper {

	@Mapping(source = "fullInfo", target = "email", qualifiedByName = "fullInfoToEmail")
	@Mapping(source = "doctor", target = "doctorName", qualifiedByName = "doctorToDoctorName")
	@Mapping(source = "drugs", target = "drugsNames", qualifiedByName = "drugsToDrugsNames")
	@Mapping(source = "drugs", target = "drugs", qualifiedByName = "patientDrugListToPatientDrugDtoList")
	PatientDto toPatientDto(Patient patient);

	PatientRabbitDto patientDtoToPatientRabbitDto(PatientDto patientDto);

	@Mapping(source = "fullInfo.birthDate", target = "fullInfo.birthDate", qualifiedByName = "birthDateStringToBirthDateDate")
	@Mapping(source = "drugs", target = "drugs", qualifiedByName = "patientDrugDtoToPatientDrug")
	Patient toPatient(PatientDto patientDto);

	List<PatientDto> toPatientDtoList(List<Patient> patients);

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
	default List<PatientDrug> toPatientDrug(List<PatientDrugDto> patientDrugDtoList) {
		List<PatientDrug> patientDrugList = new ArrayList<>();
		if (patientDrugDtoList != null) {
			patientDrugDtoList.forEach(patientDrugDto -> {
				PatientDrug patientDrug = new PatientDrug();
				patientDrug.setId(new PatientDrugId(patientDrugDto.getPatientId(), patientDrugDto.getDrugId()));
				patientDrug.setPrescriptionEndDate(patientDrugDto.getPrescriptionEndDate());
				patientDrug.setPrescriptionStartDate(patientDrugDto.getPrescriptionStartDate());
				patientDrugList.add(patientDrug);
			});
		}
		return patientDrugList;
	}

	@Named("patientDrugListToPatientDrugDtoList")
	default List<PatientDrugDto> toPatientDrugDtoList(List<PatientDrug> patientDrugList) {
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

	@Named("birthDateStringToBirthDateDate")
	default LocalDate birthDateStringToBirthDateDate(String birthDate) {
		if (birthDate != null && !birthDate.equals("")) {
			return LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		return null;
	}
}
