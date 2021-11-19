package com.dddd.doctorpatientrest.crud;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.service_impls.PatientServiceImpl;
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
class PatientWithH2Test {

	private final PatientServiceImpl patientService;

	private final PatientRepository patientRepository;

	private final DoctorRepository doctorRepository;

	private final FullInfoRepository fullInfoRepository;

	private final DrugRepository drugRepository;

	@Autowired
	public PatientWithH2Test(PatientServiceImpl patientService,
							 PatientRepository patientRepository,
							 DoctorRepository doctorRepository,
							 FullInfoRepository fullInfoRepository,
							 DrugRepository drugRepository) {
		this.patientService = patientService;
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
	void patientsGetAll() {
		List<PatientDto> expectedPatientDtoList = getPatientDtoList();
		List<PatientDto> actualPatientDtoList = patientService.findAll();
		assertEquals(expectedPatientDtoList, actualPatientDtoList);
	}

	@Test
	void patientsGetOne() {
		long id = 1L;
		List<DrugDto> drugDtoList = new ArrayList<>();
		drugDtoList.add(getDrugDto(id));
		PatientDto expectedPatientDto = getPatientDto(id, drugDtoList);
		PatientDto actualPatientDto = patientService.findById(1L);
		assertEquals(actualPatientDto, expectedPatientDto);
	}

	@Test
	void patientsGetOneShouldThrowNotFoundException() {
		long id = 666L;
		Exception exception = assertThrows(ResourceNotFoundException.class, () -> patientService.findById(id));
		assertEquals(Constants.PATIENT_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void patientsPost() {
		long id = 4L;
		PatientDto expectedPatientDto = new PatientDto(id, "testfName" + id, "testlName" + id, getFullInfoDto(id), null, null);
		PatientDto actualPatientDto = patientService.create(expectedPatientDto);
		assertEquals(expectedPatientDto, actualPatientDto);
	}

	@Test
	void patientsPostShouldThrowAlreadyExistsException() {
		long id = 1L;
		List<DrugDto> drugDtoList = new ArrayList<>();
		drugDtoList.add(getDrugDto(id));
		PatientDto patientDto = getPatientDto(id, drugDtoList);
		Exception exception = assertThrows(ResourceAlreadyExistsException.class,
				() -> patientService.create(patientDto));
		assertEquals(Constants.PATIENT_ALREADY_EXISTS + id, exception.getMessage());
	}

	@Test
	void patientsPut() {
		long id = 1L;
		List<DrugDto> drugDtoList = new ArrayList<>();
		drugDtoList.add(getDrugDto(id));
		PatientDto expectedPatientDto = getPatientDto(id, drugDtoList);
		PatientDto actualPatientDto = patientService.update(expectedPatientDto);
		assertEquals(expectedPatientDto, actualPatientDto);
	}

	@Test
	void patientsPutShouldThrowNotFoundException() {
		long id = 666L;
		List<DrugDto> drugDtoList = new ArrayList<>();
		drugDtoList.add(getDrugDto(id));
		PatientDto expectedPatientDto = getPatientDto(id, drugDtoList);
		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> patientService.update(expectedPatientDto));
		assertEquals(Constants.PATIENT_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void patientsDelete() {
		long id = 1L;
		patientService.deleteById(id);
		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> patientService.findById(id));
		assertEquals(Constants.PATIENT_NOT_FOUND + id, exception.getMessage());
	}

	@Test
	void patientsDeleteShouldThrowNotFoundException() {
		long id = 13L;
		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> patientService.deleteById(id));
		assertEquals(Constants.PATIENT_NOT_FOUND + id, exception.getMessage());
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
