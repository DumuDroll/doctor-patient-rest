package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PatientDto {

	private long id;

	private String firstName;

	private String lastName;

	private FullInfoDto fullInfo;

	private DoctorDto doctor;

	private List<DrugDto> drugs;

	private String email;

	private String doctorName;

	private List<String> drugsNames;
}
