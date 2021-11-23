package com.dddd.doctorpatientrest.crud.controllers;

import com.dddd.doctorpatientrest.application.services.service_impls.FullInfoServiceImpl;
import com.dddd.doctorpatientrest.web.contollers.FullInfoController;
import com.dddd.doctorpatientrest.web.mapstruct.dto.FullInfoDto;
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

@WebMvcTest(FullInfoController.class)
class FullInfoControllerTest {

	private final MockMvc mockMvc;

	@MockBean
	private FullInfoServiceImpl fullInfoService;

	@Autowired
	public FullInfoControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	void doctorsControllerGetAll() throws Exception {
		FullInfoDto fullInfoDto1 = new FullInfoDto(1L,"","","");
		FullInfoDto fullInfoDto2 = new FullInfoDto(2L,"","","");
		List<FullInfoDto> fullInfoDtoList = new ArrayList<>();
		fullInfoDtoList.add(fullInfoDto1);
		fullInfoDtoList.add(fullInfoDto2);
		when(fullInfoService.findAll()).thenReturn(fullInfoDtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/fullInfo/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(fullInfoDtoList.size()));

	}

	@Test
	void doctorsControllerGetOne() throws Exception {
		long id = 1L;
		FullInfoDto fullInfoDto = new FullInfoDto(id,"","TT","");
		when(fullInfoService.findById(id)).thenReturn(fullInfoDto);

		mockMvc.perform(MockMvcRequestBuilders.get("/fullInfo/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email").value(fullInfoDto.getEmail()));
	}

	@Test
	void doctorControllerPost() throws Exception{
		long id = 1L;
		FullInfoDto fullInfoDto = new FullInfoDto(id,"","TT","");
		Gson gson = new Gson();
		when(fullInfoService.create(fullInfoDto)).thenReturn(fullInfoDto);

		mockMvc.perform(post("/fullInfo/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(gson.toJson(fullInfoDto)))
				.andExpect(jsonPath("$.email").value(fullInfoDto.getEmail()));
	}

	@Test
	void doctorControllerDelete() throws Exception {
		long id = 1L;
		FullInfoDto fullInfoDto = new FullInfoDto(id,"","TT","");
		when(fullInfoService.findById(id)).thenReturn(fullInfoDto);

		mockMvc.perform(delete("/fullInfo/{id}", id))
				.andExpect(status().isOk());
	}
}
