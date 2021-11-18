package com.dddd.doctorpatientrest.application.constants;

public class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String DOCTOR_ID = "/{doctorId}";
	public static final String PATIENT_ID = "/{patientId}";
	public static final String DRUG_ID = "/{drugId}";
	public static final String FULL_INFO_ID = "/{fullInfoID}";

	public static final String DOCTOR_NOT_FOUND = "Could not find doctor with this id: ";
	public static final String PATIENT_NOT_FOUND = "Could not find patient with this id: ";
	public static final String DRUG_NOT_FOUND = "Could not find drug with this id: ";
	public static final String FULL_INFO_NOT_FOUND = "Could not find fullInfo with this id: ";

	public static final String DOCTOR_ALREADY_EXISTS = "Doctor with id already exists. Id: ";
	public static final String PATIENT_ALREADY_EXISTS = "Patient with id already exists. Id: ";
	public static final String DRUG_ALREADY_EXISTS = "Drug with id already exists. Id: ";
	public static final String FULL_INFO_ALREADY_EXISTS = "FullInfo with id already exists. Id: ";

	public static final String DOCTOR_WITH_ID = "doctor with id: ";
	public static final String PATIENT_WITH_ID = "patient with id: ";
	public static final String DRUG_WITH_ID = "drug with id: ";
	public static final String FULL_INFO_WITH_ID = "fullInfo with id: ";

	public static final String DOCTOR = "doctor";
	public static final String PATIENT = "patient";
	public static final String DRUG = "drug";
	public static final String FULL_INFO = "fullInfo";

	public static final String PERSISTING = "Persisting ";
	public static final String PERSISTED = "Persisted ";
	public static final String UPDATING = "Updating ";
	public static final String UPDATED = "Updated ";
	public static final String REMOVING = "Removing ";
	public static final String REMOVED = "Removed ";


}
