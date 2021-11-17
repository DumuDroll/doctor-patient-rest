package com.dddd.doctorpatientrest.application.constants;

public class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String DOCTOR_ID = "/{doctorId}";
	public static final String PATIENT_ID = "/{patientId}";
	public static final String DRUG_ID = "/{drugId}";
	public static final String FULL_INFO_ID = "/{fullInfoID}";

}
