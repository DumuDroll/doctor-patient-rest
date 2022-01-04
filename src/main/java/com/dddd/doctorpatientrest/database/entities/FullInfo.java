package com.dddd.doctorpatientrest.database.entities;


import com.dddd.doctorpatientrest.database.entities.logger_listeners.FullInfoLoggerListener;
import com.dddd.doctorpatientrest.database.entities.pre_persist_listeners.FullInfoUUIDListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@EntityListeners({FullInfoLoggerListener.class, FullInfoUUIDListener.class})
@Table(name = "full_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private UUID uuid;

	private LocalDate birthDate;

	private String email;

	private String phoneNumber;

	@OneToOne(mappedBy = "fullInfo")
	private Patient patient;

}
