package com.dddd.doctorpatientrest.web.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FullInfoDto {

	@JsonProperty("id")
	private long id;

	@JsonProperty("birthDate")
	private String birthDate;

	@JsonProperty("email")
	private String email;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("patient")
	private PatientDto patient;
}
