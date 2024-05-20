package com.tripshot.global.security.filter;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tripshot.global.util.JWTUtil;
import com.tripshot.user.model.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	// JWTUtil 주입
	private final JWTUtil jwtUtil;

	public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// 클라이언트 요청에서 username, password 추출
		String userId = obtainUsername(request);
		String password = obtainPassword(request);

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

		// userId
		String userId = customUserDetails.getUsername();

		// 권한 가져오기
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
		GrantedAuthority auth = iterator.next();
		String role = auth.getAuthority();

		// 토큰 생성
		String token = jwtUtil.createJwt(userId, role, 60 * 60 * 60L);
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
