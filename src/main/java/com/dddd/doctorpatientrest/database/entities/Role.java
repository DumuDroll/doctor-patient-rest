package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.general.constants.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

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

	private UUID uuid;

	@Column(unique=true)
	@Enumerated(EnumType.ORDINAL)
	RoleEnum name;

	public Role(RoleEnum name) {
		this.name = name;
	}

}
