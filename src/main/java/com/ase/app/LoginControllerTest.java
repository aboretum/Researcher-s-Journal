package com.ase.app;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHome() {
		
	}

	@Test
	public void testLogin() throws NoSuchAlgorithmException {
		LoginController loginController = new LoginController();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("username", "nacho");
		request.setParameter("password", "123456");
		
		Model model = new ExtendedModelMap();
		
		String resultText = loginController.login(null, model, request);
		assertEquals(resultText, "info");
	}

}
