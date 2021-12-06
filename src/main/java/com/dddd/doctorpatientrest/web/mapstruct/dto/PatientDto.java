package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PatientDto {

	@ToString.Exclude
	private long id;

	private String firstName;

	private String lastName;

	private FullInfoDto fullInfo;

	@ToString.Exclude
	private DoctorDto doctor;

	@ToString.Exclude
	private List<DrugDto> drugs;

	@ToString.Exclude
	private String email;

	@ToString.Exclude
	private String doctorName;

	@ToString.Exclude
	private List<String> drugsNames;
}
