package com.dddd.doctorpatientrest.database.entities.logger_listeners;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.database.entities.Drug;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Log4j2
public class DrugLoggerListener {

	@PrePersist
	public void methodInvokedBeforePersist(Drug entity) {
		log.info(Constants.PERSISTING + Constants.DRUG);
	}

	@PostPersist
	public void methodInvokedAfterPersist(Drug entity) {
		log.info(Constants.PERSISTED + Constants.DRUG_WITH_ID + entity.getId());
	}

	@PreUpdate
	public void methodInvokedBeforeUpdate(Drug entity) {
		log.info(Constants.UPDATING + Constants.DRUG_WITH_ID + entity.getId());
	}

	@PostUpdate
	public void methodInvokedAfterUpdate(Drug entity) {
		log.info(Constants.UPDATED + Constants.DRUG_WITH_ID + entity.getId());
	}

	@PreRemove
	private void methodInvokedBeforeRemove(Drug entity) {
		log.info(Constants.REMOVING + Constants.DRUG_WITH_ID + entity.getId());
	}

	@PostRemove
	public void methodInvokedAfterRemove(Drug entity) {
		log.info(Constants.REMOVED + Constants.DRUG_WITH_ID + entity.getId());
	}

}
