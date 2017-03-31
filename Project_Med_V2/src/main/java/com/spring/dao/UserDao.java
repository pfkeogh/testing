package com.spring.dao;

import java.util.List;

import com.spring.pojo.User;

public interface UserDao {
	void saveOrUpdate(User u);
	List<User> list();
	void delete(int id);
	User load(int id);
	User validate(String email);
}
