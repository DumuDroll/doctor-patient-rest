package com.dddd.doctorpatientrest.database.entities.logger_listeners;

import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.database.entities.FullInfo;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Log4j2
public class FullInfoLoggerListener {

	@PrePersist
	public void methodInvokedBeforePersist(FullInfo fullInfo) {
		log.info(Constants.PERSISTING + Constants.FULL_INFO);
	}

	@PostPersist
	public void methodInvokedAfterPersist(FullInfo fullInfo) {
		log.info(Constants.PERSISTED + Constants.FULL_INFO_WITH_ID + fullInfo.getId());
	}

	@PreUpdate
	public void methodInvokedBeforeUpdate(FullInfo fullInfo) {
		log.info(Constants.UPDATING + Constants.FULL_INFO_WITH_ID + fullInfo.getId());
	}

	@PostUpdate
	public void methodInvokedAfterUpdate(FullInfo fullInfo) {
		log.info(Constants.UPDATED + Constants.FULL_INFO_WITH_ID + fullInfo.getId());
	}

	@PreRemove
	private void methodInvokedBeforeRemove(FullInfo fullInfo) {
		log.info(Constants.REMOVING + Constants.FULL_INFO_WITH_ID + fullInfo.getId());
	}

	@PostRemove
	public void methodInvokedAfterRemove(FullInfo fullInfo) {
		log.info(Constants.REMOVED + Constants.FULL_INFO_WITH_ID + fullInfo.getId());
	}

}
