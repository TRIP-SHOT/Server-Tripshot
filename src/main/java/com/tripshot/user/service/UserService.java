package com.tripshot.user.service;

import com.tripshot.user.model.JoinDto;
import com.tripshot.user.model.LoginResponseDTO;
import com.tripshot.user.model.UserInfoDto;
import com.tripshot.user.model.UserUpdateRequestDto;

public interface UserService {
	Boolean join(JoinDto joinDto);

	LoginResponseDTO findNicknameByUsename(String userId);

	UserInfoDto getUserInfo(Long userPk);

	int updateUser(UserUpdateRequestDto user);

	int withdraw(Long id);
}
