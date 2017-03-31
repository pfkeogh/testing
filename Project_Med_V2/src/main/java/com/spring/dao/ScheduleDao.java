package com.spring.dao;

import java.util.List;

import com.spring.pojo.Schedule;


public interface ScheduleDao {
	void saveOrUpdate(Schedule doc);
	void delete(int id);
	List<Schedule> list();
	Schedule load(int id);
}
