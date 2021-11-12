package com.dddd.doctorpatientrest.database.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drugs")
@Getter
@Setter
@NoArgsConstructor
public class Drug {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "patients_drugs",
			joinColumns = @JoinColumn(name = "drug_id"),
			inverseJoinColumns = @JoinColumn(name = "patient_id")
	)
	private List<Patient> patients;

}
