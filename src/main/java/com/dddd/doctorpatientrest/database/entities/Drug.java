package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.database.entities.logger_listeners.DrugLoggerListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(DrugLoggerListener.class)
@Table(name = "drugs")
@Getter
@Setter
@NoArgsConstructor
public class Drug {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "drugs")
	private List<Patient> patients;

}
