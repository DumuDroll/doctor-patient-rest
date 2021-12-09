package com.dddd.doctorpatientrest.database.entities;


import com.dddd.doctorpatientrest.database.entities.logger_listeners.FullInfoLoggerListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(FullInfoLoggerListener.class)
@Table(name = "full_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private LocalDate birthDate;

	private String email;

	private String phoneNumber;

	@OneToOne(mappedBy = "fullInfo")
	private Patient patient;

}
