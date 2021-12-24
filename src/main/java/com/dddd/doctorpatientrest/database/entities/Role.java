package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.application.constants.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique=true)
	@Enumerated(EnumType.ORDINAL)
	RoleEnum name;

	public Role(RoleEnum name) {
		this.name = name;
	}

}
