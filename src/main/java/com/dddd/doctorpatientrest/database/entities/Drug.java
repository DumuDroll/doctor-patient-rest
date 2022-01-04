package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.database.entities.logger_listeners.DrugLoggerListener;
import com.dddd.doctorpatientrest.database.entities.pre_persist_listeners.DrugUUIDListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners({DrugLoggerListener.class, DrugUUIDListener.class})
@Table(name = "drugs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drug {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private UUID uuid;

	private String name;

	@OneToMany(
			mappedBy = "drug",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<PatientDrug> patients = new ArrayList<>();

}
