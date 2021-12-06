package com.dddd.doctorpatientrest.web.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

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
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate birthDate;

	private String email;

	@ToString.Exclude
	private String phoneNumber;

}
