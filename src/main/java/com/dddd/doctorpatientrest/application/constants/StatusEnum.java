package com.dddd.doctorpatientrest.application.constants;

import lombok.Getter;

@Getter
public enum StatusEnum {

	FIRST_IN("FIRST_IN"),
	ACTIVE("ACTIVE"),
	BLOCKED("BLOCKED");

	private final String label;

	StatusEnum(String label) {
		this.label = label;
	}

}
