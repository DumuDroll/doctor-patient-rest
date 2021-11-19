package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DrugDto {

	private long id;

	private String name;

	private List<PatientSlimDto> patients;

}
