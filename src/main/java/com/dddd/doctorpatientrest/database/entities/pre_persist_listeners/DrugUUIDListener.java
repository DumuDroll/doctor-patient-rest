package com.dddd.doctorpatientrest.database.entities.pre_persist_listeners;

import com.dddd.doctorpatientrest.database.entities.Drug;

import javax.persistence.PrePersist;

public class DrugUUIDListener {

	@PrePersist
	private void onCreate(Drug drug){
		drug.setUuid(java.util.UUID.randomUUID());
	}

}
