package com.tripshot.global.security.filter;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.tripshot.global.util.JWTUtil;
import com.tripshot.user.model.CustomUserDetails;
import com.tripshot.user.model.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//request에서 Authorization 헤더를 찾음
        String authorization= request.getHeader("Authorization");

		//Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.debug("*****{}이 없어서 다음 filter로 넘김",authorization);
            filterChain.doFilter(request, response);
            
            //조건이 해당되면 메소드 종료 (필수)
            return;
        }
			
		//Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];
        try {
            if (jwtUtil.isExpired(token)) {
                log.debug("토큰이 만료됨: {}", token);
                filterChain.doFilter(request, response);
                return;
            }

            String username = jwtUtil.getUsername(token);
            String role = jwtUtil.getRole(token);
            Long id = jwtUtil.getId(token);
            log.info("username={}, role={}, id={}", username, role, id);

            User user = new User();
            user.setUserId(username);
            user.setPassword("temppassword");
            user.setRole(role);
            user.setId(id);

            CustomUserDetails customUserDetails = new CustomUserDetails(user);

            Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (Exception e) {
            log.debug("토큰 처리 중 오류 발생: {}", e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }
}