package com.dddd.doctorpatientrest.web.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrugDto {

	@JsonProperty("id")
	private long id;

	@JsonProperty("name")
	private String name;

}
