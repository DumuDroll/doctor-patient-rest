package com.dddd.doctorpatientrest.database.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
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


	@OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private FullInfo fullInfo;

	@ManyToOne
	@JsonBackReference
	private Doctor doctor;

	@ManyToMany(mappedBy = "patients", fetch = FetchType.LAZY)
	private List<Drug> drugs;

}