package com.dddd.doctorpatientrest.web.security.user_details;

import com.dddd.doctorpatientrest.database.entities.Status;
import com.dddd.doctorpatientrest.database.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MyUserDetails implements UserDetails {
	private Long id;

	private final String username;

	@JsonIgnore
	private final String password;

	private final Status status;

	private final Collection<? extends GrantedAuthority> authorities;

	public MyUserDetails(Long id, String username, String password, Status status,
						 Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.status = status;
		this.authorities = authorities;
	}

	public static MyUserDetails build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new MyUserDetails(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				user.getStatus(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
