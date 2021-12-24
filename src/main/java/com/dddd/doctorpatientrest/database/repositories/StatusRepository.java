package com.dddd.doctorpatientrest.database.repositories;

import com.dddd.doctorpatientrest.application.constants.StatusEnum;
import com.dddd.doctorpatientrest.database.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {

	Optional<Status> findByName(StatusEnum name);

}
