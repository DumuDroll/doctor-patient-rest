package com.dddd.doctorpatientrest.crud.controllers;

import com.dddd.doctorpatientrest.application.services.service_impls.PatientServiceImpl;
import com.dddd.doctorpatientrest.web.contollers.PatientController;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

	private final MockMvc mockMvc;

	@MockBean
	private PatientServiceImpl patientService;

	@Autowired
	public PatientControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	void doctorsControllerGetAll() throws Exception {
		PatientDto patientDto1 = new PatientDto(1L, "A", "", null, null, null, "", "", null);
		PatientDto patientDto2 = new PatientDto(2L, "B", "", null, null, null, "", "", null);
		List<PatientDto> patientDtoList = new ArrayList<>();
		patientDtoList.add(patientDto1);
		patientDtoList.add(patientDto2);
		when(patientService.findAll()).thenReturn(patientDtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/patients/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(patientDtoList.size()));

	}

	@Test
	void doctorsControllerGetOne() throws Exception {
		long id = 1L;
		PatientDto patientDto = new PatientDto(id, "A", "", null, null, null, "", "", null);
		when(patientService.findById(id)).thenReturn(patientDto);

		mockMvc.perform(MockMvcRequestBuilders.get("/patients/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value(patientDto.getFirstName()));
	}

	@Test
	void doctorControllerPost() throws Exception {
		long id = 1L;
		PatientDto patientDto = new PatientDto(id, "A", "", null, null, null, "", "", null);
		Gson gson = new Gson();
		when(patientService.create(patientDto)).thenReturn(patientDto);

		mockMvc.perform(post("/patients/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(gson.toJson(patientDto)))
				.andExpect(jsonPath("$.firstName").value(patientDto.getFirstName()));
	}

	@Test
	void doctorControllerDelete() throws Exception {
		long id = 1L;
		PatientDto patientDto = new PatientDto(id, "A", "", null, null, null, "", "", null);
		when(patientService.findById(id)).thenReturn(patientDto);

		mockMvc.perform(delete("/patients/{id}", id))
				.andExpect(status().isOk());
	}
}
