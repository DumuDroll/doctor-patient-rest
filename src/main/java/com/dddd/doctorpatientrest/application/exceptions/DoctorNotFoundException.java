package com.dddd.doctorpatientrest.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DoctorNotFoundException extends RuntimeException {

	public DoctorNotFoundException(long id) {
		super("Could not find doctor with this id: " + id);
	}

}
