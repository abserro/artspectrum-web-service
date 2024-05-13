package com.example.artspectrum.config;

import com.example.artspectrum.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
	private final User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNameRole()))
				.collect(Collectors.toList());
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getLogin();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return user.getRemoved() == null;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return user.getRemoved() == null;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return user.getRemoved() == null;
	}
	
	@Override
	public boolean isEnabled() {
		return user.getRemoved() == null;
	}
}
