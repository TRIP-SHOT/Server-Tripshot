package com.tripshot.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tripshot.user.model.JoinDto;
import com.tripshot.user.model.LoginResponseDTO;
import com.tripshot.user.model.User;
import com.tripshot.user.model.UserInfoDto;
import com.tripshot.user.model.UserUpdateRequestDto;

@Mapper
public interface UserMapper {
	int existByUserId(String userId);
	int insertUser(JoinDto joinDto);
	User findByUserId(String userId);
	LoginResponseDTO findNicknameByUsename(String userId);
	UserInfoDto findUserById(Long userPk);
	String findImageKeyByUserId(Long id);
	int updateUser(UserUpdateRequestDto user);
	int withdraw(Long id);
}
