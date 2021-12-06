package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.database.entities.logger_listeners.PatientLoggerListener;
import lombok.*;

import javax.persistence.*;
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
	private Long id;

	private String firstName;

	private String lastName;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private FullInfo fullInfo;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Doctor doctor;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
			name = "patients_drugs",
			joinColumns = @JoinColumn(name = "patient_id"),
			inverseJoinColumns = @JoinColumn(name = "drug_id")
	)
	private List<Drug> drugs;

}