package com.dddd.doctorpatientrest.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DrugNotFoundException extends RuntimeException{

	public DrugNotFoundException(long id) {
		super("Could not find drug with this id: " + id);
	}

}
