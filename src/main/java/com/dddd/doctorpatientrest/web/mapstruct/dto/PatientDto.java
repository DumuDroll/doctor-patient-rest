package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PatientDto {

	@ToString.Exclude
	private long id;

	@ToString.Exclude
	private UUID uuid;

	private String firstName;

	private String lastName;

	private FullInfoDto fullInfo;

	@ToString.Exclude
	private byte[] diagnosis;

	@ToString.Exclude
	private DoctorDto doctor;

	@ToString.Exclude
	private List<PatientDrugDto> drugs;

	@ToString.Exclude
	private String email;

	@ToString.Exclude
	private String doctorName;

	@ToString.Exclude
	private List<String> drugsNames;

	public PatientDto(long id,
					  String firstName,
					  String lastName,
					  FullInfoDto fullInfo,
					  DoctorDto doctor,
					  List<PatientDrugDto> drugs,
					  String email,
					  String doctorName,
					  List<String> drugsNames) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullInfo = fullInfo;
		this.doctor = doctor;
		this.drugs = drugs;
		this.email = email;
		this.doctorName = doctorName;
		this.drugsNames = drugsNames;
	}
}
