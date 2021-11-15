package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FullInfoDto {

	private long id;

	private String birthDate;

	private String email;

	private String phoneNumber;

}
