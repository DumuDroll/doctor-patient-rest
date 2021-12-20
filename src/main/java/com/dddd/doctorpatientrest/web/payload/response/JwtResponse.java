package com.dddd.doctorpatientrest.web.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private List<String> roles;

	public JwtResponse(String token, Long id, String username, List<String> roles) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.roles = roles;
	}
}
