package com.dddd.doctorpatientrest.database.repositories;

import com.dddd.doctorpatientrest.database.entities.FullInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FullInfoRepository extends JpaRepository<FullInfo, Long> {

	Page<FullInfo> findAllByEmailContaining(String email, Pageable pageable);

}
