package com.ase.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ase.dao.*;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;


/**
 * Handles requests for the user registration.
 */
@Controller
public class AddUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(AddUserController.class);
	
	DatabaseUtility dbUtil = new DatabaseUtility();
	
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String userName = "abc";
		String passWord = "123";
		String member_title = "researcher";
		
		dbUtil.connect();
		DBCollection col = dbUtil.getCollection();
		BasicDBObject doc = new BasicDBObject("user_name",userName).
				append("password",passWord).
				append("member_title",member_title);
		col.insert(doc);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "main";
	}
}
