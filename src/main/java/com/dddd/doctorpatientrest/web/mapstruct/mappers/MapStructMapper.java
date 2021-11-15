package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.database.entities.Drug;
import com.dddd.doctorpatientrest.database.entities.FullInfo;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.FullInfoDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	Doctor doctorDtoToDoctor(DoctorDto doctorDto);

	DoctorDto doctorToDoctorDto(Doctor doctor);

	List<DoctorDto> doctorListToDoctorDtoList(List<Doctor> doctors);

	List<Doctor> doctorDtoListToDoctorList(List<DoctorDto> doctorDtos);

	PatientDto patientToPatientDto(Patient patient);

	Patient patientDtoToPatient(PatientDto patientDto);

	List<PatientDto> patientListToPatientDtoList(List<Patient> patients);

	List<Patient> patientDtoListToPatientList(List<PatientDto> patientDtos);

	FullInfoDto fullInfoToFullInfoDto(FullInfo fullInfo);

	FullInfo fullInfoDtoToFullInfo(FullInfoDto fullInfoDto);

	DrugDto drugToDrugDto(Drug drug);

	Drug drugDtoToDrug(DrugDto drugDto);

	List<Drug> drugDtoListToDrugList(List<DrugDto> drugDtos);

	List<DrugDto> drugListToDrugDtoList(List<Drug> drugs);

}
