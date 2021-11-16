package com.dddd.doctorpatientrest.application.constants;

public class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String DOCTOR_NOT_FOUND = "Could not find doctor with this id: ";
	public static final String DRUG_NOT_FOUND = "Could not find drug with this id: ";
	public static final String PATIENT_NOT_FOUND = "Could not find patient with this id: ";
	public static final String FULL_INFO_NOT_FOUND = "Could not find fullInfo";
	public static final String DOCTOR_ID = "/{doctorId}";
	public static final String PATIENT_ID = "/{patientId}";
	public static final String DRUG_ID = "/{drugId}";
	public static final String FULL_INFO_ID = "/{fullInfoID}";
}
