package com.dddd.doctorpatientrest.crud;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.service_impls.DoctorServiceImpl;
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
class DoctorWithH2Test {

	private final DoctorServiceImpl doctorService;

	private final PatientRepository patientRepository;

	private final DoctorRepository doctorRepository;

	private final FullInfoRepository fullInfoRepository;

	private final DrugRepository drugRepository;

	@Autowired
	DoctorWithH2Test(DoctorServiceImpl doctorService,
					 PatientRepository patientRepository,
					 DoctorRepository doctorRepository,
					 FullInfoRepository fullInfoRepository,
					 DrugRepository drugRepository) {
		this.doctorService = doctorService;
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
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
	void testDoctorsGetAll() {
		List<DoctorDto> expectedDoctorDto = getDoctorDtoList();
		List<DoctorDto> actualDoctorDto = doctorService.findAll();
		assertEquals(expectedDoctorDto, actualDoctorDto);
	}

	@Test
	void testDoctorsGetOne() {
		long id = 1L;
		DoctorDto expectedDoctorDto = getDoctorDto(id);
		DoctorDto actualDoctorDto = doctorService.findById(id);
		assertEquals(expectedDoctorDto, actualDoctorDto);
	}

	@Test
	void testDoctorGetShouldThrowNotFoundException() {
		long id = 666L;
		Exception exception = assertThrows(ResourceNotFoundException.class, () -> doctorService.findById(id));
		assertEquals(Constants.DOCTOR_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void testDoctorsPost() {
		long id = 4L;
		DoctorDto expectedDoctorDto = getDoctorDto(id);
		DoctorDto actualDoctorDto = doctorService.create(expectedDoctorDto);
		assertEquals(actualDoctorDto, expectedDoctorDto);
	}

	@Test
	void testDoctorsPostShouldThrowAlreadyExistsException() {
		long id = 1L;
		DoctorDto expectedDoctorDto = getDoctorDto(id);
		Exception exception = assertThrows(ResourceAlreadyExistsException.class,
				() -> doctorService.create(expectedDoctorDto));
		assertEquals(Constants.DOCTOR_ALREADY_EXISTS + id, exception.getMessage());
	}

	@Test
	void testDoctorsPut() {
		long id = 2L;
		DoctorDto expectedDoctorDto = getDoctorDto(id);
		DoctorDto actualDoctorDto = doctorService.update(expectedDoctorDto);
		assertEquals(actualDoctorDto, expectedDoctorDto);
	}

	@Test
	void testDoctorsPutShouldThrowNotFoundException() {
		long id = 123123L;
		DoctorDto expectedDoctorDto = getDoctorDto(id);
		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> doctorService.update(expectedDoctorDto));
		assertEquals(Constants.DOCTOR_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void testDoctorsDelete() {
		long id = 1L;
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

	public List<DoctorDto> getDoctorDtoList() {
		List<DoctorDto> doctorDtoList = new ArrayList<>();
		for (long i = 1; i < 4; i++) {
			DoctorDto doctorDto = getDoctorDto(i);
			doctorDtoList.add(doctorDto);
		}
		return doctorDtoList;
	}

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
