package com.dddd.doctorpatientrest.web.contollers;

import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.general.services.FullInfoService;
import com.dddd.doctorpatientrest.web.mapstruct.dto.FullInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/fullInfo/")
public class FullInfoController {

	private final FullInfoService fullInfoService;

	public FullInfoController(FullInfoService fullInfoService) {
		this.fullInfoService = fullInfoService;
	}

	@GetMapping
	public ResponseEntity<List<FullInfoDto>> all() {
		return new ResponseEntity<>(fullInfoService.findAll(), HttpStatus.OK);
	}

	@GetMapping("filtered/")
	public ResponseEntity<Map<String, Object>> allFiltered(@RequestParam(defaultValue = "") String name,
														   @RequestParam(defaultValue = "0") int page,
														   @RequestParam(defaultValue = "5") int size) {
		return fullInfoService.findAllFiltered(name, page, size);
	}

	@PutMapping
	public ResponseEntity<FullInfoDto> updateFullInfo(@RequestBody FullInfoDto fullInfoDto) {
		return new ResponseEntity<>(fullInfoService.update(fullInfoDto), HttpStatus.OK);
	}

	@GetMapping(Constants.FULL_INFO_ID)
	public ResponseEntity<Object> getById(@PathVariable long fullInfoID) {
		fullInfoService.findById(fullInfoID);
		return ResponseEntity.ok().build();
	}

}
