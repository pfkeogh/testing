package com.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.pojo.Doctor;

@Repository
public class DoctorDaoImpl implements DoctorDao{
	@Autowired
	private SessionFactory sessionFactory;
	
    public DoctorDaoImpl() {
		super();
	}
	public DoctorDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Transactional
	public void saveOrUpdate(Doctor d){
		sessionFactory.getCurrentSession().saveOrUpdate(d);
	}
	
	@Transactional
	public void delete(int id){
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Doctor.class, id));
	}
	
	@Transactional
	public Doctor load(int id){
		String hql = "from Doctor where id=" +id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Doctor> DoctorList = (List<Doctor>) query.list();
         
        if (DoctorList != null && !DoctorList.isEmpty()) {
            return DoctorList.get(0);
        }
        return null;
	}
	
	@Transactional
	public Doctor validate(String email){
		String hql = "from Doctor where email='" +email+"'";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Doctor> DoctorList = (List<Doctor>) query.list();
         
        if (DoctorList != null && !DoctorList.isEmpty()) {
            return DoctorList.get(0);
        }
		return null;
	}
	@Transactional
	public List<Doctor> list(){
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Doctor> DoctorList = (List<Doctor>) sessionFactory.getCurrentSession()
			.createQuery("From Doctor").list();
		if (DoctorList != null && !DoctorList.isEmpty()) {
            return DoctorList;
        }
		return null;
	}
}
