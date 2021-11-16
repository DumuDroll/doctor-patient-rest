package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PatientDto {

	private long id;

	private String firstName;

	private String lastName;

	private FullInfoDto fullInfo;

	private DoctorDto doctor;

	private List<DrugDto> drugs;
}
