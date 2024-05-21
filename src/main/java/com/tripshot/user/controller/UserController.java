package com.tripshot.user.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tripshot.global.ApiResponse;
import com.tripshot.user.model.JoinDto;
import com.tripshot.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService service;

	@PostMapping("/join")
	public ResponseEntity<ApiResponse<String>> join(@RequestBody JoinDto joinDto) {
		service.join(joinDto);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "회원가입 성공", "회원가입에 성공하셨습니다."), HttpStatus.OK);
	}

	@GetMapping("/")
	public String mainP() {

		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		return "Main Controller : " + name +" \n"+"role ="+role;
	}
}
