package com.dddd.doctorpatientrest.database.entities.pre_persist_listeners;

import com.dddd.doctorpatientrest.database.entities.PatientDrug;

import javax.persistence.PrePersist;

public class PatientDrugUUIDListener {

	@PrePersist
	private void onCreate(PatientDrug patientDrug){
		patientDrug.setUuid(java.util.UUID.randomUUID());
	}

}
