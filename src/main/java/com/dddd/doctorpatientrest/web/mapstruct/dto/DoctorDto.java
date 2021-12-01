package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DoctorDto {

	private long id;

	private String name;

	private String experience;

	private List<PatientSlimDto> patients;

	private List<String> patientsNames;

}
