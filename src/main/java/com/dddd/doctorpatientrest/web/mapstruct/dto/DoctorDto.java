package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DoctorDto {

	@ToString.Exclude
	private long id;

	@ToString.Exclude
	private UUID uuid;

	private String name;

	private String experience;

}
