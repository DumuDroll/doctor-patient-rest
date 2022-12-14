package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.general.services.UserService;
import com.dddd.doctorpatientrest.web.mapstruct.dto.IconDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.IconMessageDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> all() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

	@GetMapping("filtered/")
	public ResponseEntity<Map<String, Object>> allFiltered(@RequestParam(defaultValue = "") String name,
														   @RequestParam(defaultValue = "0") int page,
														   @RequestParam(defaultValue = "5") int size,
														   @RequestParam(defaultValue = "true") boolean blocked) {
		return userService.findAllFiltered(blocked, name, page, size);
	}

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.create(userDto), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.update(userDto), HttpStatus.OK);
	}

	@GetMapping(Constants.USER_ID)
	public ResponseEntity<UserDto> getById(@PathVariable long userId) {
		return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
	}

	@DeleteMapping(Constants.DRUG_ID)
	public ResponseEntity<Object> deleteDrug(@PathVariable long drugId) {
		userService.deleteById(drugId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/icon")
	public ResponseEntity<IconDto> showIcon(@RequestParam("id") long userId) {
		return new ResponseEntity<>(userService.showIcon(userId), HttpStatus.OK);
	}

	@PostMapping("/icon")
	public ResponseEntity<IconMessageDto> saveIcon(@RequestParam("file") MultipartFile file,
												   @RequestParam("email") String email) {
		String message = "";
		try {
			userService.saveIcon(file, email);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new IconMessageDto(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new IconMessageDto(message));
		}
	}
}
