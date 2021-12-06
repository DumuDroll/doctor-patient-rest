package com.dddd.doctorpatientrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = { DoctorPatientRestApplication.class, Jsr310JpaConverters.class })
public class DoctorPatientRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorPatientRestApplication.class, args);
	}

}
