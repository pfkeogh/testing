package com.spring.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.pojo.Appointment;
import com.spring.pojo.Day;
import com.spring.pojo.Doctor;
import com.spring.pojo.Leave;
import com.spring.pojo.Review;
import com.spring.pojo.Schedule;
import com.spring.pojo.Speciality;
import com.spring.pojo.User;
import com.spring.service.AppointmentService;
import com.spring.service.DoctorService;
import com.spring.service.ReviewService;


@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	DoctorService doctorService;
	@Autowired
	AppointmentService apptService;
	@Autowired
	ReviewService reviewService;
	
	private final static Logger log = Logger.getLogger(DoctorController.class.getName());
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String processLogin(Model model, User user, HttpServletRequest request) {
		log.info("Doctor logging in");
		Doctor realDoc = doctorService.validate(user.getEmail());
		System.out.println("real user: "+realDoc);
		System.out.println("user: "+user);
		if(realDoc!=null){
			if(realDoc.getEmail().equals(user.getEmail())&& realDoc.getPassword().equals(user.getPassword())){
				model.addAttribute("user",user);
				request.getSession().setAttribute("doctor", realDoc);
				log.info("Doctor valid");
				return "doctor";
			}
		}
		log.info("Doctor invalid");
		model.addAttribute("error","Incorrect Login");
		return "login";
	}
	@RequestMapping("/schedule")
	public String schedule(Model model) {
		log.info("Doctor setting schedule");
		model.addAttribute("days",Arrays.asList(Day.values()));
		model.addAttribute("schedule", new Schedule());
		return "doctor_schedule";
	}
	@RequestMapping(value="/schedule", method=RequestMethod.POST)
	public String newSchedule(@Valid Schedule schedule, Errors errors, Model model, HttpServletRequest request){
		log.info("");
		if (errors.hasErrors()) {
			model.addAttribute("feedback", "Request failed");
			return "doctor_schedule";
		}
		System.out.println(schedule);
		Doctor doctor = (Doctor) request.getSession().getAttribute("doctor");
		doctor.setSchedule(schedule);
		doctorService.saveOrUpdate(doctor);
		model.addAttribute("feedback", "Schedule Set");
		return "redirect:/doctor";
	}
	@RequestMapping("/leave")
	public String leave(Model model) {
		log.info("Doctor editing leave");
		model.addAttribute("leave", new Leave());
		return "doctor_leave";
	}
	@RequestMapping(value="/leave", method=RequestMethod.POST)
	public String newLeave(@Valid Leave leave, Errors errors, Model model, HttpServletRequest request){
		if (errors.hasErrors()) {
			model.addAttribute("feedback", "Request failed");
			return "doctor_leave";
		}
		System.out.println(leave);
		Doctor doctor = (Doctor) request.getSession().getAttribute("doctor");
		doctor.setLeave(leave);
		doctorService.saveOrUpdate(doctor);
		model.addAttribute("feedback", "Leave requested");
		log.info("Leave Requested");
		return "redirect:/doctor";
	}
	@RequestMapping("/appts")
	public String appts(Model model, HttpServletRequest request) {
		log.info("Doctor view Appointments");
		Doctor doctor = (Doctor)request.getSession().getAttribute("doctor");
		model.addAttribute("apptlist", apptService.list("doctor", doctor.getId()));
		return "doctor_appts";
	}
	@RequestMapping("/cancel/{id}")
	public String cancel(@PathVariable String id, Model model) {
		log.info("Doctor viewing appointments");
		int aptId = Integer.parseInt(id);
		apptService.delete(aptId);
		model.addAttribute("feedback", "Appointment cancelled");
		return "redirect:/doctor/appts";
	}
	@RequestMapping("/review/{id}")
	public String review(@PathVariable String id, Model model) {
		log.info("Doctor viewing review #:" +id);
		int rId = Integer.parseInt(id);
		Review review = (Review) reviewService.loadApt(rId);
		model.addAttribute("review", review);
		return "doctor_review";
	}
	
}
