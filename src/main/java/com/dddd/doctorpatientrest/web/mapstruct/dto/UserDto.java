package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private long id;

	private String username;

	private String password;

	private String status;

	private Set<String> roles;

	private String jwt;

}
