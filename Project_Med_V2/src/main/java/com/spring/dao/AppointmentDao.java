package com.spring.dao;

import java.util.List;

import com.spring.pojo.Appointment;


public interface AppointmentDao {
	void saveOrUpdate(Appointment doc);
	void delete(int id);
	List<Appointment> list();
	List<Appointment> list(String user, int id);
	Appointment load(int id);
	
}
