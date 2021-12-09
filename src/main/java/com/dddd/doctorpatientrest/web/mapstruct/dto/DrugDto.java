package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DrugDto {

	@ToString.Exclude
	private int id;

	private String name;

}
