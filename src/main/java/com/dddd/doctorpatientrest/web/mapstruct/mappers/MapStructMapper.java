package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	Doctor doctorDtoToDoctor(DoctorDto doctorDto);

	DoctorDto doctorToDoctorDto(Doctor doctor);

	PatientDto patientToPatientDto(Patient patient);

	Patient patientDtoToPatient(PatientDto patientDto);

	List<PatientDto> patientDtoListToPatientList(List<Patient> patientList);

	List<Patient> patientListToPatientDtoList(List<PatientDto> patientDtoList);

}
