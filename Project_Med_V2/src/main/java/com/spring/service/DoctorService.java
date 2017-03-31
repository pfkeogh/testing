package com.spring.service;

import java.util.List;

import com.spring.pojo.Doctor;

public interface DoctorService {
	void saveOrUpdate(Doctor doc);
	void delete(int id);
	List<Doctor> list();
	Doctor load(int id);
	Doctor validate(String email);
}
