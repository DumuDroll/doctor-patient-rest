package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface DoctorMapper {

	@Mapping(source = "patients", target = "patientsNames", qualifiedByName = "patientsToPatientsNames")
	DoctorDto doctorToDoctorDto(Doctor doctor);

	Doctor doctorDtoToDoctor(DoctorDto doctorDto);

	List<DoctorDto> doctorListToDoctorDtoList(List<Doctor> doctors);

	List<Doctor> doctorListDtoToDoctorList(List<DoctorDto> doctorDtoList);

	@Named("patientsToPatientsNames")
	default List<String> patientsToPatientsNames(List<Patient> patients) {
		List<String> patientNames = new ArrayList<>();
		if(patients!=null){
			patients.forEach(patient -> patientNames.add(patient.getFirstName().concat(" ").concat(patient.getLastName())));
		}
		return patientNames;
	}

}
