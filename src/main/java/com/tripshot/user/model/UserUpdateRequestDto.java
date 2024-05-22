package com.tripshot.user.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
	private  Long id;
	private  String nickname;
	private  String email;
}
