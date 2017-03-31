package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dao.AppointmentDao;
import com.spring.pojo.Appointment;
@Repository
public class AppointmentServiceImpl implements AppointmentService{
	@Autowired
	AppointmentDao appointmentDoa;
	
	public void saveOrUpdate(Appointment p){
		appointmentDoa.saveOrUpdate(p);
	}
	public void delete(int id){
		appointmentDoa.delete(id);
	}
	public Appointment load(int id){
		return appointmentDoa.load(id);
	}
	public List<Appointment> list(){
		return appointmentDoa.list();
	}
	public List<Appointment> list(String user, int id) {
		return appointmentDoa.list(user, id);
	}
}
