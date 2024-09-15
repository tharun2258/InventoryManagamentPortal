package com.user.registration.securityconfig;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.user.registration.entity.User;

public class CustomUserDetails implements UserDetails {
	
	private User user;
	
	

	public CustomUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRole().stream()
        .map(role -> new SimpleGrantedAuthority(role.getRolename()))
        .collect(Collectors.toSet());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	

}
