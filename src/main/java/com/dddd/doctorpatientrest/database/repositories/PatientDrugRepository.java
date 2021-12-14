package com.dddd.doctorpatientrest.database.repositories;

import com.dddd.doctorpatientrest.database.entities.PatientDrug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PatientDrugRepository extends JpaRepository<PatientDrug, Long> {

	Page<PatientDrug> findAllByPatientIdAndPrescriptionStartDateLessThanEqualAndPrescriptionEndDateGreaterThanEqual(long patientId, LocalDate startDate, LocalDate endDate, Pageable pageable);

}
