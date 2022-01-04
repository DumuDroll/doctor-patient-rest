package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PatientDrugDto {

	private long patientId;

	private long drugId;

	private UUID uuid;

	private String drugName;

	private LocalDate prescriptionStartDate;

	private LocalDate prescriptionEndDate;

}

