package com.tripshot.global.security.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripshot.global.util.JWTUtil;
import com.tripshot.user.model.CustomUserDetails;
import com.tripshot.user.model.LoginDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	// JWTUtil 주입
	private final JWTUtil jwtUtil;
	
	private Long TOKEN_EXPIRED = 86400000L;
    
	public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		LoginDto loginDto = new LoginDto();
		try {
		    ObjectMapper objectMapper = new ObjectMapper();
		    ServletInputStream inputStream = request.getInputStream();
		    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		    loginDto = objectMapper.readValue(messageBody, LoginDto.class);

		} catch (IOException e) {
		    throw new RuntimeException(e);
		}

		String userId = loginDto.getUserId();
		String password = loginDto.getPassword();
		
		
		// 클라이언트 요청에서 username, password 추출
//		String userId = obtainUsername(request);
//		String password = obtainPassword(request);

		log.info("userId={}",userId);
		// 스프링 시큐리티에서 username과 password를 검증하기 위해서는 token에 담아야 함
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, password, null);

		// token에 담은 검증을 위한 AuthenticationManager로 전달
		return authenticationManager.authenticate(authToken);
	}

	
	// 로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) {
		// UserDetailsS
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		log.info("로그인이 성공한순간에 user는...?{}",customUserDetails.getUser());
		// userId
		String userId = customUserDetails.getUsername();
		//TODO 보안상 문제가 있을 듯 나중에 리팩토링!!
		Long id = customUserDetails.getUser().getId();
		log.info("id 로그인할때 있나요...?===={}",id);

		// 권한 가져오기
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
		GrantedAuthority auth = iterator.next();
		String role = auth.getAuthority();

		// 토큰 생성
		String token = jwtUtil.createJwt(userId, role, id, TOKEN_EXPIRED);
		response.addHeader("Authorization", "Bearer " + token);

	}

	// 로그인 실패시 실행하는 메소드
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,	AuthenticationException failed) {
		// 로그인 실패시 401 응답 코드 반환
		response.setStatus(401);

	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		return request.getParameter("userId"); // 기본적으로 "username" 파라미터를 사용하여 ID를 가져옴
	}

}
