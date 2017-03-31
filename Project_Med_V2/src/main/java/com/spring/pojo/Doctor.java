package com.spring.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="med_doctor")
public class Doctor {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	@Size(min=3, max=16)
	private String email;
	@NotNull
	@Size(min=1, max=16)
	private String firstName;
	@NotNull
	@Size(min=1, max=16)
	private String lastName;
	@NotNull
	@Size(min=1, max=16)
	private String password;
	@NotNull
	@Size(min=4, max=6)
	private String gender;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Speciality speciality;
	@OneToOne(cascade = CascadeType.ALL)
	private Schedule schedule;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="leave_id")
	private Leave leave;
	
	
	public Doctor() {
		super();
		this.leave = new Leave();
		this.schedule = new Schedule();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((speciality == null) ? 0 : speciality.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (speciality != other.speciality)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", gender=" + gender + ", speciality=" + speciality + "]";
	}
	public Leave getLeave() {
		return leave;
	}
	public void setLeave(Leave leave) {
		this.leave = leave;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Speciality getSpeciality() {
		return speciality;
	}
	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}
