package com.dddd.doctorpatientrest.database.repositories;

import com.dddd.doctorpatientrest.database.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
