package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PatientDrugDto {

	private long patientId;

	private long drugId;

	private LocalDate prescriptionStartDate;

	private LocalDate prescriptionEndDate;

}

