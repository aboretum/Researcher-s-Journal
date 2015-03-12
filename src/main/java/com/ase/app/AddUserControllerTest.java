package com.ase.app;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddUserControllerTest {

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
	public void testAddUser() throws NoSuchAlgorithmException{
		AddUserController addUserController = new AddUserController();
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("username", "nacho");
		request.setParameter("password", "123456");
		request.setParameter("confirmPassword", "123456");
		request.setParameter("email", "test");
		request.setParameter("member_title", "researcher");
		Model model = new ExtendedModelMap();
		
		HttpServletRequest abstract_request = request;
		String returnText = addUserController.home(null, model, abstract_request);
		assertEquals(returnText,"home");
	}
	
	@Test
	public void testAddUser2(){
		
	}

}
