package com.dddd.doctorpatientrest.database.entities.pre_persist_listeners;

import com.dddd.doctorpatientrest.database.entities.Doctor;

import javax.persistence.PrePersist;

public class DoctorUUIDListener {

	@PrePersist
	private void onCreate(Doctor doctor){
		doctor.setUuid(java.util.UUID.randomUUID());
	}

}
