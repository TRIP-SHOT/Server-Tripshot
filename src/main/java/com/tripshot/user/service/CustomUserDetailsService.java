package com.tripshot.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tripshot.user.mapper.UserMapper;
import com.tripshot.user.model.CustomUserDetails;
import com.tripshot.user.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
	private final UserMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//유저에 대한 정보를 조회한 뒤 반환한다.
		User user = mapper.findByUserId(username);
		log.info("user={}",user);
		if(user != null) {
			return new CustomUserDetails(user);
		}
		
		return null;
	}

}
