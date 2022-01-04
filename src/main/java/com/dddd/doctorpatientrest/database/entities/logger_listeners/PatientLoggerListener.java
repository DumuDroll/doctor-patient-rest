package com.dddd.doctorpatientrest.database.entities.logger_listeners;

import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.database.entities.Patient;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Log4j2
public class PatientLoggerListener {

	@PrePersist
	public void methodInvokedBeforePersist(Patient patient) {
		log.info(Constants.PERSISTING + Constants.PATIENT);
	}

	@PostPersist
	public void methodInvokedAfterPersist(Patient patient) {
		log.info(Constants.PERSISTED + Constants.PATIENT_WITH_ID + patient.getId());
	}

	@PreUpdate
	public void methodInvokedBeforeUpdate(Patient patient) {
		log.info(Constants.UPDATING + Constants.PATIENT_WITH_ID + patient.getId());
	}

	@PostUpdate
	public void methodInvokedAfterUpdate(Patient patient) {
		log.info(Constants.UPDATED + Constants.PATIENT_WITH_ID + patient.getId());
	}

	@PreRemove
	private void methodInvokedBeforeRemove(Patient patient) {
		log.info(Constants.REMOVING + Constants.PATIENT_WITH_ID + patient.getId());
	}

	@PostRemove
	public void methodInvokedAfterRemove(Patient patient) {
		log.info(Constants.REMOVED + Constants.PATIENT_WITH_ID + patient.getId());
	}

}
