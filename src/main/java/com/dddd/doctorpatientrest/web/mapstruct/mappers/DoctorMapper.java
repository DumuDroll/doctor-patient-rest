package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.Doctor;
import com.dddd.doctorpatientrest.web.mapstruct.dto.DoctorDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.rabbit_dto.DoctorRabbitDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DoctorMapper {

	DoctorDto toDoctorDto(Doctor doctor);

	Doctor toDoctor(DoctorDto doctorDto);

	List<DoctorDto> toDoctorDtoList(List<Doctor> doctors);

	DoctorRabbitDto doctorDtoToDoctorRabbit(DoctorDto doctorDto);

}
