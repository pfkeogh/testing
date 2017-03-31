package test.main;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import static
org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.spring.controller.HomeController;

public class HomeControllerTest {
	
	public HomeController controller = new HomeController();
	@Test
	public void testHome() throws Exception {
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/")).andExpect(view().name("home"))
		.andExpect(model().attributeExists("searchParam"));
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
		fail("Not yet implemented");
	}

}
