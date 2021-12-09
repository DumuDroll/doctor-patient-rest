package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.database.entities.FullInfo;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.database.entities.PatientDrug;
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
	PatientDto patientToPatientDto(Patient patient);

	@Mapping(source = "drugs", target = "drugs", qualifiedByName = "patientDrugDtoToPatientDrugDto")
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

	@Named("patientDrugDtoToPatientDrugDto")
	default List<PatientDrug> patientDrugDtoToPatientDrugDto(List<PatientDrug> patientDrugList){
		//TO DO
		return null;
	}

}
