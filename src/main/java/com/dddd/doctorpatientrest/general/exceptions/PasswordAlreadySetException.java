package com.dddd.doctorpatientrest.general.exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PasswordAlreadySetException extends RuntimeException {

	public PasswordAlreadySetException(String message, String username) {
		super(message + username);
		log.error(message + username);
	}

}
