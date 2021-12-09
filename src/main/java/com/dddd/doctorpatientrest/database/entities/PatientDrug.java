package com.dddd.doctorpatientrest.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "patient_drug")
@Getter
@Setter
@NoArgsConstructor
public class PatientDrug {

	@EmbeddedId
	private PatientDrugId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("patientId")
	private Patient patient;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("drugId")
	private Drug drug;

	private LocalDate prescriptionStartDate;

	private LocalDate prescriptionEndDate;

	public PatientDrug(Patient patient, Drug drug) {
		this.patient = patient;
		this.drug = drug;
		this.id = new PatientDrugId(patient.getId(), drug.getId());
	}

}
