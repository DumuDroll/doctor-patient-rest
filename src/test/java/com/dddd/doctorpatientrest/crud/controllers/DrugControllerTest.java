package com.dddd.doctorpatientrest.crud.controllers;

import com.dddd.doctorpatientrest.general.services.DrugService;
import com.dddd.doctorpatientrest.web.contollers.DrugController;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
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

@WebMvcTest(DrugController.class)
class DrugControllerTest {

	private final MockMvc mockMvc;

	@MockBean
	private DrugService drugService;

	@Autowired
	public DrugControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	void doctorsControllerGetAll() throws Exception {
		DrugDto drugDto1 = new DrugDto(1L, "");
		DrugDto drugDto2 = new DrugDto(2L, "");
		List<DrugDto> drugDtoList = new ArrayList<>();
		drugDtoList.add(drugDto1);
		drugDtoList.add(drugDto2);
		when(drugService.findAll()).thenReturn(drugDtoList);

		mockMvc.perform(MockMvcRequestBuilders.get("/drugs/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(drugDtoList.size()));

	}

	@Test
	void doctorsControllerGetOne() throws Exception {
		long id = 1L;
		DrugDto drugDto = new DrugDto(id, "");
		when(drugService.findById(id)).thenReturn(drugDto);

		mockMvc.perform(MockMvcRequestBuilders.get("/drugs/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(drugDto.getName()));
	}

	@Test
	void doctorControllerPost() throws Exception{
		long id = 1L;
		DrugDto drugDto = new DrugDto(id, "");
		Gson gson = new Gson();
		when(drugService.create(drugDto)).thenReturn(drugDto);

		mockMvc.perform(post("/drugs/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(gson.toJson(drugDto)))
				.andExpect(jsonPath("$.name").value(drugDto.getName()));
	}

	@Test
	void doctorControllerDelete() throws Exception {
		long id = 1L;
		DrugDto drugDto = new DrugDto(id, "");
		when(drugService.findById(id)).thenReturn(drugDto);

		mockMvc.perform(delete("/drugs/{id}", id))
				.andExpect(status().isOk());
	}
}
