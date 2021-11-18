package com.dddd.doctorpatientrest.database.entities;


import com.dddd.doctorpatientrest.database.entities.logger_listeners.FullInfoLoggerListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@EntityListeners(FullInfoLoggerListener.class)
@Table(name = "full_info")
@Getter
@Setter
@NoArgsConstructor
public class FullInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String birthDate;

	private String email;

	private String phoneNumber;

	@OneToOne
	private Patient patient;

}
