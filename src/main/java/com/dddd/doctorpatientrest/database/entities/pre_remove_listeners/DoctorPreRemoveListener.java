package com.dddd.doctorpatientrest.database.entities.pre_remove_listeners;

import com.dddd.doctorpatientrest.database.entities.Doctor;

import javax.persistence.PreRemove;

public class DoctorPreRemoveListener {
	@PreRemove
	private void methodInvokedBeforeRemove(Doctor entity) {
		entity.getPatients().forEach(patient -> patient.setDoctor(null));
	}
}
