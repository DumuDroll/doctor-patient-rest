package com.dddd.doctorpatientrest.application.constants;

import lombok.Getter;

@Getter
public enum RoleEnum {

	USER("User"),
	ADMIN("Admin");

	private final String label;

	RoleEnum(String label) {
		this.label = label;
	}

}
