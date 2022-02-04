package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.database.entities.logger_listeners.PatientLoggerListener;
import com.dddd.doctorpatientrest.database.entities.pre_persist_listeners.PatientUUIDListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners({PatientLoggerListener.class, PatientUUIDListener.class})
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private UUID uuid;

	private String firstName;

	private String lastName;

	private String diagnosisFilePath;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private FullInfo fullInfo;

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Doctor doctor;

	@OneToMany(
			mappedBy = "patient",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<PatientDrug> drugs = new ArrayList<>();

}