package com.spring.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.pojo.Appointment;
import com.spring.pojo.Doctor;

@Repository
public class AppointmentDaoImpl implements AppointmentDao{
	@Autowired
	private SessionFactory sessionFactory;
	
    public AppointmentDaoImpl() {
		super();
	}
	public AppointmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Transactional
	public void saveOrUpdate(Appointment d){
		sessionFactory.getCurrentSession().saveOrUpdate(d);
	}
	
	@Transactional
	public void delete(int id){
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Appointment.class, id));
	}
	
	@Transactional
	public Appointment get(int id){
		String hql = "from Appointment where id=" +id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Appointment> AppointmentList = (List<Appointment>) query.list();
         
        if (AppointmentList != null && !AppointmentList.isEmpty()) {
            return AppointmentList.get(0);
        }
         
        return null;
	}
	@Transactional
	public Appointment load(int id){
		String hql = "from Appointment where id=" +id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Appointment> AppointmentList = (List<Appointment>) query.list();
         
        if (AppointmentList != null && !AppointmentList.isEmpty()) {
            return AppointmentList.get(0);
        }
        return null;
	}
	
	@Transactional
	public List<Appointment> list(){
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Appointment> AppointmentList = (List<Appointment>) sessionFactory.getCurrentSession()
                .createCriteria(Appointment.class).list();
		if (AppointmentList != null && !AppointmentList.isEmpty()) {
            return AppointmentList;
        }
		return null;
	}
	@Transactional
	public List<Appointment> list(String user,int id) {
		@SuppressWarnings({ "deprecation", "unchecked" })
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Appointment.class);
		cr.add(Restrictions.eq(user+".id", id));
		List<Appointment> AppointmentList = (List<Appointment>)cr.list();
		if (AppointmentList != null && !AppointmentList.isEmpty()) {
            return AppointmentList;
        }
		return null;
	}
}
