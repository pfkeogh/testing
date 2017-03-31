package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dao.ScheduleDao;
import com.spring.pojo.Schedule;

@Repository
public class ScheduleServiceImpl implements ScheduleService{
	
	@Autowired
	ScheduleDao scheduleDoa;
	
	public void saveOrUpdate(Schedule p){
		scheduleDoa.saveOrUpdate(p);
	}
	public void delete(int id){
		scheduleDoa.delete(id);
	}
	public Schedule load(int id){
		return scheduleDoa.load(id);
	}
	public List<Schedule> list(){
		return scheduleDoa.list();
	}
}
