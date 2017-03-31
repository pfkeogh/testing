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

import com.spring.pojo.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao{
	@Autowired
	private SessionFactory sessionFactory;
	
    public ReviewDaoImpl() {
		super();
	}
	public ReviewDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Transactional
	public void saveOrUpdate(Review d){
		sessionFactory.getCurrentSession().saveOrUpdate(d);
	}
	
	@Transactional
	public void delete(int id){
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Review.class, id));
	}
	
	@Transactional
	public Review get(int id){
		String hql = "from Review where id=" +id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Review> ReviewList = (List<Review>) query.list();
         
        if (ReviewList != null && !ReviewList.isEmpty()) {
            return ReviewList.get(0);
        }
         
        return null;
	}
	@Transactional
	public Review load(int id){
		String hql = "from Review where id=" +id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Review> ReviewList = (List<Review>) query.list();
         
        if (ReviewList != null && !ReviewList.isEmpty()) {
            return ReviewList.get(0);
        }
        return null;
	}
	@Transactional
	public Review loadApt(int id){
		String hql = "from Review where apt_id=" +id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Review> ReviewList = (List<Review>) query.list();
         
        if (ReviewList != null && !ReviewList.isEmpty()) {
            return ReviewList.get(0);
        }
        return null;
	}
	@Transactional
	public List<Review> list(){
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Review> ReviewList = (List<Review>) sessionFactory.getCurrentSession()
                .createCriteria(Review.class).list();
		if (ReviewList != null && !ReviewList.isEmpty()) {
            return ReviewList;
        }
		return null;
	}
}
