package com.dddd.doctorpatientrest.database.entities;

import javax.persistence.*;

@Entity
@Table(name="doctors")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


}
