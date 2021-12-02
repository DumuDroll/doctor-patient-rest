package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.database.entities.logger_listeners.DoctorLoggerListener;
import com.dddd.doctorpatientrest.database.entities.pre_remove_listeners.DoctorPreRemoveListener;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners({DoctorLoggerListener.class, DoctorPreRemoveListener.class})
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String experience;

	@OneToMany(mappedBy = "doctor")
	private List<Patient> patients;

}
