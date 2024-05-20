package com.tripshot.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinDto {
	private String userId;
	private String password;
	private String name;
	private String nickname;
	private String email;
	private String role;
}
