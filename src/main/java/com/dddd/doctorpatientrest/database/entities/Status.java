package com.dddd.doctorpatientrest.database.entities;

import com.dddd.doctorpatientrest.general.constants.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "statuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status implements Serializable {

	private static final long serialVersionUID = 1305122041950251207L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private UUID uuid;

	@Column(unique=true)
	@Enumerated(EnumType.STRING)
	StatusEnum name;

	public Status(StatusEnum name) {
		this.name = name;
	}
}
