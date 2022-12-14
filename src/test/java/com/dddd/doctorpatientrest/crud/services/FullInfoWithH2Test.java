package com.dddd.doctorpatientrest.crud.services;

import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.general.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.general.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.general.services.FullInfoService;
import com.dddd.doctorpatientrest.web.mapstruct.dto.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data/inserts.sql")
class FullInfoWithH2Test {

	private final FullInfoService fullInfoService;

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public FullInfoWithH2Test(FullInfoService fullInfoService,
							  JdbcTemplate jdbcTemplate) {
		this.fullInfoService = fullInfoService;
		this.jdbcTemplate = jdbcTemplate;
	}

	@AfterEach
	public void deleteAll() {
		JdbcTestUtils.deleteFromTables(jdbcTemplate,  "patients_drugs", "patients", "full_info", "doctors", "drugs" );
	}

	@Test
	void fullInfoGetAll() {
		List<FullInfoDto> expectedFullInfoDtoList = getFullInfoDtoList();

		List<FullInfoDto> actualFullInfoDtoList = fullInfoService.findAll();

		assertEquals(expectedFullInfoDtoList, actualFullInfoDtoList);
	}

	@Test
	void fullInfosGetOne() {
		long id = 1L;
		FullInfoDto expectedFullInfoDto = getFullInfoDto(id);

		FullInfoDto actualFullInfoDto = fullInfoService.findById(id);

		assertEquals(expectedFullInfoDto, actualFullInfoDto);
	}

	@Test
	void fullInfoGetOneShouldThrowNotFoundException() {
		long id = 5L;

		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> fullInfoService.findById(id));

		assertEquals(Constants.FULL_INFO_NOT_FOUND + id, exception.getMessage());
	}


	@Test
	void fullInfosPost() {
		long id = 4L;
		FullInfoDto expectedFullInfoDto = getFullInfoDto(id);

		FullInfoDto actualFullInfoDto = fullInfoService.create(expectedFullInfoDto);

		assertEquals(expectedFullInfoDto.getEmail(), actualFullInfoDto.getEmail());
	}

	@Test
	void fullInfosPostShouldThrowAlreadyExistsException() {
		long id = 2L;
		FullInfoDto expectedFullInfoDto = getFullInfoDto(id);

		Exception exception = assertThrows(ResourceAlreadyExistsException.class,
				() -> fullInfoService.create(expectedFullInfoDto));

		assertEquals(Constants.FULL_INFO_ALREADY_EXISTS + id, exception.getMessage());
	}

	@Test
	void fullInfosPut() {
		long id = 2L;
		FullInfoDto expectedFullInfoDto = getFullInfoDto(id);

		FullInfoDto actualFullInfoDto = fullInfoService.update(expectedFullInfoDto);

		assertEquals(expectedFullInfoDto, actualFullInfoDto);
	}

	@Test
	void fullInfosPutShouldThrowNotFoundException() {
		long id = 44L;
		FullInfoDto expectedFullInfoDto = getFullInfoDto(id);

		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> fullInfoService.update(expectedFullInfoDto));

		assertEquals(Constants.FULL_INFO_NOT_FOUND + id, exception.getMessage());
	}

	public List<FullInfoDto> getFullInfoDtoList() {
		List<FullInfoDto> fullInfoDtoList = new ArrayList<>();
		for (long i = 1; i < 4; i++) {
			FullInfoDto fullInfoDto = getFullInfoDto(i);
			fullInfoDtoList.add(fullInfoDto);
		}
		return fullInfoDtoList;
	}

	public FullInfoDto getFullInfoDto(long i) {
		return new FullInfoDto(i, null, "testEmail" + i, "testNumber" + i);
	}

}
