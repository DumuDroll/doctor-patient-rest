package com.dddd.doctorpatientrest.database.repositories;

import com.dddd.doctorpatientrest.database.entities.PatientDrug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientPrescriptionRepository extends JpaRepository<PatientDrug, Long> {
}
