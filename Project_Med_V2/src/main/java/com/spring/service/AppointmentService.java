package com.spring.service;

import java.util.List;

import com.spring.pojo.Appointment;


public interface AppointmentService {
	void saveOrUpdate(Appointment doc);
	void delete(int id);
	List<Appointment> list();
	List<Appointment> list(String user,int id);
	Appointment load(int id);
}
