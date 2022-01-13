package com.dddd.doctorpatientrest.web.mapstruct.mappers;

import com.dddd.doctorpatientrest.database.entities.Role;
import com.dddd.doctorpatientrest.database.entities.Status;
import com.dddd.doctorpatientrest.database.entities.User;
import com.dddd.doctorpatientrest.general.constants.RoleEnum;
import com.dddd.doctorpatientrest.general.constants.StatusEnum;
import com.dddd.doctorpatientrest.web.mapstruct.dto.UserDto;
import com.dddd.doctorpatientrest.web.mapstruct.dto.rabbit_dto.UserRabbitDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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

	UserRabbitDto userDtoToUserRabbitDto(UserDto userDto);

	UserRabbitDto userToUserRabbitDto(User user);

	List<UserDto> userListToUserDtoList(List<User> users);

	@Named("statusToStatusString")
	default String statusToStatusString(Status status) {
		if (status != null) {
			return status.getName().toString();
		}
		return null;
	}

	@Named("statusStringToStatus")
	default Status statusToStatusString(String statusString) {
		Status status = new Status();
		if (statusString != null) {
			switch (statusString) {
				case "FIRST_IN":
					status.setName(StatusEnum.FIRST_IN);
					return status;
				case "ACTIVE":
					status.setName(StatusEnum.ACTIVE);
					return status;
				case "BLOCKED":
					status.setName(StatusEnum.BLOCKED);
					return status;
				default:
					return null;
			}
		}
		return null;
	}

	@Named("rolesToRolesString")
	default Set<String> rolesToRolesString(Set<Role> roleSet) {
		Set<String> roleNames = new HashSet<>();
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
	default Set<Role> rolesStringToRoles(Set<String> roleNames) {
		Set<Role> roles = new HashSet<>();
		if (roleNames != null) {
			roleNames.forEach(roleName -> {
				switch (roleName) {
					case "ROLE_USER":
						Role role = new Role();
						role.setName(RoleEnum.ROLE_USER);
						roles.add(role);
						break;
					case "ROLE_ADMIN":
						Role roleAdmin = new Role();
						roleAdmin.setName(RoleEnum.ROLE_ADMIN);
						roles.add(roleAdmin);
						break;
					default:
				}
			});
			return roles;
		}
		return new HashSet<>();
	}
}
