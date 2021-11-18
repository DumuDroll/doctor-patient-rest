package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.database.entities.logger_listeners.PatientLoggerListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(PatientLoggerListener.class)
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	@OneToOne(mappedBy = "patient", cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private FullInfo fullInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	private Doctor doctor;

	@ManyToMany(cascade = {
			CascadeType.PERSIST
	}, fetch = FetchType.LAZY)
	@JoinTable(
			name = "patients_drugs",
			joinColumns = @JoinColumn(name = "patient_id"),
			inverseJoinColumns = @JoinColumn(name = "drug_id")
	)
	private List<Drug> drugs;

}