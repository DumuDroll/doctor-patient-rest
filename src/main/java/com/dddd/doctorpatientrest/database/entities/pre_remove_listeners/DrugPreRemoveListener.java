package com.dddd.doctorpatientrest.database.entities.pre_remove_listeners;

import com.dddd.doctorpatientrest.database.entities.Drug;

import javax.persistence.PreRemove;

public class DrugPreRemoveListener {

	@PreRemove
	private void preDrugRemove(Drug drug) {
		drug.getPatients().forEach(patientDrug -> {
			if (patientDrug.getDrug().equals(drug)) {
				patientDrug.setDrug(null);
			}
		});
	}
}
