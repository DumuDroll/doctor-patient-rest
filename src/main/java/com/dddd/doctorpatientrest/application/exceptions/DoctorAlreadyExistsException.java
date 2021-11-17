package com.dddd.doctorpatientrest.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DoctorAlreadyExistsException extends RuntimeException {

	public DoctorAlreadyExistsException(long id) {
		super("Doctor with this id: "+ id + " already exists");
	}

}
