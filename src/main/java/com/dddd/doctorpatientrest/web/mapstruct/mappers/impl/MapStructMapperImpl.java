/*
package com.dddd.doctorpatientrest.web.mapstruct.mappers.impl;

import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.MapStructMapper;

import java.util.ArrayList;
import java.util.List;

public class MapStructMapperImpl implements MapStructMapper {
	@Override
	public Doctor doctorToDoctorDto(DoctorDto doctorDto) {
		if(doctorDto== null){
			return null;
		}

		Doctor doctor = new Doctor();

		doctor.setId(doctorDto.getId());
		doctor.setName(doctorDto.getName());
		doctor.setPatients(doctorDto.getPatients());

		return doctor;
	}

	@Override
	public DoctorDto doctorDtoToDoctor(Doctor doctor) {
		return null;
	}

	@Override
	public PatientDto patientToPatientDto(Patient patient) {
		return null;
	}

	@Override
	public Patient patientDtoToPatient(PatientDto patientDto) {
		return null;
	}

	@Override
	public List<PatientDto> patientDtoListToPatientList(List<Patient> patientList) {
		if(patientList== null){
			return null;
		}

		List<PatientDto> patientDtoList = new ArrayList<>();
		for(Patient patient: patientList){
			patientDtoList.add(patientToPatientDto(patient));
		}
		return null;
	}

	@Override
	public List<Patient> patientListToPatientDtoList(List<PatientDto> patientDtoList) {
		return null;
	}
}
*/
