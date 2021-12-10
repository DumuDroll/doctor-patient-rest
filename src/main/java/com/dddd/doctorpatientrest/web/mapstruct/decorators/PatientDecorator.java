package com.dddd.doctorpatientrest.web.mapstruct.decorators;

import com.dddd.doctorpatientrest.database.entities.Drug;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.database.repositories.DrugRepository;
import com.dddd.doctorpatientrest.database.repositories.PatientRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatientDecorator {

	private final PatientRepository patientRepository;

	private final DrugRepository drugRepository;

	public PatientDecorator(PatientRepository patientRepository,
							DrugRepository drugRepository) {
		this.patientRepository = patientRepository;
		this.drugRepository = drugRepository;
	}

	public Patient decorate(Patient patient){
		patient.getDrugs().forEach(patientDrug -> {
			Optional<Patient> patientOptional = patientRepository.findById(patientDrug.getId().getPatientId());
			patientOptional.ifPresent(patientDrug::setPatient);
			Optional<Drug> drugOptional = drugRepository.findById(patientDrug.getId().getDrugId());
			drugOptional.ifPresent(patientDrug::setDrug);
		});
		return patient;
	}
}
