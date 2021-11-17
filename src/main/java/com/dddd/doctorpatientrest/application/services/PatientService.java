package com.dddd.doctorpatientrest.application.services;

import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;

public interface PatientService extends GenericService<PatientDto>{

	PatientDto addDoctorToPatient(long doctorId, PatientDto patientDto);

}
