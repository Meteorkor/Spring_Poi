package com.meteor.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		StringBuffer stb = new StringBuffer();
		
		stb.append("<table>");
		for(int idx=0;idx<10;idx++){
			stb.append("<tr>");
			stb.append("<td>");
			stb.append("1");
			stb.append("</td>");
			stb.append("<td>");
			stb.append("2");
			stb.append("</td>");
			stb.append("<td>");
			stb.append("3");
			stb.append("</td>");
			stb.append("</tr>");
		}
		stb.append("</table>");
		
		model.addAttribute("test_table", stb.toString() );
		logger.info("test");
		/*
		response.setHeader("Content-Disposition", "attachment; filename=excel.xls");
		response.setHeader("Content-Description", "JSP Generated Data"); 
		*/
		
		return "home";
	}
	
	@RequestMapping(value = "/exceldown", method = RequestMethod.GET)
	public String exceldown(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		List<String> list = new ArrayList<String>();
		  list.add("사이트 관리");
		  list.add("관리자 관리");
		  list.add("공통 코드 관리");
		  list.add("접속 이력");
		  
		  model.addAttribute("menuList", list);
		return "excelDownload";
	}
	
		
}
