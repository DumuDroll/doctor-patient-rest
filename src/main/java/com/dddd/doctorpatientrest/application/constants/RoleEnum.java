package com.dddd.doctorpatientrest.application.constants;

import lombok.Getter;

@Getter
public enum RoleEnum {

	ROLE_USER("User"),
	ROLE_ADMIN("Admin");

	private final String label;

	RoleEnum(String label) {
		this.label = label;
	}

}
