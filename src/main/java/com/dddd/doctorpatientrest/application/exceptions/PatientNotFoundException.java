package com.dddd.doctorpatientrest.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException{

	public PatientNotFoundException(long id) {
		super("Could not find patient with this id: " + id);
	}

}
