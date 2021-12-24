package com.dddd.doctorpatientrest.web.mapstruct.decorators;

import com.dddd.doctorpatientrest.application.constants.RoleEnum;
import com.dddd.doctorpatientrest.application.constants.StatusEnum;
import com.dddd.doctorpatientrest.database.entities.Role;
import com.dddd.doctorpatientrest.database.entities.Status;
import com.dddd.doctorpatientrest.database.entities.User;
import com.dddd.doctorpatientrest.database.repositories.RoleRepository;
import com.dddd.doctorpatientrest.database.repositories.StatusRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDecorator {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final RoleRepository roleRepository;

	private final StatusRepository statusRepository;

	public UserDecorator(BCryptPasswordEncoder bCryptPasswordEncoder,
						 RoleRepository roleRepository,
						 StatusRepository statusRepository) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
		this.statusRepository = statusRepository;
	}

	public User decorate(User user) {
		if (user.getPassword() != null && !"".equals(user.getPassword())) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}

		Optional<Status> status = statusRepository.findByName(StatusEnum.FIRST_IN);
		if (status.isPresent()) {
			user.setStatus(status.get());
		} else {
			user.setStatus(new Status(StatusEnum.FIRST_IN));
		}

		Optional<Role> role = roleRepository.findByName(RoleEnum.ROLE_USER);
		if (role.isPresent()) {
			user.getRoles().add(role.get());
		} else {
			user.getRoles().add(new Role(RoleEnum.ROLE_USER));
		}

		return user;
	}
}
