package com.dddd.doctorpatientrest.crud.services;

import com.dddd.doctorpatientrest.general.constants.Constants;
import com.dddd.doctorpatientrest.general.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.general.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.general.services.service_impls.DrugServiceImpl;
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
class DrugWithH2Test {

	private final DrugServiceImpl drugService;

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public DrugWithH2Test(DrugServiceImpl drugService,
						  JdbcTemplate jdbcTemplate) {
		this.drugService = drugService;
		this.jdbcTemplate = jdbcTemplate;
	}

	@AfterEach
	public void deleteAll() {
		JdbcTestUtils.deleteFromTables(jdbcTemplate,  "patients_drugs", "patients", "full_info", "doctors", "drugs" );
	}

	@Test
	void drugsGetAll() {
		List<DrugDto> expectedDrugDtoList = getDrugDtoList();

		List<DrugDto> actualDrugDtoList = drugService.findAll();

		assertEquals(expectedDrugDtoList, actualDrugDtoList);
	}

	@Test
	void drugsPost() {
		long id = 4L;
		DrugDto expectedDrugDto = new DrugDto(id, "drug" + id);

		DrugDto actualDrugDto = drugService.create(expectedDrugDto);

		assertEquals(expectedDrugDto, actualDrugDto);
	}

	@Test
	void drugsPostShouldThrowAlreadyExistsException() {
		long id = 2L;
		DrugDto expectedDrugDto = getDrugDto(id);

		Exception exception = assertThrows(ResourceAlreadyExistsException.class,
				() -> drugService.create(expectedDrugDto));

		assertEquals(Constants.DRUG_ALREADY_EXISTS + id, exception.getMessage());
	}

	@Test
	void drugsGetOne() {
		long id = 2L;
		DrugDto expectedDrugDto = getDrugDto(id);

		DrugDto actualDrugDto = drugService.findById(id);

		assertEquals(expectedDrugDto, actualDrugDto);
	}

	@Test
	void drugsGetOneShouldThrowNotFoundException() {
		long id = 666L;

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> drugService.findById(id));

		assertEquals(Constants.DRUG_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void drugsPut() {
		long id = 2L;
		DrugDto expectedDrugDto = getDrugDto(id);

		DrugDto actualDrugDto = drugService.update(expectedDrugDto);

		assertEquals(expectedDrugDto, actualDrugDto);
	}

	@Test
	void drugsPutShouldThrowNotFoundException() {
		long id = 44L;
		DrugDto expectedDrugDto = getDrugDto(id);

		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> drugService.update(expectedDrugDto));

		assertEquals(Constants.DRUG_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void drugsDelete() {
		long id = 3L;
		drugService.deleteById(id);

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> drugService.findById(id));

		assertEquals(Constants.DRUG_NOT_FOUND + id, exception.getMessage());
	}

	public List<DrugDto> getDrugDtoList() {
		List<DrugDto> drugDtoList = new ArrayList<>();
		for (long i = 1; i < 4; i++) {
			drugDtoList.add(getDrugDto(i));
		}
		return drugDtoList;
	}

	public DrugDto getDrugDto(long i) {
		return new DrugDto(i, "drug" + i);
	}
}
