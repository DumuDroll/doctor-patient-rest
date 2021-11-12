package com.dddd.doctorpatientrest.database.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "full_info")
@Getter
@Setter
@NoArgsConstructor
public class FullInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String dateOfBith;

	private String email;

	private String phoneNumber;

	@OneToOne(cascade = CascadeType.ALL)
	private Patient patient;

}
