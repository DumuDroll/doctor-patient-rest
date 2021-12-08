package com.dddd.doctorpatientrest.database.repositories;

import com.dddd.doctorpatientrest.database.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	Page<Doctor> findAllByNameContaining(String name, Pageable pageable);

}
