package com.dddd.doctorpatientrest.general.services.service_impls;

import com.dddd.doctorpatientrest.web.mapstruct.dto.rabbit_dto.DoctorRabbitDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.rabbit_dto.DrugRabbitDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.rabbit_dto.PatientRabbitDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.rabbit_dto.UserRabbitDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Log4j2
@Service
public class SenderService {

	private final RabbitTemplate rabbitTemplate;

	public SenderService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	private Message buildMessage(Object o) {
		try {
			ObjectMapper mapper = JsonMapper.builder()
					.findAndAddModules()
					.build();
			return MessageBuilder
					.withBody(mapper.writeValueAsString(o).getBytes(StandardCharsets.UTF_8))
					.setContentType(MessageProperties.CONTENT_TYPE_JSON)
					.build();
		}catch (JsonProcessingException jsonProcessingException){
			log.error(jsonProcessingException.getMessage());
			return MessageBuilder
					.withBody("".getBytes(StandardCharsets.UTF_8))
					.setContentType(MessageProperties.CONTENT_TYPE_JSON)
					.build();
		}
	}

	public void sendSavedDoctor(DoctorRabbitDto doctorDto) {
		rabbitTemplate.convertAndSend("savedDoctor", buildMessage(doctorDto));
	}

	public void sendDeletedDoctor(long id) {
		rabbitTemplate.convertAndSend("deletedDoctor", Long.toString(id));
	}

	public void sendSavedDrug(DrugRabbitDto drugDto) {
		rabbitTemplate.convertAndSend("savedDrug", buildMessage(drugDto));
	}

	public void sendDeletedDrug(long id) {
		rabbitTemplate.convertAndSend("deletedDrug", Long.toString(id));
	}

	public void sendSavedPatient(PatientRabbitDto patientDto) {
		rabbitTemplate.convertAndSend("savedPatient", buildMessage(patientDto));
	}

	public void sendUpdatedPatient(PatientRabbitDto patientDto) {
		rabbitTemplate.convertAndSend("updatedPatient", buildMessage(patientDto));
	}

	public void sendDeletedPatient(long id) {
		rabbitTemplate.convertAndSend("deletedPatient", Long.toString(id));
	}

	public void sendSavedUser(UserRabbitDto userDto) {
		rabbitTemplate.convertAndSend("savedUser", buildMessage(userDto));
	}

	public void sendDeletedUser(long id) {
		rabbitTemplate.convertAndSend("deletedUser", Long.toString(id));
	}

}