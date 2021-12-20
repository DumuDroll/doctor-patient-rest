package com.dddd.doctorpatientrest.web.contollers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin
public class UserController {

	@GetMapping(path = "/login")
	public AuthenticationBean basicAuth() {
		return new AuthenticationBean("You are authenticated");
	}

//	@PostMapping("/login")
//	public ResponseEntity<UserDto> login(@RequestBody UserDto user) {
//		if(user.getUsername().equals("user") && user.getPassword().equals("password")){
//			return new ResponseEntity<>(user, HttpStatus.OK);
//		}
//		return new ResponseEntity<>(user, HttpStatus.FORBIDDEN);
//	}

	@RequestMapping("/user")
	public Principal user(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization")
				.substring("Basic".length()).trim();
		return () ->  new String(Base64.getDecoder()
				.decode(authToken)).split(":")[0];
	}
}
