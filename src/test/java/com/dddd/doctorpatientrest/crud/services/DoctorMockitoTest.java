//package com.dddd.doctorpatientrest.crud.services;
//
//import com.dddd.doctorpatientrest.application.constants.Constants;
//import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
//import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
//import com.dddd.doctorpatientrest.application.services.service_impls.DoctorServiceImpl;
//import com.dddd.doctorpatientrest.database.entities.Doctor;
//import com.dddd.doctorpatientrest.database.repositories.DoctorRepository;
//import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
//import com.dddd.doctorpatientrest.web.mapstruct.mappers.DoctorMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class DoctorMockitoTest {
//
//	@Mock
//	private DoctorRepository doctorRepository;
//
//	@Mock
//	private DoctorMapper doctorMapper;
//
//	@InjectMocks
//	private DoctorServiceImpl doctorService;
//
//	@Test
//	void doctorsGetAll() {
//		Doctor doctor1 = new Doctor(1L, "", "", null);
//		Doctor doctor2 = new Doctor(2L, "", "", null);
//		List<Doctor> doctors = new ArrayList<>();
//		doctors.add(doctor1);
//		doctors.add(doctor2);
//		DoctorDto doctorDto1 = new DoctorDto(1L, "", "");
//		DoctorDto doctorDto2 = new DoctorDto(2L, "", "");
//		List<DoctorDto> expectedDoctorDtoList = new ArrayList<>();
//		expectedDoctorDtoList.add(doctorDto1);
//		expectedDoctorDtoList.add(doctorDto2);
//		Mockito.when(doctorRepository.findAll()).thenReturn(doctors);
//		Mockito.when(doctorMapper.doctorListToDoctorDtoList(doctors)).thenReturn(expectedDoctorDtoList);
//
//		List<DoctorDto> actualDoctorDtoList = doctorService.findAll(null, null);
//
//		assertEquals(expectedDoctorDtoList, actualDoctorDtoList);
//	}
//
//	@Test
//	void doctorsPost() {
//		Doctor doctor = new Doctor(1L, "Dmytro", "5 years", null);
//		DoctorDto doctorDto = new DoctorDto(1L, "Dmytro", "5 years");
//		when(doctorRepository.findById(doctor.getId())).thenReturn(Optional.empty());
//		when(doctorMapper.doctorToDoctorDto(doctor)).thenReturn(doctorDto);
//		when(doctorMapper.doctorDtoToDoctor(doctorDto)).thenReturn(doctor);
//		when(doctorRepository.save(doctor)).thenAnswer(invocation -> invocation.getArguments()[0]);
//
//		DoctorDto savedDoctor = doctorService.create(doctorDto);
//
//		assertThat(savedDoctor).isNotNull();
//		verify(doctorRepository).save(any(Doctor.class));
//	}
//
//	@Test
//	void doctorsPostShouldThrowAlreadyExistsException() {
//		final long id = 1L;
//		Doctor doctor = new Doctor(id, "Dmytro", "5 years", null);
//		DoctorDto doctorDto = new DoctorDto(id, "Dmytro", "5 years");
//		when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));
//
//		Exception exception = assertThrows(ResourceAlreadyExistsException.class, () -> doctorService.create(doctorDto));
//
//		assertEquals(Constants.DOCTOR_ALREADY_EXISTS + id, exception.getMessage());
//	}
//
//	@Test
//	void doctorsGetOne() {
//		final long id = 1L;
//		final Doctor doctor = new Doctor(id, "Dmytro", "5 years", null);
//		DoctorDto doctorDto = new DoctorDto(id, "Dmytro", "5 years");
//		when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));
//		when(doctorMapper.doctorToDoctorDto(doctor)).thenReturn(doctorDto);
//
//		DoctorDto actualDoctorDto = doctorService.findById(id);
//
//		assertEquals(doctorDto, actualDoctorDto);
//	}
//
//	@Test
//	void doctorGetOneShouldThrowNotFoundException() {
//		final long id = 1L;
//		when(doctorRepository.findById(id)).thenReturn(Optional.empty());
//
//		Exception exception = assertThrows(ResourceNotFoundException.class, () -> doctorService.findById(id));
//
//		assertEquals(Constants.DOCTOR_NOT_FOUND + id, exception.getMessage());
//	}
//
//	@Test
//	void testDoctorsPut() {
//		final long id = 1L;
//		Doctor oldDoctor = new Doctor(id, "Dmytro", "11 years", null);
//		Doctor newDoctor = new Doctor(id, "Dmytro", "5 years", null);
//		DoctorDto doctorDto = new DoctorDto(id, "Dmytro", "5 years");
//		when(doctorRepository.findById(id)).thenReturn(Optional.of(oldDoctor));
//		when(doctorMapper.doctorToDoctorDto(newDoctor)).thenReturn(doctorDto);
//		when(doctorMapper.doctorDtoToDoctor(doctorDto)).thenReturn(newDoctor);
//		when(doctorRepository.save(newDoctor)).thenAnswer(invocation -> invocation.getArguments()[0]);
//
//		DoctorDto actualDoctorDto = doctorService.update(doctorDto);
//
//		assertThat(actualDoctorDto).isNotNull();
//
//		verify(doctorRepository).save(any(Doctor.class));
//	}
//
//	@Test
//	void testDoctorsPutShouldThrowNotFoundException() {
//		final long id = 1L;
//		DoctorDto doctorDto = new DoctorDto(id, "Dmytro", "5 years");
//		when(doctorRepository.findById(id)).thenReturn(Optional.empty());
//
//		Exception exception = assertThrows(ResourceNotFoundException.class,
//				() -> doctorService.update(doctorDto));
//
//		assertEquals(Constants.DOCTOR_NOT_FOUND + id, exception.getMessage());
//	}
//
//	@Test
//	void testDoctorsDelete() {
//		long id = 1L;
//
//		doctorService.deleteById(id);
//
//		verify(doctorRepository, times(1)).deleteById(id);
//	}
//
//}