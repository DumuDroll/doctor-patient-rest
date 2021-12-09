package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.database.entities.logger_listeners.PatientLoggerListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@EntityListeners(PatientLoggerListener.class)
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String firstName;

	private String lastName;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private FullInfo fullInfo;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Doctor doctor;

	@OneToMany(
			mappedBy = "drug",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<PatientDrug> drugs = new ArrayList<>();

	public void addDrug(Drug drug) {
		PatientDrug patientDrug = new PatientDrug(this, drug);
		drugs.add(patientDrug);
	}

	public void removeDrug(Drug drug) {
		for (Iterator<PatientDrug> iterator = drugs.iterator(); iterator.hasNext();) {
			PatientDrug patientDrug = iterator.next();
			if (patientDrug.getPatient().equals(this) && patientDrug.getDrug().equals(drug)) {
				iterator.remove();
				patientDrug.setPatient(null);
				patientDrug.setDrug(null);
			}
		}
	}

}