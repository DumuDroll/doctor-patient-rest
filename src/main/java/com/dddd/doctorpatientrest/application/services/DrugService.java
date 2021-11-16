package com.dddd.doctorpatientrest.application.services;

import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;

public interface DrugService extends GenericService<DrugDto>{

	DrugDto addPatientToDrug(long patientId, DrugDto drugDto);

}
