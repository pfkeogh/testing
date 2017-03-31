package unittest.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.pojo.Appointment;
import com.spring.service.AppointmentService;

public class HomeControllerTest {
	
	@Autowired
	private AppointmentService appService;
	@Test
	public void testHome() {
		fail("Not yet implemented");
	}

	@Test
	public void testHomeSearch() {
		fail("Not yet implemented");
	}

	@Test
	public void testProcessLogout() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoctorLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdmin() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoctors() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoctor() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewSchedule() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeAppt() {
		fail("Not yet implemented");
	}

	@Test
	public void testConfirmAppt() {
		Appointment appt = new Appointment();
		appt.setDuration(30);
		appt.setTime("time");
		
		fail("Not yet implemented");
	}

	@Test
	public void testNextWeek() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrevWeek() {
		fail("Not yet implemented");
	}

}
