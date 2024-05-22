package com.tripshot.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoDto {
	private  String userId;
	private  String name;
	private  String nickname;
	private  String email;
	private  String profileImage;
}
