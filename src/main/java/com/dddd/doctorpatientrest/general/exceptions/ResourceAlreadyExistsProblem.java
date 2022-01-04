package com.dddd.doctorpatientrest.general.exceptions;

import lombok.extern.log4j.Log4j2;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

@Log4j2
public class ResourceAlreadyExistsProblem extends AbstractThrowableProblem {

	private static final URI TYPE
			= URI.create("http://localhost:4200/duplicateUser");

	public ResourceAlreadyExistsProblem(String username) {
		super(
				TYPE,
				"User creation error",
				Status.I_AM_A_TEAPOT,
				String.format("User with this email: %s already exists", username)
		);
		log.error(String.format("User with this email: %s already exists", username));
	}
}
