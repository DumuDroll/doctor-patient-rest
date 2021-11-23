package com.dddd.doctorpatientrest.crud.controllers;

import com.dddd.doctorpatientrest.web.contollers.DoctorController;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class DoctorTest {

	private final MockMvc mockMvc;

	@MockBean
	private DoctorController doctorController;

	@Autowired
	public DoctorTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	void doctorsControllerGetAll() throws Exception {
		DoctorDto doctorDto1 = new DoctorDto(1L, "", "");
		DoctorDto doctorDto2 = new DoctorDto(2L, "", "");
		List<DoctorDto> expectedDoctorDtoList = new ArrayList<>();
		expectedDoctorDtoList.add(doctorDto1);
		expectedDoctorDtoList.add(doctorDto2);
		when(doctorController.all()).thenReturn(new ResponseEntity<>(expectedDoctorDtoList, HttpStatus.OK));

	}

	@Test
	void doctorsControllerGetOne() {
		long id = 1L;
		DoctorDto expectedDoctorDto = new DoctorDto(id, "t", "");
		when(doctorController.getById(id)).thenReturn(new ResponseEntity<>(expectedDoctorDto, HttpStatus.OK));

		ResponseEntity<DoctorDto> actualDoctorDto = doctorController.getById(id);

		assertEquals(expectedDoctorDto, actualDoctorDto.getBody());
	}
}
