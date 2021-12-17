package com.dddd.doctorpatientrest.web.security.user_details.iml;

import com.dddd.doctorpatientrest.database.entities.User;
import com.dddd.doctorpatientrest.database.repositories.UserRepository;
import com.dddd.doctorpatientrest.web.security.user_details.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(s);

		if (user == null) {
			throw new UsernameNotFoundException("There is no user with such username");
		}

		return new MyUserDetails((UserDetails) user);
	}
}
