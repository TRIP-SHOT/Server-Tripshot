package com.tripshot.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tripshot.user.model.JoinDto;
import com.tripshot.user.model.User;

@Mapper
public interface UserMapper {
	int existByUserId(String userId);
	int insertUser(JoinDto joinDto);
	User findByUserId(String userId);
}
