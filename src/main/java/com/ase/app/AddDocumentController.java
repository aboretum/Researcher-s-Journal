package com.ase.app;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;





import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.ase.bean.Document;
import com.ase.bean.FigureDocument;
import com.ase.bean.Group;
import com.ase.bean.Result_display;
import com.ase.bean.User;
import com.ase.dao.DocumentDAO;
import com.ase.dao.GroupDAO;
import com.ase.dao.Result_DisplayDAO;
import com.ase.dao.UserDAO;
import com.ase.util.DocumentPrivacyService;

import java.io.*;

@Controller
public class AddDocumentController implements ServletContextAware {
	private static final Logger logger = LoggerFactory.getLogger(AddDocumentController.class);
	
	@Autowired
	ServletContext servletContext;
	
	private UserDAO userDAO = new UserDAO();
	private DocumentDAO DocDAO = new DocumentDAO();
	private GroupDAO groupDAO = new GroupDAO();
	private Result_DisplayDAO displayDAO = new Result_DisplayDAO();

	@RequestMapping(value = "/AddDocument", method = RequestMethod.POST)
	public String addDoc(Locale locale, Model model, HttpServletRequest request, 
			@RequestParam(value="fileField")MultipartFile figure,
			@RequestParam(value="docDescription") String docDes) {
		
		
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String localDate = dateFormat.format(date);
		
        String docType = "figure";
 
        HttpSession session = request.getSession(true);
		String userName = (String)session.getAttribute("username");
		
        if(userName!=null){
        	
        	User user = userDAO.getUserByName(userName);
			Group userGroup = groupDAO.getGroupByName(user.getUserGroup());
			
			
			DocDAO.setServletContext(this.servletContext);
        	
        	if(docType.equals("figure")){
            	
            	String fileName = "/resources/images/testFigure.jpg";
            	String doc_id = user.getUserName()+DocumentPrivacyService.generateUniqueDocID();
            	FigureDocument doc = new FigureDocument();
            	doc.setDocAuthor(user.getUserName());
            	doc.setDocContent("nocontent");
            	doc.setDocName(docDes);
            	doc.setDocType("figure");
            	doc.setDocUrl("foo.com");
            	doc.setDocID(doc_id);
            	
            	
            	
            	File image = null;
            	try{
            		image = new File(servletContext.getRealPath(fileName));
                
                FileUtils.writeByteArrayToFile(image, figure.getBytes());
            	}catch(IOException e){
            		;
            	}
            	
            	
                doc.setImageFile(image);
                DocDAO.addDocument(doc, date);
                displayDAO.addNewDocumentToGroup(date, doc, userGroup);
                displayDAO.addNewDocumentToUser(date, doc, user);
                model.addAttribute("doc", doc);
            }	
            
        	Result_display display = null;
			
			display = displayDAO.getDisplaybyDateandGroup(localDate, userGroup);
			List<Document> displayList = new ArrayList<Document>();
			
			if(display.getDocs()!=null){
				for(Document document : display.getDocs()){
					Document newDocument = DocDAO.getDocumentByDateandGroup(document.getDocDate(), userGroup);
					if(!newDocument.isDocPrivate()){
					displayList.add(0, newDocument);
					}
				}
				display.setDocs(displayList);
			}
			
			model.addAttribute("display", display);
			model.addAttribute("user", user);
			model.addAttribute("userGroup", userGroup);
		}
        
		return "index";
	}

	@Override
	public void setServletContext(ServletContext ctx) {
		this.servletContext = ctx;		
	}
	
	
}
