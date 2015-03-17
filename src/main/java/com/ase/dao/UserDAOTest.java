package com.ase.dao;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ase.bean.User;

public class UserDAOTest {
	private UserDAO userDAO = new UserDAO();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUserDAO() {
		
	}

	@Test
	public void testAddUser() throws NoSuchAlgorithmException {
		User user = new User();
		user.setUserName("nacho");
		user.setPassWord("ww");
		user.setMember_title("researcher");
		//userDAO.addUser(user);
		
	}

	@Test
	public void testGetUserByName() {
		User user = new User();
		user.setUserName("nacho");
		user.setPassWord("123");
		user.setMember_title("researcher");
		User newUser = userDAO.getUserByName(user.getUserName());
		assertEquals("nacho",newUser.getUserName());
	}

	@Test
	public void testGetAllUsers() {
		
	}

	@Test
	public void testUpdateUser() {
		
	}

	@Test
	public void testDeleteUser() {
		
	}

}
