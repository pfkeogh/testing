package com.spring.service;

import java.util.List;

import com.spring.pojo.Schedule;


public interface ScheduleService {
	void saveOrUpdate(Schedule doc);
	void delete(int id);
	List<Schedule> list();
	Schedule load(int id);
}
