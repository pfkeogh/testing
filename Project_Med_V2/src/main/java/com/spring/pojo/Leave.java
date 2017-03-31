package com.spring.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name="med_leave")
public class Leave {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String startDate;
	@NotNull
	private String endDate;
	private boolean approved;
	
	
	@Override
	public String toString() {
		return startDate+" - "+endDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public boolean isApproved() {
		return approved;
	}


	public void setApproved(boolean approved) {
		this.approved = approved;
	}


	public Leave() {
		super();
	}
	
	
}
