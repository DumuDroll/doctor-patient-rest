package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PatientDrugDto {

	private PatientDrugIdDto id;

	private PatientDto patient;

	private DrugDto drug;

	private LocalDate prescriptionStartDate;

	private LocalDate prescriptionEndDate;

	public PatientDrugDto(PatientDto patient, DrugDto drug, LocalDate prescriptionStartDate, LocalDate prescriptionEndDate) {
		this.patient = patient;
		this.drug = drug;
		this.prescriptionStartDate = prescriptionStartDate;
		this.prescriptionEndDate = prescriptionEndDate;
		this.id = new PatientDrugIdDto(drug.getId(),patient.getId());
	}
}

