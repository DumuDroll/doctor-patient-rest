package com.dddd.doctorpatientrest.database.entities.logger_listeners;

import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.database.entities.Drug;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Log4j2
public class DrugLoggerListener {

	@PrePersist
	public void methodInvokedBeforePersist(Drug drug) {
		log.info(Constants.PERSISTING + Constants.DRUG);
	}

	@PostPersist
	public void methodInvokedAfterPersist(Drug drug) {
		log.info(Constants.PERSISTED + Constants.DRUG_WITH_ID + drug.getId());
	}

	@PreUpdate
	public void methodInvokedBeforeUpdate(Drug drug) {
		log.info(Constants.UPDATING + Constants.DRUG_WITH_ID + drug.getId());
	}

	@PostUpdate
	public void methodInvokedAfterUpdate(Drug drug) {
		log.info(Constants.UPDATED + Constants.DRUG_WITH_ID + drug.getId());
	}

	@PreRemove
	private void methodInvokedBeforeRemove(Drug drug) {
		log.info(Constants.REMOVING + Constants.DRUG_WITH_ID + drug.getId());
	}

	@PostRemove
	public void methodInvokedAfterRemove(Drug drug) {
		log.info(Constants.REMOVED + Constants.DRUG_WITH_ID + drug.getId());
	}

}
