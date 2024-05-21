package com.tripshot.user.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@DeleteMapping("/logout")
	public ResponseEntity<ApiResponse<String>> logout(){
		
		return new ResponseEntity(new ApiResponse(HttpStatus.OK,"로그아웃 성공","로그아웃 처리 되었습니다."),HttpStatus.OK);
	}
}
