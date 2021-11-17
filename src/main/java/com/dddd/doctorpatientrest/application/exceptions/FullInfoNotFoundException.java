package com.dddd.doctorpatientrest.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FullInfoNotFoundException extends RuntimeException {

	public FullInfoNotFoundException(long id) {
		super("Could not find fullInfo with this id: " + id);
	}

}