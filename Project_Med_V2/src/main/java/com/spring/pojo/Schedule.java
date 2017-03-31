package com.spring.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name="med_schedule")
public class Schedule {
	@Id
	@GeneratedValue
	int id;
	@ElementCollection
	@OrderColumn(name="days_indx")
	@Enumerated(EnumType.STRING)
	Day[] days;
	@NotNull
	String startDate;
	@NotNull
	String endDate;
	
	
	public Schedule() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Day[] getDays() {
		return days;
	}
	public void setDays(Day[] days) {
		this.days = days;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
