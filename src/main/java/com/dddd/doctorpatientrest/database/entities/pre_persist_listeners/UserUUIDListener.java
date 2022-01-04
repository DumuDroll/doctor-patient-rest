package com.dddd.doctorpatientrest.database.entities.pre_persist_listeners;

import com.dddd.doctorpatientrest.database.entities.User;

import javax.persistence.PrePersist;

public class UserUUIDListener {

	@PrePersist
	private void onCreate(User user){
		user.setUuid(java.util.UUID.randomUUID());
	}

}
