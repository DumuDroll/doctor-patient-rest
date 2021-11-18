package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DoctorDto {

	private long id;

	private String name;

	private String experience;

}
