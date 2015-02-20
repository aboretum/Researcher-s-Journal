package com.ase.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.ase.dao.DatabaseUtility;



/**
 * Handles requests for the page directory.
 */
@Controller
public class PageController {
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	DatabaseUtility dbUtil = new DatabaseUtility();
	
}
