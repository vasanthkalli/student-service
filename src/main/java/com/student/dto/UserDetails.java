package com.student.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails{
	
	private static final long serialVersionUID = 1L;
	private User user;
	
	public UserDetails(User user){
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
	
		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getUsername();
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
