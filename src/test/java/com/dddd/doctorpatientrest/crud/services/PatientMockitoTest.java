package com.dddd.doctorpatientrest.crud.services;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.service_impls.PatientServiceImpl;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.database.repositories.PatientRepository;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.PatientMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientMockitoTest {

	@Mock
	private PatientRepository patientRepository;

	@Mock
	private PatientMapper patientMapper;

	@InjectMocks
	private PatientServiceImpl patientService;

//	@Test
//	void patientsGetAll() {
//		Patient patient1 = new Patient(1L, "", "1", null, null, null);
//		Patient patient2 = new Patient(2L, "2", "", null, null, null);
//		List<Patient> patients = new ArrayList<>();
//		patients.add(patient1);
//		patients.add(patient2);
//		PatientDto patientDto1 = new PatientDto(1L, "", "1", null, null, null);
//		PatientDto patientDto2 = new PatientDto(2L, "2", "", null, null, null);
//		List<PatientDto> expectedPatientDtoList = new ArrayList<>();
//		expectedPatientDtoList.add(patientDto1);
//		expectedPatientDtoList.add(patientDto2);
//		Mockito.when(patientRepository.findAll()).thenReturn(patients);
//		Mockito.when(patientMapper.patientListToPatientDtoList(patients)).thenReturn(expectedPatientDtoList);
//
//		List<PatientDto> actualPatientDtoList = patientService.findAll();
//
//		assertEquals(expectedPatientDtoList, actualPatientDtoList);
//	}
//
//	@Test
//	void patientsGetAllShouldThrowNotFoundException() {
//		Mockito.when(patientRepository.findAll()).thenReturn(new ArrayList<>());
//
//		Exception exception = assertThrows(ResourceNotFoundException.class, () -> patientService.findAll());
//
//		assertEquals(Constants.NO_DATA_IN_DB + 404, exception.getMessage());
//	}
//
//	@Test
//	void patientsPost() {
//		long id = 1L;
//		Patient patient = new Patient(id, "", "", null,
//				null, null);
//		PatientDto expectedPatientDto = new PatientDto(id, "", "", null,
//				null, null);
//		when(patientRepository.findById(patient.getId())).thenReturn(Optional.empty());
//		when(patientMapper.patientToPatientDto(patient)).thenReturn(expectedPatientDto);
//		when(patientMapper.patientDtoToPatient(expectedPatientDto)).thenReturn(patient);
//		when(patientRepository.save(patient)).thenAnswer(invocation -> invocation.getArguments()[0]);
//
//		PatientDto actualPatientDto = patientService.create(expectedPatientDto);
//
//		assertEquals(expectedPatientDto, actualPatientDto);
//	}
//
//	@Test
//	void patientsPostShouldThrowAlreadyExistsException() {
//		long id = 1L;
//		Patient patient = new Patient(id, "", "", null,
//				null, null);
//		PatientDto expectedPatientDto = new PatientDto(id, "", "", null,
//				null, null);
//		when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
//
//		Exception exception = assertThrows(ResourceAlreadyExistsException.class,
//				() -> patientService.create(expectedPatientDto));
//
//		assertEquals(Constants.PATIENT_ALREADY_EXISTS + id, exception.getMessage());
//	}
//
//	@Test
//	void patientsGetOne() {
//		long id = 1L;
//		Patient patient = new Patient(id, "", "", null,
//				null, null);
//		PatientDto expectedPatientDto = new PatientDto(id, "", "", null,
//				null, null);
//		when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
//		when(patientMapper.patientToPatientDto(patient)).thenReturn(expectedPatientDto);
//
//		PatientDto actualPatientDto = patientService.findById(1L);
//
//		assertEquals(expectedPatientDto, actualPatientDto);
//	}
//
//	@Test
//	void patientsGetOneShouldThrowNotFoundException() {
//		long id = 666L;
//		when(patientRepository.findById(id)).thenReturn(Optional.empty());
//
//		Exception exception = assertThrows(ResourceNotFoundException.class, () -> patientService.findById(id));
//
//		assertEquals(Constants.PATIENT_NOT_FOUND + id, exception.getMessage());
//	}
//
//	@Test
//	void patientsPut() {
//		long id = 1L;
//		Patient oldPatient = new Patient(id, "", "1", null,
//				null, null);
//		Patient newPatient = new Patient(id, "", "", null,
//				null, null);
//		PatientDto expectedPatientDto = new PatientDto(id, "", "", null,
//				null, null);
//		when(patientRepository.findById(id)).thenReturn(Optional.of(oldPatient));
//		when(patientMapper.patientToPatientDto(newPatient)).thenReturn(expectedPatientDto);
//		when(patientMapper.patientDtoToPatient(expectedPatientDto)).thenReturn(newPatient);
//		when(patientRepository.save(newPatient)).thenAnswer(invocation -> invocation.getArguments()[0]);
//
//		PatientDto actualPatientDto = patientService.update(expectedPatientDto);
//
//		assertEquals(expectedPatientDto, actualPatientDto);
//	}
//
//	@Test
//	void patientsPutShouldThrowNotFoundException() {
//		long id = 666L;
//		PatientDto expectedPatientDto = new PatientDto(id, "", "", null,
//				null, null);
//		when(patientRepository.findById(id)).thenReturn(Optional.empty());
//
//		Exception exception = assertThrows(ResourceNotFoundException.class,
//				() -> patientService.update(expectedPatientDto));
//
//		assertEquals(Constants.PATIENT_NOT_FOUND + id, exception.getMessage());
//	}

	@Test
	void patientsDelete() {
		long id = 1L;
		when(patientRepository.findById(id))
				.thenReturn(Optional.of(new Patient(id, null,null,null,
						null,null)));

		patientService.deleteById(id);

		verify(patientRepository, times(1)).deleteById(id);
	}

	@Test
	void patientsDeleteShouldThrowNotFoundException() {
		long id = 13L;
		when(patientRepository.findById(id))
				.thenReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> patientService.deleteById(id));

		assertEquals(Constants.PATIENT_NOT_FOUND + id, exception.getMessage());
	}

}
