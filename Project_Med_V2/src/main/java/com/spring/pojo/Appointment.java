package com.spring.pojo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="med_appointment")
public class Appointment {
	@Id
	@GeneratedValue
	int id;
	String time;
	int duration;
	@OneToOne
    @JoinColumn(name="doc_id") 
	Doctor doctor;
	@OneToOne 
    @JoinColumn(name="patient_id") 
	User user;
	
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", time=" + time + ", duration=" + duration + "]";
	}
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Appointment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}

