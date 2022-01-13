package com.dddd.doctorpatientrest.web.mapstruct.dto.rabbit_dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRabbitDto {

	private long id;

	private UUID uuid;

	private String iconName;

	private byte[] icon;

}
