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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tripshot.global.ApiResponse;
import com.tripshot.user.model.CustomUserDetails;
import com.tripshot.user.model.JoinDto;
import com.tripshot.user.model.LoginDto;
import com.tripshot.user.model.LoginResponseDTO;
import com.tripshot.user.model.User;
import com.tripshot.user.model.UserInfoDto;
import com.tripshot.user.model.UserUpdateRequestDto;
import com.tripshot.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
	private final UserService service;

	@PostMapping("/join")
	public ResponseEntity<ApiResponse<String>> join(@RequestBody JoinDto joinDto) {
		service.join(joinDto);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "회원가입 성공", "회원가입에 성공하셨습니다."), HttpStatus.OK);
	}
	
	//TODO 이거 왜안됌
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<?>> login(HttpServletRequest request) throws Exception{
		
		String token = (String) request.getAttribute("token");
        User user = (User)request.getAttribute("user");
        LoginResponseDTO response = new LoginResponseDTO(user.getId(),user.getNickname());
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "로그인 성공", response), HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ApiResponse<UserInfoDto>> userInfo(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Long userPk = userDetails.getUser().getId();
	
		UserInfoDto response = service.getUserInfo(userPk);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "회원정보 조회성공", response), HttpStatus.OK);
	}
	
	@PutMapping("/users")
	public ResponseEntity<ApiResponse<UserInfoDto>> updateUser(@RequestBody UserUpdateRequestDto user){
		//userid를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Long userPk = userDetails.getUser().getId();
		
		user.setId(userPk);
		int result = service.updateUser(user);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK, "회원정보 수정 성공", "회원정보 수정 성공"), HttpStatus.OK);
	} 
	
	@DeleteMapping("/users")
	public ResponseEntity<ApiResponse<String>> logout(){
		//userid를 가져옴
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Long userPk = userDetails.getUser().getId();
		//TODO 회원 탈퇴
		service.withdraw(userPk);
		return new ResponseEntity(new ApiResponse(HttpStatus.OK,"회원 탈퇴 성공","회원 탈퇴 성공"),HttpStatus.OK);
	}
}
