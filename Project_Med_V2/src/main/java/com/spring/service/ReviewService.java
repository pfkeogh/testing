package com.spring.service;

import java.util.List;

import com.spring.pojo.Review;

public interface ReviewService {
	void saveOrUpdate(Review doc);
	void delete(int id);
	List<Review> list();
	Review load(int id);
	Review loadApt(int id);
}
