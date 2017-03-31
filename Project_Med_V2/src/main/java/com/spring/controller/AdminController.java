package com.spring.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.pojo.Doctor;
import com.spring.pojo.Doctor;
import com.spring.pojo.Speciality;
import com.spring.pojo.User;
import com.spring.service.DoctorService;
import com.spring.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	UserService userService;
	
	private final static Logger log = Logger.getLogger(AdminController.class.getName()); 
	
	@RequestMapping("/patients")
	public String patients(Model model){
		log.info("Admin viewing patients");
		model.addAttribute("patients", userService.list());
		return "admin_patient";
	}
	@RequestMapping("/newpatient")
	public String newPatient(Model model){
		log.info("Admin creating patient");
		model.addAttribute("user", new User());
		return "register";
	}
	@RequestMapping(value="/newpatient", method=RequestMethod.POST)
	public String newPatient(@Valid User user, Errors errors, Model model){
		log.info("Admin persisting new patient");
		if (errors.hasErrors()) {
			model.addAttribute("feedback", "Unable to register");
			return "register";
		}
		System.out.println(user);
		userService.saveOrUpdate(user);
		model.addAttribute("feedback", user.getFirstName()+" registered");
		model.addAttribute("user",new User());
		log.info("New patient:"+user.toString());
		return "register";
	}
	@RequestMapping(value = "/patients/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable String id, Model model) {
		log.info("Admin editing patient #:"+id);
		User user = userService.load(Integer.parseInt(id));
    	System.out.println("user: "+user);
    	model.addAttribute("user", user);
    	return "register";      
    }
	@RequestMapping(value = "/patients/edit/{id}", method = RequestMethod.POST)
    public String editSubmitUser(@Valid User user, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("feedback", "Unable to register");
			return "register";
		}
		userService.saveOrUpdate(user);
		model.addAttribute("feedback", "Edit successful");
    	model.addAttribute("user", user);
    	log.info("Patient updated:"+user.toString());
    	return "register";      
    }
     
	
    @RequestMapping(value = "/patients/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable String id, ModelAndView mv) {
    	log.info("Deleting patient #:"+id);
    	mv.addObject("feedback", "User removed");
    	mv.setViewName("redirect:/patients");
    	userService.delete(Integer.parseInt(id));
    	log.info("Patient deleted");
    	return mv;
    }
    
    /*DOCTOR STUFF*/
	@RequestMapping("/newdoctor")
	public String newDoctor(Model model){
		log.info("Admin creating new doctor");
		model.addAttribute("doctor",new Doctor());
		model.addAttribute("specialityList",Arrays.asList(Speciality.values()));
		return "doctor_form";
	}
	
	
	
	@RequestMapping(value="/newdoctor", method=RequestMethod.POST)
	public String newDoctor(@Valid Doctor doctor, Errors errors, Model model){
		if (errors.hasErrors()) {
			model.addAttribute("feedback", "Unable to register");
			return "doctor_form";
		}
		System.out.println("ABOUT TO SAVE DOC");
		System.out.println(doctor);
		
		doctorService.saveOrUpdate(doctor);
		System.out.println("SAVED DOC");
		model.addAttribute("feedback", "Dr."+doctor.getFirstName()+" registered");
		model.addAttribute("doctor",new Doctor());
		log.info("Admin created new doctor: "+doctor.toString());
		return "doctor_form";
	}
	
	@RequestMapping(value = "/doctors/edit/{id}", method = RequestMethod.GET)
    public String editDoctor(@PathVariable String id, Model model) {
		log.info("Admin editing doctor #:"+id);
		Doctor doctor = doctorService.load(Integer.parseInt(id));
    	System.out.println("doctor: "+doctor);
    	model.addAttribute("doctor", doctor);
    	return "doctor_form";      
    }
	@RequestMapping(value = "/doctors/edit/{id}", method = RequestMethod.POST)
    public String editSubmitDoctor(@Valid Doctor doctor, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("feedback", "Unable to register");
			return "doctor_form";
		}
		doctorService.saveOrUpdate(doctor);
		model.addAttribute("feedback", "Edit successful");
    	model.addAttribute("doctor", doctor);
    	log.info("Doctor edited "+doctor.toString());
    	return "doctor_form";      
    }
     
    @RequestMapping(value = "/doctors/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteDoctor(@PathVariable String id, ModelAndView mv) {
    	log.info("Admin deleting doctor #: "+id);
    	mv.addObject("feedback", "Doctor removed");
    	mv.setViewName("redirect:/doctors");
    	doctorService.delete(Integer.parseInt(id));
    	log.info("Doctor deleted");
    	return mv;
    }
    @RequestMapping(value = "/doctors/leave/{id}", method = RequestMethod.GET)
    public ModelAndView leave(@PathVariable String id, ModelAndView mv) {
    	log.info("Admin granting leave to doctor #: "+id);
    	mv.setViewName("redirect:/doctors");
    	Doctor doctor = doctorService.load(Integer.parseInt(id));
    	doctor.getLeave().setApproved(true);
    	doctorService.saveOrUpdate(doctor);
    	mv.addObject("feedback", "leave granted");
    	log.info("Leave granted to "+doctor.toString());
    	return mv;
    }
    @RequestMapping(value = "/doctors/cancelleave/{id}", method = RequestMethod.GET)
    public ModelAndView cancelLeave(@PathVariable String id, ModelAndView mv) {
    	log.info("Admin cancelling leave of doctor #: "+id);
    	mv.setViewName("redirect:/doctors");
    	Doctor doctor = doctorService.load(Integer.parseInt(id));
    	doctor.getLeave().setApproved(false);
    	doctorService.saveOrUpdate(doctor);
    	mv.addObject("feedback", "leave cancelled");
    	log.info("Leave cancelled");
    	return mv;
    }
}
