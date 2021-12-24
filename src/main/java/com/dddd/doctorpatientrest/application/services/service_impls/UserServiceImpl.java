package com.dddd.doctorpatientrest.application.services.service_impls;

import com.dddd.doctorpatientrest.application.constants.Constants;
import com.dddd.doctorpatientrest.application.constants.RoleEnum;
import com.dddd.doctorpatientrest.application.constants.StatusEnum;
import com.dddd.doctorpatientrest.application.exceptions.PasswordAlreadySetException;
import com.dddd.doctorpatientrest.application.exceptions.ResourceAlreadyExistsException;
import com.dddd.doctorpatientrest.application.exceptions.ResourceNotFoundException;
import com.dddd.doctorpatientrest.application.services.UserService;
import com.dddd.doctorpatientrest.database.entities.Role;
import com.dddd.doctorpatientrest.database.entities.Status;
import com.dddd.doctorpatientrest.database.entities.User;
import com.dddd.doctorpatientrest.database.repositories.RoleRepository;
import com.dddd.doctorpatientrest.database.repositories.StatusRepository;
import com.dddd.doctorpatientrest.database.repositories.UserRepository;
import com.dddd.doctorpatientrest.web.mapstruct.decorators.UserDecorator;
import com.dddd.doctorpatientrest.web.mapstruct.dto.UserDto;
import com.dddd.doctorpatientrest.web.mapstruct.mappers.UserMapper;
import com.dddd.doctorpatientrest.web.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final JwtUtils jwtUtils;

	private final UserMapper userMapper;

	private final UserDecorator userDecorator;

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final StatusRepository statusRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final AuthenticationManager authenticationManager;

	@Autowired
	public UserServiceImpl(JwtUtils jwtUtils,
						   UserMapper userMapper,
						   UserDecorator userDecorator,
						   UserRepository userRepository,
						   RoleRepository roleRepository,
						   StatusRepository statusRepository,
						   AuthenticationManager authenticationManager,
						   BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.jwtUtils = jwtUtils;
		this.userMapper = userMapper;
		this.userDecorator = userDecorator;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.statusRepository = statusRepository;
		this.authenticationManager = authenticationManager;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public ResponseEntity<Map<String, Object>> findAllFiltered(String email, int page, int size) {

		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));
		Page<User> users = userRepository.findAllByUsernameContaining(email, pageRequest);
		Map<String, Object> response = new HashMap<>();
		response.put("data", userMapper.userListToUserDtoList(users.getContent()));
		response.put("currentPage", users.getNumber());
		response.put("pageSize", users.getSize());
		response.put("totalItems", users.getTotalElements());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public List<UserDto> findAll() {
		return userMapper.userListToUserDtoList(userRepository.findAll());
	}

	@Override
	public UserDto findById(long id) {
		return userRepository.findById(id).map(userMapper::userToUserDto)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND, id));
	}

	@Override
	public UserDto create(UserDto userDto) {
		if (userRepository.existsByUsername(userDto.getUsername())) {
			throw new ResourceAlreadyExistsException(Constants.USER_ALREADY_EXISTS, userDto.getUsername());
		}

		return userMapper.userToUserDto(userRepository.save(userDecorator.decorate(userMapper.userDtoToUser(userDto))));
	}

	@Override
	public UserDto update(UserDto userDto) {
		User user = new User();
		Optional<User> optionalUser = userRepository.findById(userDto.getId());

		if(optionalUser.isPresent()){
			user=optionalUser.get();
		}

		Set<String> strRoles = userDto.getRoles();
		Set<Role> roles = new HashSet<>();
		String strStatus = userDto.getStatus();

		if (strStatus != null) {
			Optional<Status> status = statusRepository.findByName(StatusEnum.valueOf(strStatus));
			if (status.isPresent()) {
				user.setStatus(status.get());
			} else {
				user.setStatus(new Status(StatusEnum.valueOf(strStatus)));
			}
		}

		if (strRoles != null) {
			strRoles.forEach(strRole -> {
				Optional<Role> role = roleRepository.findByName(RoleEnum.valueOf(strRole));
				role.ifPresent(roles::add);
				if (role.isPresent()) {
					roles.add(role.get());
				} else {
					roles.add(new Role(RoleEnum.valueOf(strRole)));
				}
			});
		}

		user.setUsername(userDto.getUsername());
		user.setId(userDto.getId());
		user.setRoles(roles);

		return userMapper.userToUserDto(userRepository.save(user));
	}

	public UserDto login(UserDto userDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

		Optional<User> user = userRepository.findByUsername(userDto.getUsername());
		if (user.isPresent()) {
			userDto = userMapper.userToUserDto(user.get());
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		userDto.setJwt(jwtUtils.generateJwtToken(authentication));

		return userDto;
	}

	public UserDto firstLogin(UserDto userDto) {
		Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
		User user = new User();
		if (optionalUser.isPresent()) {
			if (!optionalUser.get().getStatus().getName().equals(StatusEnum.FIRST_IN)) {
				throw new PasswordAlreadySetException(Constants.PASSWORD_ALREADY_SET, userDto.getUsername());
			}
			user = optionalUser.get();
			user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
			Optional<Status> status = statusRepository.findByName(StatusEnum.ACTIVE);
			if (status.isPresent()) {
				user.setStatus(status.get());
			} else {
				user.setStatus(new Status((StatusEnum.ACTIVE)));
			}
		}

		return userMapper.userToUserDto(userRepository.save(user));
	}

}
