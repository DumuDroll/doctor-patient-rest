package com.dddd.doctorpatientrest.crud;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.service_impls.DoctorServiceImpl;
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
class DoctorWithH2Test {

	private final DoctorServiceImpl doctorService;

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	DoctorWithH2Test(DoctorServiceImpl doctorService,
					 JdbcTemplate jdbcTemplate) {
		this.doctorService = doctorService;
		this.jdbcTemplate = jdbcTemplate;
	}


	@AfterEach
	public void deleteAll() {
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "full_info", "patients_drugs", "patients", "doctors", "drugs" );
	}

	@Test
	void testDoctorsGetAll() {
		List<DoctorDto> expectedDoctorDto = getDoctorDtoList();

		List<DoctorDto> actualDoctorDto = doctorService.findAll();

		assertEquals(expectedDoctorDto, actualDoctorDto);
	}

	@Test
	void testDoctorsGetOne() {
		long id = 1L;
		DoctorDto expectedDoctorDto = getDoctorDto(id);

		DoctorDto actualDoctorDto = doctorService.findById(id);

		assertEquals(expectedDoctorDto, actualDoctorDto);
	}

	@Test
	void testDoctorGetShouldThrowNotFoundException() {
		long id = 666L;

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> doctorService.findById(id));

		assertEquals(Constants.DOCTOR_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void testDoctorsPost() {
		long id = 4L;
		DoctorDto expectedDoctorDto = getDoctorDto(id);

		DoctorDto actualDoctorDto = doctorService.create(expectedDoctorDto);

		assertEquals(actualDoctorDto, expectedDoctorDto);
	}

	@Test
	void testDoctorsPostShouldThrowAlreadyExistsException() {
		long id = 1L;
		DoctorDto expectedDoctorDto = getDoctorDto(id);

		Exception exception = assertThrows(ResourceAlreadyExistsException.class,
				() -> doctorService.create(expectedDoctorDto));

		assertEquals(Constants.DOCTOR_ALREADY_EXISTS + id, exception.getMessage());
	}

	@Test
	void testDoctorsPut() {
		long id = 2L;
		DoctorDto expectedDoctorDto = getDoctorDto(id);
		DoctorDto actualDoctorDto = doctorService.update(expectedDoctorDto);
		assertEquals(actualDoctorDto, expectedDoctorDto);
	}

	@Test
	void testDoctorsPutShouldThrowNotFoundException() {
		long id = 123123L;
		DoctorDto expectedDoctorDto = getDoctorDto(id);

		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> doctorService.update(expectedDoctorDto));

		assertEquals(Constants.DOCTOR_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void testDoctorsDelete() {
		long id = 1L;
		doctorService.deleteById(id);

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> doctorService.findById(id));

		assertEquals(Constants.DOCTOR_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void testDoctorsDeleteShouldThrowNotFoundException() {
		long id = 333L;

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> doctorService.deleteById(id));

		assertEquals(Constants.DOCTOR_NOT_FOUND + id, exception.getMessage());
	}

	public List<DoctorDto> getDoctorDtoList() {
		List<DoctorDto> doctorDtoList = new ArrayList<>();
		for (long i = 1; i < 4; i++) {
			DoctorDto doctorDto = getDoctorDto(i);
			doctorDtoList.add(doctorDto);
		}
		return doctorDtoList;
	}

	public DoctorDto getDoctorDto(long i) {
		return new DoctorDto(i, "testName" + i, "testExp" + i);
	}

}
