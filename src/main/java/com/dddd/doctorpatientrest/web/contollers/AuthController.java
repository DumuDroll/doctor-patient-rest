package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.general.services.UserService;
import com.dddd.doctorpatientrest.web.mapstruct.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	private final UserService userService;

	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signin")
	public ResponseEntity<UserDto> authenticateUser(@RequestBody UserDto userDto) {
		return ResponseEntity.ok(userService.login(userDto));
	}

	@PostMapping("/setPassword")
	public ResponseEntity<UserDto> setPassword(@RequestBody UserDto userDto) {
		return ResponseEntity.ok(userService.firstLogin(userDto));
	}

	@PostMapping("/signup")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.create(userDto), HttpStatus.OK);
	}
}
