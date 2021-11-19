package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.services.service_impls.FullInfoServiceImpl;
import com.dddd.doctorpatientrest.web.mapstruct.dto.FullInfoDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fullInfo/")
public class FullInfoController {

	private final FullInfoServiceImpl fullInfoService;

	public FullInfoController(FullInfoServiceImpl fullInfoService) {
		this.fullInfoService = fullInfoService;
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403") })
	@GetMapping
	public ResponseEntity<List<FullInfoDto>> all() {
		return new ResponseEntity<>(fullInfoService.findAll(), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404"),
			@ApiResponse(responseCode = "409") })
	@PostMapping
	public ResponseEntity<FullInfoDto> createFullInfo(@RequestBody FullInfoDto fullInfoDto) {
		return new ResponseEntity<>(fullInfoService.create(fullInfoDto), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404") })
	@PutMapping
	public ResponseEntity<FullInfoDto> updateFullInfo(@RequestBody FullInfoDto fullInfoDto) {
		return new ResponseEntity<>(fullInfoService.update(fullInfoDto), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404") })
	@GetMapping(Constants.FULL_INFO_ID)
	public ResponseEntity<FullInfoDto> getById(@PathVariable long fullInfoID) {
		return new ResponseEntity<>(fullInfoService.findById(fullInfoID), HttpStatus.OK);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "401"),
			@ApiResponse(responseCode = "403"),
			@ApiResponse(responseCode = "404") })
	@DeleteMapping(Constants.FULL_INFO_ID)
	public ResponseEntity<Object> deleteFullInfo(@PathVariable long fullInfoID) {
		fullInfoService.deleteById(fullInfoService.findById(fullInfoID).getId());
		return ResponseEntity.ok().build();
	}

}
