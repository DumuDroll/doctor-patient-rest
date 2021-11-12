package com.dddd.doctorpatientrest.application.exception;

public class DoctorNotFoundException extends RuntimeException {

	DoctorNotFoundException(Long id){
		super("Could not find doctor" + id);
	}
}
