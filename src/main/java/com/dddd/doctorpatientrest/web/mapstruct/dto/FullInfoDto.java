package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FullInfoDto {

	private long id;

	private String birthDate;

	private String email;

	private String phoneNumber;

}
