package com.dddd.doctorpatientrest.application.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {

	public ResourceAlreadyExistsException(String message, long id) {
		super(message + id);
		log.error(message + id);
	}

	public ResourceAlreadyExistsException(String message, String username) {
		super(message + username);
		log.error(message + username);
	}
}
