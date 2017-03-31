package com.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.pojo.Schedule;

@Repository
public class ScheduleDaoImpl implements ScheduleDao{
	@Autowired
	private SessionFactory sessionFactory;
	
    public ScheduleDaoImpl() {
		super();
	}
	public ScheduleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Transactional
	public void saveOrUpdate(Schedule d){
		sessionFactory.getCurrentSession().saveOrUpdate(d);
	}
	
	@Transactional
	public void delete(int id){
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Schedule.class, id));
	}
	
	@Transactional
	public Schedule get(int id){
		String hql = "from Schedule where id=" +id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Schedule> ScheduleList = (List<Schedule>) query.list();
         
        if (ScheduleList != null && !ScheduleList.isEmpty()) {
            return ScheduleList.get(0);
        }
         
        return null;
	}
	@Transactional
	public Schedule load(int id){
		String hql = "from Schedule where id=" +id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Schedule> ScheduleList = (List<Schedule>) query.list();
         
        if (ScheduleList != null && !ScheduleList.isEmpty()) {
            return ScheduleList.get(0);
        }
        return null;
	}
	
	@Transactional
	public List<Schedule> list(){
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Schedule> ScheduleList = (List<Schedule>) sessionFactory.getCurrentSession()
                .createCriteria(Schedule.class).list();
		if (ScheduleList != null && !ScheduleList.isEmpty()) {
            return ScheduleList;
        }
		return null;
	}
}
