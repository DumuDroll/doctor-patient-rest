package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DrugDto {

	@ToString.Exclude
	private long id;

	private UUID uuid;

	private String name;

	public DrugDto(long id, String name) {
		this.id = id;
		this.name = name;
	}

}
