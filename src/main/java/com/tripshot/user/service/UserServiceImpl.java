package com.tripshot.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tripshot.user.mapper.UserMapper;
import com.tripshot.user.model.JoinDto;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;//비밀번호를 인코딩하기위함

	@Override
	public Boolean join(JoinDto joinDto) {
		//id조회, 중복되는 경우 생성할 수 없음.
		//TODO 예외를 반환한다.
		int isExists = mapper.existByUserId(joinDto.getUserId());
		if(isExists > 0) {
			return false;
		}
		
		//비밀번호 암호화
		joinDto.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
		
		//insert
		mapper.insertUser(joinDto);
		return true;
	}

}
