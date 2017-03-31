package com.spring.dao;

import java.util.List;

import com.spring.pojo.Doctor;

public interface DoctorDao {
	void saveOrUpdate(Doctor doc);
	void delete(int id);
	List<Doctor> list();
	Doctor load(int id);
	Doctor validate(String email);
}
