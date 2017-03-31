package com.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.pojo.User;
import com.spring.pojo.User;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	SessionFactory sessionFactory;
	
	public UserDaoImpl() {
        
    }
     
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	
	@Transactional
	public User getUser(String email){
		String hql = "from User where email='" + email+"'";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<User> userList = (List<User>) query.list();
         
        if (userList != null && !userList.isEmpty()) {
            return userList.get(0);
        }
         
        return null;
	}
	
	@Transactional
	public void saveOrUpdate(User u){
		sessionFactory.getCurrentSession().saveOrUpdate(u);
	}
	
	@Transactional
	public void delete(int id){
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(User.class, id));
	}
	@Transactional
	public User load(int id){
		String hql = "from User where id=" +id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<User> UserList = (List<User>) query.list();
         
        if (UserList != null && !UserList.isEmpty()) {
            return UserList.get(0);
        }
        return null;
	}
	@Transactional
	public User validate(String email){
		String hql = "from User where email='" +email+"'";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<User> UserList = (List<User>) query.list();
         
        if (UserList != null && !UserList.isEmpty()) {
            return UserList.get(0);
        }
        return null;
	}
	@Transactional
	public List<User> list(){
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<User> UserList = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class).list();
		if (UserList != null && !UserList.isEmpty()) {
            return UserList;
        }
		return null;
	}
}
