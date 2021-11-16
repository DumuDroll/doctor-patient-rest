package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.FullInfo;
import com.dddd.doctorpatientrest.web.mapstruct.dto.FullInfoDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FullInfoMapper {

	FullInfoDto fullInfoToFullInfoDto(FullInfo fullInfo);

	FullInfo fullInfoDtoToFullInfo(FullInfoDto fullInfoDto);

	List<FullInfo> fullInfoDtoListToFullInfoList(List<FullInfoDto> fullInfoDtoList);

	List<FullInfoDto> fullInfoListToFullInfoDtoList(List<FullInfo> fullInfos);

}
