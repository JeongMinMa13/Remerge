package com.kh.reMerge.user.model.service;

import java.util.ArrayList;

import com.kh.reMerge.user.model.vo.User;

public interface UserService {

	int insertUser(User u);

	int checkId(String checkId);

	User loginUser(User u);

	// 메시지용 - 중구
	ArrayList<User> getAllUsers();
}
