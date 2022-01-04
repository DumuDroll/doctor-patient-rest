package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.database.entities.logger_listeners.DoctorLoggerListener;
import com.dddd.doctorpatientrest.database.entities.pre_persist_listeners.DoctorUUIDListener;
import com.dddd.doctorpatientrest.database.entities.pre_remove_listeners.DoctorPreRemoveListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners({DoctorLoggerListener.class, DoctorPreRemoveListener.class, DoctorUUIDListener.class})
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uuid;

	private String name;

	private String experience;

	@OneToMany(mappedBy = "doctor")
	private List<Patient> patients;

}
