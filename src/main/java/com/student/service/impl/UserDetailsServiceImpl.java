package com.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.student.dao.StudentDao;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails= studentDao.getUserByUsername(username);
		return userDetails;
	}
	
}
