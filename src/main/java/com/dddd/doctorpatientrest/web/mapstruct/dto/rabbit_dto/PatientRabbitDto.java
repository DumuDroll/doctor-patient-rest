package com.dddd.doctorpatientrest.web.mapstruct.dto.rabbit_dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PatientRabbitDto {

	private long id;

	private UUID uuid;

	private byte[] diagnosis;

	private String diagnosisFilePath;

	private FullInfoRabbitDto fullInfo;

	private DoctorRabbitDto doctor;

	private List<DrugRabbitDto> drugs;

}
