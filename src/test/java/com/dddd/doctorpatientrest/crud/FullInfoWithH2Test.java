package com.dddd.doctorpatientrest.crud;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.service_impls.FullInfoServiceImpl;
import com.dddd.doctorpatientrest.database.repositories.DoctorRepository;
import com.dddd.doctorpatientrest.database.repositories.DrugRepository;
import com.dddd.doctorpatientrest.database.repositories.FullInfoRepository;
import com.dddd.doctorpatientrest.database.repositories.PatientRepository;
import com.dddd.doctorpatientrest.web.mapstruct.dto.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data/inserts.sql")
public class FullInfoWithH2Test {

	private final FullInfoServiceImpl fullInfoService;

	private final PatientRepository patientRepository;

	private final DoctorRepository doctorRepository;

	private final FullInfoRepository fullInfoRepository;

	private final DrugRepository drugRepository;

	@Autowired
	public FullInfoWithH2Test(FullInfoServiceImpl fullInfoService,
							  PatientRepository patientRepository,
							  DoctorRepository doctorRepository,
							  FullInfoRepository fullInfoRepository,
							  DrugRepository drugRepository) {
		this.fullInfoService = fullInfoService;
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
		this.fullInfoRepository = fullInfoRepository;
		this.drugRepository = drugRepository;
	}

	@AfterEach
	public void deleteAll() {
		patientRepository.deleteAll();
		doctorRepository.deleteAll();
		fullInfoRepository.deleteAll();
		drugRepository.deleteAll();
	}

	@Test
	void fullInfoGetAll() {
		List<FullInfoDto> expectedFullInfoDtoList = getFullInfoDtoList();
		List<FullInfoDto> actualFullInfoDtoList = fullInfoService.findAll();
		assertEquals(expectedFullInfoDtoList, actualFullInfoDtoList);
	}

	@Test
	void fullInfosGetOne() {
		long id = 1L;
		FullInfoDto expectedFullInfoDto = getFullInfoDto(id);
		FullInfoDto actualFullInfoDto = fullInfoService.findById(id);
		assertEquals(expectedFullInfoDto, actualFullInfoDto);
	}

	@Test
	void fullInfoGetOneShouldThrowNotFoundException() {
		long id = 5L;
		FullInfoDto expectedFullInfoDto = getFullInfoDto(id);
		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> fullInfoService.create(expectedFullInfoDto));
		assertEquals(Constants.FULL_INFO_NOT_FOUND + id, exception.getMessage());
	}

//	@Test
//	void drugsPostShouldThrowAlreadyExistsException() {
//		long id = 2L;
//		DrugDto expectedDrugDto = getDrugDto(id);
//		Exception exception = assertThrows(ResourceAlreadyExistsException.class,
//				() -> drugService.create(expectedDrugDto));
//		assertEquals(Constants.DRUG_ALREADY_EXISTS + id, exception.getMessage());
//	}
//
//	@Test
//	void drugsPut() {
//		long id = 2L;
//		DrugDto expectedDrugDto = getDrugDto(id);
//		DrugDto actualDrugDto = drugService.update(expectedDrugDto);
//		assertEquals(expectedDrugDto, actualDrugDto);
//	}
//
//	@Test
//	void drugsPutShouldThrowNotFoundException() {
//		long id = 44L;
//		DrugDto expectedDrugDto = getDrugDto(id);
//		Exception exception = assertThrows(ResourceNotFoundException.class,
//				() -> drugService.update(expectedDrugDto));
//		assertEquals(Constants.DRUG_NOT_FOUND + id, exception.getMessage());
//	}
//
//	@Test
//	void drugsPatchShouldThrowAlreadyExistsException() {
//		long id = 2L;
//		DrugDto expectedDrugDto = getDrugDto(id);
//		Exception exception = assertThrows(ResourceAlreadyExistsException.class,
//				() -> drugService.addPatientToDrug(id, expectedDrugDto));
//		assertEquals("This drug is already prescribed to a patient with id: " + id, exception.getMessage());
//	}
//
//	@Test
//	void drugsDelete() {
//		long id = 3L;
//		drugService.deleteById(id);
//		Exception exception = assertThrows(ResourceNotFoundException.class, () -> drugService.findById(id));
//		assertEquals(Constants.DRUG_NOT_FOUND + id, exception.getMessage());
//	}
//
//	@Test
//	void drugsDeleteShouldThrowNotFoundException() {
//		long id = 666L;
//		Exception exception = assertThrows(ResourceNotFoundException.class, () -> drugService.deleteById(id));
//		assertEquals(Constants.DRUG_NOT_FOUND + id, exception.getMessage());
//	}

	public List<PatientDto> getPatientDtoList() {
		List<PatientDto> patientDtoList = new ArrayList<>();
		for (long i = 1; i < 4; i++) {
			List<DrugDto> drugDtoList = new ArrayList<>();
			drugDtoList.add(getDrugDto(i));
			patientDtoList.add(getPatientDto(i, drugDtoList));
		}
		return patientDtoList;
	}

	public List<DrugDto> getDrugDtoList() {
		List<DrugDto> drugDtoList = new ArrayList<>();
		for (long i = 1; i < 4; i++) {
			drugDtoList.add(getDrugDto(i));
		}
		return drugDtoList;
	}

	public List<PatientSlimDto> getPatientSlimDtoList() {
		List<PatientSlimDto> patientSlimDtoList = new ArrayList<>();
		for (long i = 1; i < 4; i++) {
			patientSlimDtoList.add(getPatientSlimDto(i));
		}
		return patientSlimDtoList;
	}

	public List<FullInfoDto> getFullInfoDtoList() {
		List<FullInfoDto> fullInfoDtoList = new ArrayList<>();
		for (long i = 1; i < 4; i++) {
			FullInfoDto fullInfoDto = getFullInfoDto(i);
			fullInfoDtoList.add(fullInfoDto);

		}
	}

	public FullInfoDto getFullInfoDto(long i) {
		return new FullInfoDto(i, "testDate" + i, "testEmail" + i, "testNumber" + i);
	}

	public DoctorDto getDoctorDto(long i) {
		return new DoctorDto(i, "testName" + i, "testExp" + i);
	}

	public PatientDto getPatientDto(long i, List<DrugDto> drugDtoList) {
		return new PatientDto(i, "testfName" + i, "testlName" + i, getFullInfoDto(i), getDoctorDto(i), drugDtoList);
	}

	public PatientSlimDto getPatientSlimDto(long i) {
		return new PatientSlimDto(i, "testfName" + i, "testlName" + i);
	}

	public DrugDto getDrugDto(long i) {
		List<PatientSlimDto> patientSlimDtoList = new ArrayList<>();
		patientSlimDtoList.add(getPatientSlimDto(i));
		return new DrugDto(i, "drug" + i, patientSlimDtoList);
	}
}
