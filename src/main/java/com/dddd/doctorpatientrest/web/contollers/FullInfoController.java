package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.services.service_impls.FullInfoServiceImpl;
import com.dddd.doctorpatientrest.web.mapstruct.dto.FullInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/fullInfo/")
public class FullInfoController {

	private final FullInfoServiceImpl fullInfoService;

	public FullInfoController(FullInfoServiceImpl fullInfoService) {
		this.fullInfoService = fullInfoService;
	}

	@GetMapping
	public ResponseEntity<List<FullInfoDto>> all() {
		return new ResponseEntity<>(fullInfoService.findAll(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<FullInfoDto> updateFullInfo(@RequestBody FullInfoDto fullInfoDto) {
		return new ResponseEntity<>(fullInfoService.update(fullInfoDto), HttpStatus.OK);
	}

	@GetMapping(Constants.FULL_INFO_ID)
	public ResponseEntity<FullInfoDto> getById(@PathVariable long fullInfoID) {
		return new ResponseEntity<>(fullInfoService.findById(fullInfoID), HttpStatus.OK);
	}

}
