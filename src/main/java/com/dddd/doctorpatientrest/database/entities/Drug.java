package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.database.entities.logger_listeners.DrugLoggerListener;
import com.dddd.doctorpatientrest.database.entities.pre_remove_listeners.DrugPreRemoveListener;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners({DrugLoggerListener.class, DrugPreRemoveListener.class})
@Table(name = "drugs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drug {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "drugs")
	private List<Patient> patients;

}
