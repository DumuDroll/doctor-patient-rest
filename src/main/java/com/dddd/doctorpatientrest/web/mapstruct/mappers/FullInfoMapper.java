package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.FullInfo;
import com.dddd.doctorpatientrest.web.mapstruct.dto.FullInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper
public interface FullInfoMapper {

	FullInfoDto toFullInfoDto(FullInfo fullInfo);

	@Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "birthDateStringToBirthDateDate")
	FullInfo toFullInfo(FullInfoDto fullInfoDto);

	List<FullInfoDto> toFullInfoDtoList(List<FullInfo> fullInfos);

	@Named("birthDateStringToBirthDateDate")
	default LocalDate birthDateStringToBirthDateDate(String birthDate) {
		if (birthDate != null && !birthDate.equals("")) {
			return LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		return null;
	}
}
