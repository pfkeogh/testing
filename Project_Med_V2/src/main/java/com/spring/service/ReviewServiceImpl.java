package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dao.ReviewDao;
import com.spring.pojo.Review;

@Repository
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewDao reviewDoa;
	
	public void saveOrUpdate(Review p){
		reviewDoa.saveOrUpdate(p);
	}
	public void delete(int id){
		reviewDoa.delete(id);
	}
	public Review load(int id){
		return reviewDoa.load(id);
	}
	public Review loadApt(int id){
		return reviewDoa.loadApt(id);
	}
	public List<Review> list(){
		return reviewDoa.list();
	}
}
