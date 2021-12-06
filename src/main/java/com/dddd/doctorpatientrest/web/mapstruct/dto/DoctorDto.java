package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DoctorDto {

	@ToString.Exclude
	private long id;

	private String name;

	private String experience;

}
