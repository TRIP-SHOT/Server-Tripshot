package com.tripshot.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
	private  Long id;
	private  String userId;
	private  String password;
	private  String name;
	private  String nickname;
	private  String email;
	private  String role;
	private  String profileImage;
}
