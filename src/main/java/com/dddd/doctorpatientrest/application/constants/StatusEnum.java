package com.dddd.doctorpatientrest.application.constants;

import lombok.Getter;

@Getter
public enum StatusEnum {

	FIRST_IN("first_in"),
	ACTIVE("active"),
	BLOCKED("blocked");

	private final String label;

	StatusEnum(String label) {
		this.label = label;
	}

}
