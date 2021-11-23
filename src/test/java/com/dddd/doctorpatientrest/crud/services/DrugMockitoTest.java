package com.dddd.doctorpatientrest.crud.services;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.service_impls.DrugServiceImpl;
import com.dddd.doctorpatientrest.application.services.service_impls.PatientServiceImpl;
import com.dddd.doctorpatientrest.database.entities.Drug;
import com.dddd.doctorpatientrest.database.entities.Patient;
import com.dddd.doctorpatientrest.database.repositories.DrugRepository;
import com.dddd.doctorpatientrest.database.repositories.PatientRepository;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientSlimDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.DrugMapper;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.PatientMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrugMockitoTest {
	@Mock
	private DrugRepository drugRepository;

	@Mock
	private DrugMapper drugMapper;

	@InjectMocks
	private DrugServiceImpl drugService;

	@Mock
	private PatientRepository patientRepository;

	@Mock
	private PatientMapper patientMapper;

	@Mock
	private PatientServiceImpl patientService;

	@Test
	void drugsGetAll() {
		Drug drug1 = new Drug(1L, "DMT", new ArrayList<>());
		Drug drug2 = new Drug(2L, "HTML", new ArrayList<>());
		List<Drug> drugs = new ArrayList<>();
		drugs.add(drug1);
		drugs.add(drug2);
		DrugDto drugDto1 = new DrugDto(1L, "DMT", new ArrayList<>());
		DrugDto drugDto2 = new DrugDto(2L, "HTML", new ArrayList<>());
		List<DrugDto> expectedDrugDtoList = new ArrayList<>();
		expectedDrugDtoList.add(drugDto1);
		expectedDrugDtoList.add(drugDto2);
		Mockito.when(drugRepository.findAll()).thenReturn(drugs);
		Mockito.when(drugMapper.drugListToDrugDtoList(drugs)).thenReturn(expectedDrugDtoList);

		List<DrugDto> actualDrugDtoList = drugService.findAll();

		assertEquals(expectedDrugDtoList, actualDrugDtoList);
	}

	@Test
	void drugsGetAllShouldThrowNotFoundException() {
		Mockito.when(drugRepository.findAll()).thenReturn(new ArrayList<>());

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> drugService.findAll());

		assertEquals(Constants.NO_DATA_IN_DB + 404, exception.getMessage());
	}

	@Test
	void drugsPost() {
		long id = 1L;
		Drug drug = new Drug(id, "DMT", new ArrayList<>());
		DrugDto expectedDrugDto = new DrugDto(id, "DMT", new ArrayList<>());
		when(drugRepository.findById(id)).thenReturn(Optional.empty());
		when(drugMapper.drugToDrugDto(drug)).thenReturn(expectedDrugDto);
		when(drugMapper.drugDtoToDrug(expectedDrugDto)).thenReturn(drug);
		when(drugRepository.save(drug)).thenAnswer(invocation -> invocation.getArguments()[0]);

		DrugDto actualDrugDto = drugService.create(expectedDrugDto);

		assertEquals(expectedDrugDto, actualDrugDto);
	}

	@Test
	void drugsPostShouldThrowAlreadyExistsException() {
		long id = 1L;
		Drug drug = new Drug(id, "DMT", new ArrayList<>());
		DrugDto drugDto = new DrugDto(id, "DMT", new ArrayList<>());
		when(drugRepository.findById(id)).thenReturn(Optional.of(drug));

		Exception exception = assertThrows(ResourceAlreadyExistsException.class,
				() -> drugService.create(drugDto));

		assertEquals(Constants.DRUG_ALREADY_EXISTS + id, exception.getMessage());
	}

	@Test
	void drugsGetOne() {
		long id = 1L;
		Drug drug = new Drug(id, "DMT", new ArrayList<>());
		DrugDto drugDto = new DrugDto(id, "DMT", new ArrayList<>());
		when(drugRepository.findById(id)).thenReturn(Optional.of(drug));
		when(drugMapper.drugToDrugDto(drug)).thenReturn(drugDto);

		DrugDto actualDrugDto = drugService.findById(id);

		assertEquals(drugDto, actualDrugDto);
	}

	@Test
	void drugsGetOneShouldThrowNotFoundException() {
		long id = 666L;
		when(drugRepository.findById(id)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> drugService.findById(id));

		assertEquals(Constants.DRUG_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void drugsPut() {
		long id = 1L;
		Drug oldDrug = new Drug(id, "DMTT", new ArrayList<>());
		Drug newDrug = new Drug(id, "DMT", new ArrayList<>());
		DrugDto expectedDrugDto = new DrugDto(id, "DMT", new ArrayList<>());
		when(drugRepository.findById(id)).thenReturn(Optional.of(oldDrug));
		when(drugMapper.drugToDrugDto(newDrug)).thenReturn(expectedDrugDto);
		when(drugMapper.drugDtoToDrug(expectedDrugDto)).thenReturn(newDrug);
		when(drugRepository.save(newDrug))
				.thenAnswer(invocation -> invocation.getArguments()[0]);

		DrugDto actualDrugDto = drugService.update(expectedDrugDto);

		assertEquals(expectedDrugDto, actualDrugDto);
	}

	@Test
	void drugsPutShouldThrowNotFoundException() {
		long id = 1L;
		DrugDto expectedDrugDto = new DrugDto(id, "DMT", new ArrayList<>());
		when(drugRepository.findById(id)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> drugService.update(expectedDrugDto));

		assertEquals(Constants.DRUG_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void drugsPatchWithDrugGetPatientsEqualsNull() {
		long id = 1L;
		Drug drug = new Drug(id, "DMT", null);
		DrugDto expectedDrugDto = new DrugDto(id, "DMT", null);
		Patient patient = new Patient(id, "", "", null,
				null, null);
		PatientDto expectedPatientDto = new PatientDto(id, "", "", null,
				null, null);
		when(patientMapper.patientDtoToPatient(expectedPatientDto)).thenReturn(patient);
		when(patientService.findById(id)).thenReturn(expectedPatientDto);
		when(drugRepository.findById(id)).thenReturn(Optional.of(drug));
		when(drugMapper.drugToDrugDto(drug)).thenReturn(expectedDrugDto);
		when(drugMapper.drugDtoToDrug(expectedDrugDto)).thenReturn(drug);
		when(drugRepository.save(drug))
				.thenAnswer(invocation -> invocation.getArguments()[0]);
		when(patientRepository.save(patient))
				.thenAnswer(invocation -> invocation.getArguments()[0]);

		DrugDto actualDrugDto = drugService.addPatientToDrug(patient.getId(), expectedDrugDto);


		PatientSlimDto patientSlimDto = new PatientSlimDto(id, "", "");
		List<PatientSlimDto> patientSlimDtoList = new ArrayList<>();
		patientSlimDtoList.add(patientSlimDto);
		expectedDrugDto.setPatients(patientSlimDtoList);

		assertEquals(expectedDrugDto, actualDrugDto);
	}

	@Test
	void drugsDelete() {
		long id = 1L;
		when(drugRepository.findById(id))
				.thenReturn(Optional.of(new Drug(id, null, null)));

		drugService.deleteById(id);

		verify(drugRepository, times(1)).deleteById(id);
	}

	@Test
	void drugsDeleteShouldThrowNotFoundException() {
		long id = 1L;
		when(drugRepository.findById(id))
				.thenReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> drugService.deleteById(id));

		assertEquals(Constants.DRUG_NOT_FOUND + id, exception.getMessage());
	}

}
