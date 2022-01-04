package com.dddd.doctorpatientrest.web.security.user_details;

import com.dddd.doctorpatientrest.general.constants.StatusEnum;
import com.dddd.doctorpatientrest.database.entities.User;
import com.dddd.doctorpatientrest.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		if(user.getStatus().getName().equals(StatusEnum.BLOCKED)){
			throw new AccessDeniedException("This account is blocked");
		}
		return MyUserDetails.build(user);
	}
}
