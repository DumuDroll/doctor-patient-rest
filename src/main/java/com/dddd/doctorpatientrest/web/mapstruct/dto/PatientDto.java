package com.dddd.doctorpatientrest.web.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PatientDto {

	@JsonProperty("id")
	private long id;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("fullInfo")
	private FullInfoDto fullInfo;

	@JsonProperty("doctor")
	private DoctorDto doctor;

	@JsonProperty("drugs")
	private List<DrugDto> drugs;
}
