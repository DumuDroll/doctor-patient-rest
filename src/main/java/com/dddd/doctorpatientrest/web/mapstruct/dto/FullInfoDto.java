package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

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
	private String birthDate;

	private String email;

	@ToString.Exclude
	private String phoneNumber;

}
