package com.dddd.doctorpatientrest.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FullInfoAlreadyExistsException extends RuntimeException{

	public FullInfoAlreadyExistsException(long id) {
		super("FillInfo with this id: " + id + " already exists");
	}

}
