package com.dddd.doctorpatientrest.crud.controllers;

import com.dddd.doctorpatientrest.application.services.service_impls.DoctorServiceImpl;
import com.dddd.doctorpatientrest.web.contollers.DoctorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {

	private final MockMvc mockMvc;

	@MockBean
	private DoctorServiceImpl doctorService;

	@Autowired
	public DoctorControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

//	@Test
//	void doctorsControllerGetAll() throws Exception {
//		DoctorDto doctorDto1 = new DoctorDto(1L, "", "");
//		DoctorDto doctorDto2 = new DoctorDto(2L, "", "");
//		List<DoctorDto> expectedDoctorDtoList = new ArrayList<>();
//		expectedDoctorDtoList.add(doctorDto1);
//		expectedDoctorDtoList.add(doctorDto2);
//		when(doctorService.findAll()).thenReturn(expectedDoctorDtoList);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/doctors/"))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.size()").value(expectedDoctorDtoList.size()));
//
//	}
//
//	@Test
//	void doctorsControllerGetOne() throws Exception {
//		long id = 1L;
//		DoctorDto expectedDoctorDto = new DoctorDto(id, "t", "");
//		when(doctorService.findById(id)).thenReturn(expectedDoctorDto);
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/doctors/{id}", id))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.name").value(expectedDoctorDto.getName()));
//	}
//
//	@Test
//	void doctorControllerPost() throws Exception{
//		long id = 1L;
//		DoctorDto expectedDoctorDto = new DoctorDto(id, "t", "");
//		Gson gson = new Gson();
//		when(doctorService.create(expectedDoctorDto)).thenReturn(expectedDoctorDto);
//
//		mockMvc.perform(post("/doctors/")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(gson.toJson(expectedDoctorDto)))
//				.andExpect(jsonPath("$.name").value(expectedDoctorDto.getName()))
//				.andExpect(jsonPath("$.experience").value(expectedDoctorDto.getExperience()));
//	}
//
//	@Test
//	void doctorControllerDelete() throws Exception {
//		long id = 1L;
//		DoctorDto expectedDoctorDto = new DoctorDto(id, "t", "");
//		when(doctorService.findById(id)).thenReturn(expectedDoctorDto);
//
//		mockMvc.perform(delete("/doctors/{id}", id))
//				.andExpect(status().isOk());
//	}
}
