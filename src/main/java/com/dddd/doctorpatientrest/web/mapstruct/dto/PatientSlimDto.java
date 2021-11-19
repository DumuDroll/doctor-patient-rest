package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PatientSlimDto {

	private long id;

	private String firstName;

	private String lastName;

}
