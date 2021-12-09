package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PatientDrugIdDto {

	private int patientId;

	private int drugId;

}
