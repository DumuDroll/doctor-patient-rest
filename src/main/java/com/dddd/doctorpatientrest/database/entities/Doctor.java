package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.database.entities.logger_listeners.DoctorLoggerListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(DoctorLoggerListener.class)
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String experience;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Patient> patients;

}
