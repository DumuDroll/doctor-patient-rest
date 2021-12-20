package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.application.constants.RoleEnum;
import com.dddd.doctorpatientrest.application.constants.StatusEnum;
import com.dddd.doctorpatientrest.database.entities.Role;
import com.dddd.doctorpatientrest.database.entities.Status;
import com.dddd.doctorpatientrest.database.entities.User;
import com.dddd.doctorpatientrest.web.mapstruct.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {

	@Mapping(source = "status", target = "status", qualifiedByName = "statusStringToStatus")
	@Mapping(source = "roles", target = "roles", qualifiedByName = "rolesStringToRoles")
	User userDtoToUser(UserDto userDto);

	@Mapping(source = "status", target = "status", qualifiedByName = "statusToStatusString")
	@Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToRolesString")
	UserDto userToUserDto(User user);

	@Named("statusToStatusString")
	default String statusToStatusString(Status status) {
		if (status != null) {
			return status.toString();
		}
		return null;
	}

	@Named("statusStringToStatus")
	default Status statusToStatusString(String statusString) {
		Status status = new Status();
		switch (statusString) {
			case "first_in":
				status.setName(StatusEnum.FIRST_IN);
				return status;
			case "active":
				status.setName(StatusEnum.ACTIVE);
				return status;
			case "blocked":
				status.setName(StatusEnum.BLOCKED);
				return status;
			default:
				return null;
		}
	}

	@Named("rolesToRolesString")
	default List<String> rolesToRolesString(Set<Role> roleSet) {
		List<String> roleNames = new ArrayList<>();
		roleSet.forEach(role -> {
			switch (role.getName()) {
				case ROLE_USER:
					roleNames.add(RoleEnum.ROLE_USER.toString());
					break;
				case ROLE_ADMIN:
					roleNames.add(RoleEnum.ROLE_ADMIN.toString());
					break;
				default:
			}
		});
		return roleNames;
	}

	@Named("rolesStringToRoles")
	default Set<Role> rolesStringToRoles(List<String> roleNames) {
		Set<Role> roles = new HashSet<>();
		roleNames.forEach(roleName -> {
			switch (roleName) {
				case "User":
					Role role = new Role();
					role.setName(RoleEnum.ROLE_USER);
					roles.add(role);
					break;
				case "Admin":
					Role roleAdmin = new Role();
					roleAdmin.setName(RoleEnum.ROLE_ADMIN);
					roles.add(roleAdmin);
					break;
				default:
			}
		});
		return roles;
	}
}
