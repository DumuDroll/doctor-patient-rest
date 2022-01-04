package com.dddd.doctorpatientrest.database.repositories;

import com.dddd.doctorpatientrest.general.constants.RoleEnum;
import com.dddd.doctorpatientrest.database.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleEnum name);
}
