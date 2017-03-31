package com.spring.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.bean.SearchParam;
import com.spring.pojo.Appointment;
import com.spring.pojo.Day;
import com.spring.pojo.Doctor;
import com.spring.pojo.Leave;
import com.spring.pojo.Schedule;
import com.spring.pojo.Speciality;
import com.spring.pojo.User;
import com.spring.service.AppointmentService;
import com.spring.service.DoctorService;
import com.spring.service.UserService;

import javafx.util.converter.LocalDateStringConverter;

@Controller
public class HomeController {
	@Autowired
	DoctorService doctorService;
	@Autowired
	UserService userService;
	@Autowired
	AppointmentService apptService;
	
	private final static Logger log = Logger.getLogger(HomeController.class.getName());
	
	@RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
	public String home(Model model){
		model.addAttribute("searchParam", new SearchParam());
		model.addAttribute("specialities", Arrays.asList(Speciality.values()));
		log.info("HomePage Visited");
		return "home";
	}
	@RequestMapping(value={"/","/home"}, method=RequestMethod.POST)
	public String homeSearch(Model model, SearchParam searchParam ){
		model.addAttribute("specialities", Arrays.asList(Speciality.values()));
		model.addAttribute("search", searchParam.getSpeciality());
		model.addAttribute("doctors", doctorService.list());
		log.info("Searching for "+searchParam.getSpeciality());
		return "home";
	}
	
	@RequestMapping(value="/logout")
	public String processLogout(HttpServletRequest request, Model model) {
		request.getSession().invalidate();
		model.addAttribute("searchParam", new SearchParam());
		log.info("Logout - session invalidated");
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("user",new User());
		log.info("User login");
		return "login";
	}
	@RequestMapping("/doctor/login")
	public String doctorLogin(Model model, HttpServletRequest request) {
		Doctor doctor = (Doctor)request.getSession().getAttribute("doctor");
		if(doctor==null){
			model.addAttribute("user",new User());
			log.info("Doctor login");
			return "login";
		}
		return "doctor";
	}
	
	
	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("user",new User());
		log.info("User registration");
		return "register";
	}
	
	@RequestMapping(value={"/admin"}, method=RequestMethod.GET)
	public String admin(){
		log.info("Admin");
		return "admin";
	}
	
	@RequestMapping("/doctors")
	public String doctors(Model model){
		model.addAttribute("doctors", doctorService.list());
		log.info("Admin viewing doctors");
		return "admin_doctor";
	}
	
	@RequestMapping("/doctor")
	public String doctor(){
		log.info("doctor homepage");
		return "doctor";
	}
	
	@RequestMapping("/schedule/{id}")
	public String viewSchedule(@PathVariable String id, Model model, HttpServletRequest request){
		Doctor doctor = doctorService.load(Integer.parseInt(id));
		log.info("Displaying schedule of doctor - "+doctor.toString());
		model.addAttribute("doctor", doctor);
		//get schedule from doctor and convert to date
		Schedule sched = doctor.getSchedule();
		List<Appointment> appts = apptService.list("doctor", Integer.parseInt(id));
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME; 
		LocalTime schedStart = null;
		LocalTime schedEnd = null;
		LocalDate today = LocalDate.now();
		String week = (String)request.getParameter("week");
		if(week!=null){
			int i = Integer.parseInt(week);
			System.out.println(week);
			today=today.plusDays(i*7);
		}
		//2D array representing all possible appointments for each day of week
		//check if appointments exists
		ArrayList<String>[] schedule = new ArrayList[7];
		if(sched.getStartDate()!=null){
			schedStart = LocalTime.parse(sched.getStartDate(), formatter);
			schedEnd = LocalTime.parse(sched.getEndDate(), formatter);
	 		formatter = DateTimeFormatter.ofPattern("hh:mm a");
	 		DateTimeFormatter aptFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
	 		for(int i=0; i<7; i++){
	 			ArrayList<String> times = new ArrayList<String>();
	 			LocalTime temp = schedStart;
		 		while(temp.compareTo(schedEnd)<0){
		 			boolean flag=true;
		 			String compareDay = today.plusDays(i).toString()+" "+temp.format(formatter);
		 			if(appts!=null){
			 			for(Appointment appt : appts){
			 				if(appt.getTime().equals(compareDay)){
			 					flag=false;
			 				}
			 			}
		 			}
		 			if(LocalDateTime.now().isAfter(LocalDateTime.parse(compareDay, aptFormat))){
		 				flag=false;
		 			}
		 			if(flag){
	 					times.add(temp.format(formatter));
	 				}
		 			else{
		 				times.add("");
		 			}
		 			temp = temp.plusMinutes(30);
		 		}
		 		schedule[i]=times;
	 		}
		}
		//create array representing availability on each day based on schedule
		Day[] days = sched.getDays();
		boolean[] available = new boolean[7];
		for(Day day:days){
			available[day.num]=true;
		}
		//get todays date and day of week
		
		formatter = DateTimeFormatter.ofPattern("EEEE");
		String t = today.format(formatter);
		Day day = Day.valueOf(t);
 		
		//get leave and convert to date, then check if that changes availability IF leave approved
		Leave leave = doctor.getLeave();
		LocalDate leaveStart = null;
		LocalDate leaveEnd = null;
		DateTimeFormatter dayWrap = DateTimeFormatter.ofPattern("EEEE");
		if(leave.getStartDate()!=null && leave.isApproved()){
			formatter = DateTimeFormatter.ISO_DATE;
			leaveStart = LocalDate.parse(leave.getStartDate(), formatter);
			leaveEnd = LocalDate.parse(leave.getEndDate(), formatter);
			for(int i=0; i<7; i++){
				System.out.println("_____________________________________________");
				System.out.println(today.plusDays(i)+" "+leaveStart);
				System.out.println(today.plusDays(i).compareTo(leaveStart));
				System.out.println(today.plusDays(i)+" "+leaveEnd);
				System.out.println(today.plusDays(i).compareTo(leaveEnd));
				
				
	 			if(today.plusDays(i).compareTo(leaveStart)>=0 && today.plusDays(i).compareTo(leaveEnd)<=0){
	 				String cur = today.plusDays(i).format(dayWrap);
	 				Day curDay = Day.valueOf(cur);
	 				System.out.println(curDay);
	 				available[curDay.num]=false;
	 				System.out.println("TAKING LEAVE");
	 			}
	 			System.out.println("___________________i:"+i+"__________________________");
	 		}
		}
		
 		//Align list representing days of week to have today as start of list
		List<Day> alignedDays = Arrays.asList(Day.values());
		List<Day> daysList = new ArrayList<Day>(alignedDays);
		while(true){
			Day d = daysList.get(0);
			System.out.println("DAY:"+d);
			if(d.equals(day)){
				break;
			}
			d = daysList.remove(0);
			daysList.add(d);
		}
		alignedDays=daysList;
		request.getSession().setAttribute("userDoc", doctor);
		
		//pass required information to model
		formatter = DateTimeFormatter.ISO_DATE;
		model.addAttribute("formatter", formatter);
		model.addAttribute("today", today);
		model.addAttribute("days", alignedDays);
		model.addAttribute("available",available);
		model.addAttribute("times", schedule);
		log.info("Schedule created and passed to view");
		return "schedule";
	}
	@RequestMapping("/schedule/{id}/{time}")
	public String makeAppt(@PathVariable("id") String id, @PathVariable("time") String time, Model model, HttpServletRequest request){
		log.info("Viewing appointment - doctor:"+id+" time:"+time);
		Doctor doctor = (Doctor)request.getSession().getAttribute("userDoc");
		User user = (User)request.getSession().getAttribute("user");
		if(user==null){
			user = new User();
		}
		System.out.println("time: "+time);
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"); 
		model.addAttribute("doctor", doctor);
		model.addAttribute("user", user);
		model.addAttribute("time", time);
		return "appointment";
	}
	
	@RequestMapping(value="/schedule/{id}/{time}", method=RequestMethod.POST)
	public String confirmAppt(@PathVariable("id") String id, @PathVariable("time") String time, Model model, HttpServletRequest request){
		log.info("Saving appointment - doctor:"+id+" time:"+time);
		User user = (User)request.getSession().getAttribute("user");
		if(user==null){
			model.addAttribute("user", new User());
			model.addAttribute("feedback", "Must be logged in to book");
			model.addAttribute("doctor", (Doctor)request.getSession().getAttribute("userDoc"));
			return "appointment";
		}
		
		Doctor doctor = (Doctor)request.getSession().getAttribute("userDoc");
		user = userService.load(user.getId());
		doctor = doctorService.load(Integer.parseInt(id));
		Appointment appt = new Appointment();
		appt.setTime(time);
		appt.setDuration(30);
		appt.setDoctor(doctor);
		appt.setUser(user);
		apptService.saveOrUpdate(appt);
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("userDoc", null);
		model.addAttribute("feedback", "Appointment booked");
		return "redirect:/user";
	}
	
	@RequestMapping(value="/nextweek/{id}")
	public String nextWeek(@PathVariable String id, HttpServletRequest request, Model model){
		String week = (String)request.getParameter("week");
		log.info("Next week # - "+week);
		if(week==null){
			model.addAttribute("week", 1);//change to param
		}
		else{
			int i = Integer.parseInt(week);
			i++;
			model.addAttribute("week", i);
		}
		return "redirect:/schedule/"+id;
	}
	@RequestMapping(value="/prevweek/{id}")
	public String prevWeek(@PathVariable String id, HttpServletRequest request, Model model){
		
		String week = (String)request.getParameter("week");
		log.info("Prev week # - "+week);
		if(week==null){
			model.addAttribute("week", 1);//change to param
		}
		else{
			int i = Integer.parseInt(week);
			i--;
			model.addAttribute("week", i);
		}
		return "redirect:/schedule/"+id;
	}
}
