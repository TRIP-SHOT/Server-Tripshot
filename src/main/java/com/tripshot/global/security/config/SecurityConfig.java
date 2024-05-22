package com.tripshot.global.security.config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

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
		http
				.cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration configuration = new CorsConfiguration();
						
						configuration.addAllowedOriginPattern("*");
//						configuration.setAllowedOrigins(Collections.singletonList("*"));
						configuration.setAllowedMethods(Collections.singletonList("*"));
						configuration.setAllowCredentials(true);
						configuration.setAllowedHeaders(Collections.singletonList("*"));
						configuration.setMaxAge(3600L);
						configuration.setExposedHeaders(Collections.singletonList("Authorization"));
						return configuration;
					}
				})));

		// csrf disable -> session을 유지하지 않으므로 방어할 필요 x
		http.csrf((auth) -> auth.disable());

		// From 로그인 방식 disable -> jwt를 사용함
		http.formLogin((auth) -> auth.disable());

		// http basic 인증 방식 disable ->jwt를 사용함
		http.httpBasic((auth) -> auth.disable());

		// 경로별 인가 작업
		http.authorizeHttpRequests((auth) -> auth
//				.anyRequest().permitAll());
				//TODO
				.requestMatchers("/map/**").permitAll()
				.requestMatchers(HttpMethod.POST,"/login").permitAll()
				.requestMatchers(HttpMethod.POST,"/join").permitAll()
				.requestMatchers("/swagger-ui/index.html#").permitAll()
						.requestMatchers("/boards/hearts").authenticated()
				.requestMatchers(HttpMethod.GET,"/boards/**").permitAll()
				.anyRequest().authenticated())
				.sessionManagement((session) -> session
		            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
				//TODO
//				.requestMatchers("/boards").hasRole("USER").anyRequest().authenticated());
        http.addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
		
		// 필터 추가 LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
		http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),	UsernamePasswordAuthenticationFilter.class);
		//https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html#security-matchers

		// 세션 설정 -> jwt방식은 stateless상태를 유지한다!!
		http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
}