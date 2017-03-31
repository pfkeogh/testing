package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.pojo.Appointment;
import com.spring.pojo.Review;
import com.spring.pojo.User;
import com.spring.service.AppointmentService;
import com.spring.service.ReviewService;
import com.spring.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private AppointmentService apptService;
	
	private final static Logger log = Logger.getLogger(HomeController.class.getName());
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String processLogin(Model model, User user, HttpServletRequest request) {
		log.info("User logging:"+user);
		User realUser = userService.validate(user.getEmail());
		System.out.println("real user: "+realUser);
		System.out.println("user: "+user);
		if(realUser!=null){
			if(realUser.getEmail().equals(user.getEmail())&& realUser.getPassword().equals(user.getPassword())){
				request.getSession().setAttribute("user", realUser);
				log.info("User login valid");
				return "redirect:/user";
			}
		}
		log.info("User login invalid");
		model.addAttribute("error","Incorrect Login");
		return "login";
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String getuser(Model model, HttpServletRequest request){
		log.info("User home page");
		User user = (User)request.getSession().getAttribute("user");
		model.addAttribute("apptlist", apptService.list("user", user.getId()));
		return "user";
	}
	
	//user input via request params
	@RequestMapping(value="/thisUser", method=RequestMethod.GET)
	public String showThisUser(@RequestParam("user") long user,Model model) {
		log.info("Specific user page");
		model.addAttribute("user", user);
		return "user";
	}
	//user resource from url path 
	@RequestMapping(value="/user/{user}", method=RequestMethod.GET)
	public String userResource(@PathVariable("user") String user, Model model) {
		log.info("User home "+user);
		model.addAttribute("user",user);
		return "user";
	}
	
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegistration(Model model, @Valid User user, Errors errors, HttpServletRequest request) {
		log.info("User registering");
		if (errors.hasErrors()) {
			return "register";
		}
		System.out.println("ABOUT TO SAVE USER");
		System.out.println(user);
		userService.saveOrUpdate(user);
		System.out.println("SAVED USER");
		request.getSession().setAttribute("user", user);
		model.addAttribute("user",user);
		log.info("User "+user.toString()+" registered");
		return "redirect:/user";
	}
	@RequestMapping("/user/cancel/{id}")
	public String cancel(@PathVariable String id, Model model) {
		apptService.delete(Integer.parseInt(id));
		model.addAttribute("feedback", "Appointment cancelled");
		log.info("User cancelling Appointment #:"+id);
		return "redirect:/user";
	}
	
	@RequestMapping("/user/review/{id}")
	public String review(@PathVariable String id, Model model){
		model.addAttribute("review", new Review());
		log.info("User creating review");
		return "review";
	}
	@RequestMapping(value="/user/review/{id}", method=RequestMethod.POST)
	public String submitReview(@PathVariable String id, @Valid Review review, Errors errors, Model model){
		log.info("User submitting review");
		if(errors.hasErrors()){
			log.info("Review invalid");
			model.addAttribute("feedback", "Problem with submission");
			return "review";
		}
		Appointment appointment = (Appointment) apptService.load(Integer.parseInt(id));
		review.setAppointment(appointment);
		reviewService.saveOrUpdate(review);
		model.addAttribute("feedback", "Thank you for your feedback");
		log.info("Review valid");
		return "redirect:/user";
	}
	
}
