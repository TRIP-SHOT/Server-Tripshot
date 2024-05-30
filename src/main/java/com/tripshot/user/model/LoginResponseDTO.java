package com.tripshot.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class LoginResponseDTO {
	private final Long userId;
	private final String nickName; 
}
