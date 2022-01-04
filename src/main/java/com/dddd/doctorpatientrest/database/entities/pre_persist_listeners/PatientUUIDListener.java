package com.dddd.doctorpatientrest.database.entities.pre_persist_listeners;

import com.dddd.doctorpatientrest.database.entities.Patient;

import javax.persistence.PrePersist;

public class PatientUUIDListener {

	@PrePersist
	private void onCreate(Patient patient){
		patient.setUuid(java.util.UUID.randomUUID());
	}

}
