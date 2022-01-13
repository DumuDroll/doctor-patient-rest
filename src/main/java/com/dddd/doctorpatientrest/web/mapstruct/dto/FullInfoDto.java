package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FullInfoDto {

	@ToString.Exclude
	private long id;

	@ToString.Exclude
	private UUID uuid;

	@ToString.Exclude
	private String birthDate;

	private String email;

	@ToString.Exclude
	private String phoneNumber;

	public FullInfoDto(long id, String birthDate, String email, String phoneNumber) {
		this.id = id;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

}
