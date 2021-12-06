package com.dddd.doctorpatientrest.crud.services;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.service_impls.PatientServiceImpl;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DrugDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.FullInfoDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.PatientDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data/inserts.sql")
class PatientWithH2Test {

	private final PatientServiceImpl patientService;

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PatientWithH2Test(PatientServiceImpl patientService,
							 JdbcTemplate jdbcTemplate) {
		this.patientService = patientService;
		this.jdbcTemplate = jdbcTemplate;
	}

	@AfterEach
	public void deleteAll() {
		JdbcTestUtils.deleteFromTables(jdbcTemplate,  "patients_drugs", "patients","full_info", "doctors", "drugs" );
	}

	@Test
	void patientsGetAll() {
		List<PatientDto> expectedPatientDtoList = getPatientDtoList();

		List<PatientDto> actualPatientDtoList = patientService.findAll();

		assertEquals(expectedPatientDtoList, actualPatientDtoList);
	}

	@Test
	void patientsPost() {
		long id = 4L;
		PatientDto expectedPatientDto = new PatientDto(id, "testfName" + id, "testlName" + id,
				getFullInfoDto(id), null, null, "", "", null);

		PatientDto actualPatientDto = patientService.create(expectedPatientDto);

		assertEquals(expectedPatientDto.getFirstName(), actualPatientDto.getFirstName());
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
	void patientsGetOne() {
		long id = 1L;
		List<DrugDto> drugDtoList = new ArrayList<>();
		drugDtoList.add(getDrugDto(id));
		PatientDto expectedPatientDto = getPatientDto(id, drugDtoList);

		PatientDto actualPatientDto = patientService.findById(1L);

		assertEquals(expectedPatientDto, actualPatientDto);
	}

	@Test
	void patientsGetOneShouldThrowNotFoundException() {
		long id = 666L;

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> patientService.findById(id));

		assertEquals(Constants.PATIENT_NOT_FOUND + id, exception.getMessage());
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
	void patientPatch() {
		long id = 1L;
		long doctorId = 2L;
		List<DrugDto> drugDtoList = new ArrayList<>();
		drugDtoList.add(getDrugDto(id));
		PatientDto expectedPatientDto = getPatientDto(id, drugDtoList);
expectedPatientDto.setDoctor(new DoctorDto(2, "testName2", "testExp2"));
	expectedPatientDto.setDoctorName("testName2");

		PatientDto actualPatientDto = patientService.addDoctorToPatient(doctorId, expectedPatientDto);

		assertEquals(expectedPatientDto, actualPatientDto);
	}

	@Test
	void patientPatchShouldThrowNotFoundException() {
		long id = 1;
		long doctorId = 666L;
		List<DrugDto> drugDtoList = new ArrayList<>();
		drugDtoList.add(getDrugDto(id));
		PatientDto expectedPatientDto = getPatientDto(id,drugDtoList);

		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> patientService.addDoctorToPatient(doctorId, expectedPatientDto));

		assertEquals(Constants.DOCTOR_NOT_FOUND + doctorId, exception.getMessage());

	}

	@Test
	void patientsDelete() {
		long id = 1L;
		patientService.deleteById(id);

		Exception exception = assertThrows(ResourceNotFoundException.class,
				() -> patientService.findById(id));

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

	public FullInfoDto getFullInfoDto(long i) {
		return new FullInfoDto(i, null, "testEmail" + i, "testNumber" + i);
	}

	public DoctorDto getDoctorDto(long i) {
		return new DoctorDto(i, "testName" + i, "testExp" + i);
	}

	public PatientDto getPatientDto(long i, List<DrugDto> drugDtoList) {
		List<String> drugNames = new ArrayList<>();
		drugNames.add("drug"+i);
		return new PatientDto(i, "testfName" + i, "testlName" + i, getFullInfoDto(i), getDoctorDto(i),
				drugDtoList, "testEmail" + i, "testName"+i, drugNames);
	}


	public List<DrugDto> getDrugDtoList() {
		List<DrugDto> drugDtoList = new ArrayList<>();
		for (long i = 1; i < 4; i++) {
			drugDtoList.add(getDrugDto(i));
		}
		return drugDtoList;
	}

	public DrugDto getDrugDto(long i) {
		return new DrugDto(i, "drug" + i);
	}
}
