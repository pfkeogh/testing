package com.spring.service;

import java.util.ArrayList;

import com.spring.pojo.User;

public interface UserService {
	void saveOrUpdate(User u);
	ArrayList<User> list();
	User load(int id);
	User validate(String email);
	void delete(int id);
	
}
