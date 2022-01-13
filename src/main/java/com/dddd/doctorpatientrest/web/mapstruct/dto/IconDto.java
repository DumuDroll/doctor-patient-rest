package com.dddd.doctorpatientrest.web.mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IconDto {

	private String iconName;

	private byte[] icon;
}
