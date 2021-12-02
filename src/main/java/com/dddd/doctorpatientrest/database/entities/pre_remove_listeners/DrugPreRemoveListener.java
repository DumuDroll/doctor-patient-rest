package com.dddd.doctorpatientrest.database.entities.pre_remove_listeners;

import com.dddd.doctorpatientrest.database.entities.Drug;

import javax.persistence.PreRemove;

public class DrugPreRemoveListener {
	@PreRemove
	private void methodInvokedBeforeRemove(Drug entity) {
		entity.getPatients().forEach(patient -> patient.getDrugs().remove(entity));
	}
}
