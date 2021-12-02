//package com.dddd.doctorpatientrest.crud.services;
//
//import com.dddd.doctorpatientrest.application.constants.Constants;
//import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
//import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
//import com.dddd.doctorpatientrest.application.services.service_impls.FullInfoServiceImpl;
//import com.dddd.doctorpatientrest.database.entities.FullInfo;
//import com.dddd.doctorpatientrest.database.repositories.FullInfoRepository;
//import com.dddd.doctorpatientrest.web.mapstruct.dto.FullInfoDto;
//import com.dddd.doctorpatientrest.web.mapstruct.mappers.FullInfoMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class FullInfoMockitoTest {
//
//	@Mock
//	private FullInfoRepository fullInfoRepository;
//
//	@Mock
//	private FullInfoMapper fullInfoMapper;
//
//	@InjectMocks
//	private FullInfoServiceImpl fullInfoService;
//
//	@Test
//	void fullInfosGetAll() {
//		FullInfo fullInfo1 = new FullInfo(1L, "", "",
//				"", null);
//		FullInfo fullInfo2 = new FullInfo(2L, "2", "",
//				"", null);
//		List<FullInfo> fullInfos = new ArrayList<>();
//		fullInfos.add(fullInfo1);
//		fullInfos.add(fullInfo2);
//		FullInfoDto fullInfoDto1 = new FullInfoDto(1L, "", "",
//				"", null);
//		FullInfoDto fullInfoDto2 = new FullInfoDto(2L, "", "",
//				"", null);
//		List<FullInfoDto> expectedFullInfoDtoList = new ArrayList<>();
//		expectedFullInfoDtoList.add(fullInfoDto1);
//		expectedFullInfoDtoList.add(fullInfoDto2);
//
//		Mockito.when(fullInfoRepository.findAll()).thenReturn(fullInfos);
//		Mockito.when(fullInfoMapper.fullInfoListToFullInfoDtoList(fullInfos)).thenReturn(expectedFullInfoDtoList);
//
//		List<FullInfoDto> actualFullInfoDtoList = fullInfoService.findAll();
//
//		assertEquals(expectedFullInfoDtoList, actualFullInfoDtoList);
//	}
//
//	@Test
//	void fullInfosGetAllShouldThrowNotFoundException() {
//		Mockito.when(fullInfoRepository.findAll()).thenReturn(new ArrayList<>());
//
//		Exception exception = assertThrows(ResourceNotFoundException.class, () -> fullInfoService.findAll());
//
//		assertEquals(Constants.NO_DATA_IN_DB + 404, exception.getMessage());
//	}
//
//	@Test
//	void fullInfosPost() {
//		long id = 1L;
//		FullInfo fullInfo = new FullInfo(id, "", "",
//				"", null);
//		FullInfoDto expectedFullInfoDto = new FullInfoDto(id, "", "",
//				"", null);
//		when(fullInfoRepository.findById(id)).thenReturn(Optional.empty());
//		when(fullInfoMapper.fullInfoToFullInfoDto(fullInfo)).thenReturn(expectedFullInfoDto);
//		when(fullInfoMapper.fullInfoDtoToFullInfo(expectedFullInfoDto)).thenReturn(fullInfo);
//		when(fullInfoRepository.save(fullInfo)).thenAnswer(invocation -> invocation.getArguments()[0]);
//
//		FullInfoDto actualFullInfoDto = fullInfoService.create(expectedFullInfoDto);
//
//		assertEquals(expectedFullInfoDto, actualFullInfoDto);
//	}
//
//	@Test
//	void fullInfosPostShouldThrowAlreadyExistsException() {
//		long id = 1L;
//		FullInfo fullInfo = new FullInfo(id, "", "",
//				"", null);
//		FullInfoDto expectedFullInfoDto = new FullInfoDto(id, "", "",
//				"", null);
//		when(fullInfoRepository.findById(id)).thenReturn(Optional.of(fullInfo));
//
//		Exception exception = assertThrows(ResourceAlreadyExistsException.class,
//				() -> fullInfoService.create(expectedFullInfoDto));
//
//		assertEquals(Constants.FULL_INFO_ALREADY_EXISTS + id, exception.getMessage());
//	}
//
//	@Test
//	void fullInfosGetOne() {
//		long id = 1L;
//		FullInfo fullInfo = new FullInfo(id, "", "",
//				"", null);
//		FullInfoDto expectedFullInfoDto = new FullInfoDto(id, "", "",
//				"", null);
//		when(fullInfoRepository.findById(id)).thenReturn(Optional.of(fullInfo));
//		when(fullInfoMapper.fullInfoToFullInfoDto(fullInfo)).thenReturn(expectedFullInfoDto);
//
//		FullInfoDto actualFullInfoDto = fullInfoService.findById(id);
//
//		assertEquals(expectedFullInfoDto, actualFullInfoDto);
//	}
//
//	@Test
//	void fullInfosGetOneShouldThrowNotFoundException() {
//		long id = 13L;
//		when(fullInfoRepository.findById(id)).thenReturn(Optional.empty());
//
//		Exception exception = assertThrows(ResourceNotFoundException.class,
//				() -> fullInfoService.findById(id));
//
//		assertEquals(Constants.FULL_INFO_NOT_FOUND + id, exception.getMessage());
//	}
//
//	@Test
//	void fullInfosPut() {
//		long id = 2L;
//		FullInfo oldFullInfo = new FullInfo(id, "", "",
//				"", null);
//		FullInfo newFullInfo = new FullInfo(id, "", "2",
//				"", null);
//		FullInfoDto expectedFullInfoDto = new FullInfoDto(id, "", "2",
//				"", null);
//		when(fullInfoRepository.findById(id)).thenReturn(Optional.of(oldFullInfo));
//		when(fullInfoMapper.fullInfoToFullInfoDto(newFullInfo)).thenReturn(expectedFullInfoDto);
//		when(fullInfoMapper.fullInfoDtoToFullInfo(expectedFullInfoDto)).thenReturn(newFullInfo);
//		when(fullInfoRepository.save(newFullInfo)).thenAnswer(invocation -> invocation.getArguments()[0]);
//
//
//		FullInfoDto actualFullInfoDto = fullInfoService.update(expectedFullInfoDto);
//
//		assertEquals(expectedFullInfoDto, actualFullInfoDto);
//	}
//
//	@Test
//	void fullInfosPutShouldThrowNotFoundException() {
//		long id = 44L;
//		FullInfoDto expectedFullInfoDto = new FullInfoDto(id, "", "2",
//				"", null);
//		when(fullInfoRepository.findById(id)).thenReturn(Optional.empty());
//
//		Exception exception = assertThrows(ResourceNotFoundException.class,
//				() -> fullInfoService.update(expectedFullInfoDto));
//
//		assertEquals(Constants.FULL_INFO_NOT_FOUND + id, exception.getMessage());
//	}
//
//	@Test
//	void fullInfosDelete() {
//		long id = 1L;
//		when(fullInfoRepository.findById(id)).thenReturn(Optional.of(new FullInfo(id, "", "",
//				"", null)));
//
//		fullInfoService.deleteById(id);
//
//		verify(fullInfoRepository, times(1)).deleteById(id);
//	}
//
//	@Test
//	void fullInfosDeleteShouldThrowNotFoundException() {
//		long id = 666L;
//		when(fullInfoRepository.findById(id))
//				.thenReturn(Optional.empty());
//
//		Exception exception = assertThrows(ResourceNotFoundException.class, () -> fullInfoService.deleteById(id));
//
//		assertEquals(Constants.FULL_INFO_NOT_FOUND + id, exception.getMessage());
//	}
//}
