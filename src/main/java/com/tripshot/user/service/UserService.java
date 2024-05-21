package com.tripshot.user.service;

import com.tripshot.user.model.JoinDto;

public interface UserService {
	Boolean join(JoinDto joinDto);

	Long findUserIdByUsername(String username);
}
