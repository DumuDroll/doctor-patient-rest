package com.dddd.doctorpatientrest.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PatientAlreadyExistsException extends RuntimeException{

	public PatientAlreadyExistsException(long id) {
		super("Patient with this id: " + id + " already exists");
	}

}
