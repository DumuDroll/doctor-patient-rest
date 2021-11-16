package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DrugDto {

	private long id;

	private String name;

	private List<PatientSlimDto> patients;

}
