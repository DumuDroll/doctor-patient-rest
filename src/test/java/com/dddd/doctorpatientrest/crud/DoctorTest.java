package com.dddd.doctorpatientrest.crud;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.service_impls.DoctorServiceImpl;
import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.database.repositories.DoctorRepository;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.DoctorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DoctorTest {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private DoctorServiceImpl doctorService;

	@Autowired
	private DoctorMapper doctorMapper;


	@Test
	void testDoctorsGetOne() {
		DoctorDto doctorDto = doctorService.findById(1L);
		Doctor doctor = new Doctor();
		doctor.setId(1L);
		doctor.setName("Dmytro");
		doctor.setExperience("5 years");
		assertEquals(doctor, doctorMapper.doctorDtoToDoctor(doctorDto));

	}

	@Test
	void testDoctorGetShouldThrowNotFoundException() {
		long id = 123123L;
		Exception exception = assertThrows(ResourceNotFoundException.class, () -> doctorService.findById(id));
		assertEquals(Constants.DOCTOR_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void testDoctorsGetAll() {

		Doctor doctor1 = new Doctor(1L, "Dmytro", "5 years", null);
		Doctor doctor2 = new Doctor(2L, "Petro", "15 years", null);
		Doctor doctor3 = new Doctor(3L, "Pavlo", "3 years", null);

		List<Doctor> doctors = new ArrayList<>();
		doctors.add(doctor1);
		doctors.add(doctor2);
		doctors.add(doctor3);

		List<DoctorDto> expectedDoctorDtoList = doctorMapper.doctorListToDoctorDtoList(doctors);

		List<DoctorDto> actualDoctorDtoList = doctorService.findAll();
		if (expectedDoctorDtoList.size() == actualDoctorDtoList.size()) {
			for (int i = 0; i < expectedDoctorDtoList.size(); i++) {
				assertEquals(expectedDoctorDtoList.get(i), actualDoctorDtoList.get(i));
			}
		}
	}

	@Test
	void testDoctorsPost() {
		DoctorDto doctorDto = new DoctorDto(4L, "Viktor", "0 years");
		doctorService.create(doctorDto);
		assertEquals(doctorService.findById(4L), doctorDto);
	}

	@Test
	void testDoctorsPostShouldThrowAlreadyExistsException() {
		long id = 1L;
		DoctorDto doctorDto = new DoctorDto(1L, "Viktor", "0 years");
		Exception exception = assertThrows(ResourceAlreadyExistsException.class, () -> doctorService.create(doctorDto));
		assertEquals(Constants.DOCTOR_ALREADY_EXISTS + id, exception.getMessage());
	}

	@Test
	void testDoctorsPut() {
		DoctorDto doctorDto = new DoctorDto(4L, "Viktor", "0 years");
		doctorService.update(doctorDto);
		assertEquals(doctorService.findById(4L), doctorDto);
	}

	@Test
	void testDoctorsPutShouldThrowNotFoundException() {
		long id = 123123L;
		DoctorDto doctorDto = new DoctorDto(id, "Viktor", "0 years");
		Exception exception = assertThrows(ResourceNotFoundException.class, () -> doctorService.update(doctorDto));
		assertEquals(Constants.DOCTOR_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void testDoctorsDelete() {
		long id = 3L;
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

}
