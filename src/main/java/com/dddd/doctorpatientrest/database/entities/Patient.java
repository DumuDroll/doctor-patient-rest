package com.dddd.doctorpatientrest.database.entities;

import javax.persistence.*;

@Entity
@Table(name="patients")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
