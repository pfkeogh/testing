package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dao.DoctorDao;
import com.spring.pojo.Doctor;

@Repository
public class DoctorServiceImpl implements DoctorService{
	
	@Autowired
	DoctorDao doctorDoa;
	
	public void saveOrUpdate(Doctor p){
		doctorDoa.saveOrUpdate(p);
	}
	public void delete(int id){
		doctorDoa.delete(id);
	}
	public Doctor load(int id){
		return doctorDoa.load(id);
	}
	public Doctor validate(String email){
		return doctorDoa.validate(email);
	}
	public List<Doctor> list(){
		return doctorDoa.list();
	}
}
