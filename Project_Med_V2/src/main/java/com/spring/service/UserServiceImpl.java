package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.dao.UserDao;
import com.spring.pojo.User;

@Component
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	public void saveOrUpdate(User u){
		userDao.saveOrUpdate(u);
	}

	public ArrayList<User> list(){
		return (ArrayList<User>)userDao.list();
	}
	public User load(int id){
		return userDao.load(id);
	}
	public User validate(String email){
		return userDao.validate(email);
	}
	public void delete(int id){
		userDao.delete(id);
	}
}
