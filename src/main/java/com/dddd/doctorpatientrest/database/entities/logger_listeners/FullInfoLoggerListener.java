package com.dddd.doctorpatientrest.database.entities.logger_listeners;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.database.entities.FullInfo;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Log4j2
public class FullInfoLoggerListener {

	@PrePersist
	public void methodInvokedBeforePersist(FullInfo entity) {
		log.info(Constants.PERSISTING + Constants.FULL_INFO);
	}

	@PostPersist
	public void methodInvokedAfterPersist(FullInfo entity) {
		log.info(Constants.PERSISTED + Constants.FULL_INFO_WITH_ID + entity.getId());
	}

	@PreUpdate
	public void methodInvokedBeforeUpdate(FullInfo entity) {
		log.info(Constants.UPDATING + Constants.FULL_INFO_WITH_ID + entity.getId());
	}

	@PostUpdate
	public void methodInvokedAfterUpdate(FullInfo entity) {
		log.info(Constants.UPDATED + Constants.FULL_INFO_WITH_ID + entity.getId());
	}

	@PreRemove
	private void methodInvokedBeforeRemove(FullInfo entity) {
		log.info(Constants.REMOVING + Constants.FULL_INFO_WITH_ID + entity.getId());
	}

	@PostRemove
	public void methodInvokedAfterRemove(FullInfo entity) {
		log.info(Constants.REMOVED + Constants.FULL_INFO_WITH_ID + entity.getId());
	}

}
