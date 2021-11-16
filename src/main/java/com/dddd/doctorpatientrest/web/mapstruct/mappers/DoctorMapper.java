package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DoctorMapper {

	Doctor doctorDtoToDoctor(DoctorDto doctorDto);

	DoctorDto doctorToDoctorDto(Doctor doctor);

	List<DoctorDto> doctorListToDoctorDtoList(List<Doctor> doctors);

	List<Doctor> doctorDtoListToDoctorList(List<DoctorDto> doctorDtoList);

}
