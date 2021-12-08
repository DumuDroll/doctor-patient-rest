package com.dddd.doctorpatientrest.database.repositories;

import com.dddd.doctorpatientrest.database.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	Page<Patient> findAllByFirstNameContainingAndLastNameContaining(String fName, String lName, Pageable pageable);
}
