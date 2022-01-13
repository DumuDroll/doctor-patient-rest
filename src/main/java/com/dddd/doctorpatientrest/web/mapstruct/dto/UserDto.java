package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private long id;

	@ToString.Exclude
	private UUID uuid;

	private String username;

	private String password;

	private String status;

	private Set<String> roles;

	private String jwt;

}
