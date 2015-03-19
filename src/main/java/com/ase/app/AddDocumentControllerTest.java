package com.ase.app;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("appContext.xml")
public class AddDocumentControllerTest {
	
	
	MockServletContext mockServletContext = new MockServletContext("");

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddDoc() {
		AddDocumentController adc = new AddDocumentController();
		adc.setServletContext(mockServletContext);
		System.out.println(mockServletContext.getRealPath("/"));
		MockHttpServletRequest request = new MockHttpServletRequest();
		Model model = new ExtendedModelMap();
		
		HttpServletRequest abstract_request = request;
		//String returnText = adc.addDoc(null, model, abstract_request);
		// assertEquals(returnText,"home");
		
	}

}
