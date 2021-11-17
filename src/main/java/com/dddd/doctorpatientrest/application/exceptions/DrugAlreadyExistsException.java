package com.dddd.doctorpatientrest.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DrugAlreadyExistsException extends RuntimeException{

	public DrugAlreadyExistsException(long id) {
		super("Drug with this id: " + id + " already exists");
	}

}
