package com.dddd.doctorpatientrest.database.entities.pre_persist_listeners;

import com.dddd.doctorpatientrest.database.entities.FullInfo;

import javax.persistence.PrePersist;

public class FullInfoUUIDListener {

	@PrePersist
	private void onCreate(FullInfo fullInfo){
		fullInfo.setUuid(java.util.UUID.randomUUID());
	}

}
