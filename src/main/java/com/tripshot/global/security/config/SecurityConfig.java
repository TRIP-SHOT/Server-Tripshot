package com.tripshot.global.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tripshot.global.security.filter.JWTFilter;
import com.tripshot.global.security.filter.LoginFilter;
import com.tripshot.global.util.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final AuthenticationConfiguration authenticationConfiguration;

	// JWTUtil 주입
	private final JWTUtil jwtUtil;
	public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
		
		this.authenticationConfiguration = authenticationConfiguration;
		
		this.jwtUtil = jwtUtil;
	}

	// AuthenticationManager Bean 등록
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// csrf disable -> session을 유지하지 않으므로 방어할 필요 x
		http.csrf((auth) -> auth.disable());

		// From 로그인 방식 disable -> jwt를 사용함
		http.formLogin((auth) -> auth.disable());

		// http basic 인증 방식 disable ->jwt를 사용함
		http.httpBasic((auth) -> auth.disable());

		// 경로별 인가 작업
		http.authorizeHttpRequests((auth) -> auth.requestMatchers("/**").permitAll());
//				.requestMatchers("/boards").hasRole("USER").anyRequest().authenticated());

        http
        .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
		
		// 필터 추가 LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
		http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),	UsernamePasswordAuthenticationFilter.class);

		// 세션 설정 -> jwt방식은 stateless상태를 유지한다!!
		http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
}