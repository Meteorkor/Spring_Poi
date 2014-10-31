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

import com.meteor.model.Poi_Row_Interface;
import com.meteor.model.Poi_Worker_Row;
import com.meteor.model.Poi_ab_sheet_model;
import com.meteor.model.Test_impl;
import com.meteor.model.Worker;

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
		
		return "home";
	}
	
	@RequestMapping(value = "/exceldown", method = RequestMethod.GET)
	public String exceldown(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		 ArrayList<String> ColumnList = new ArrayList<String>();
		  ColumnList.add("이름");
		  ColumnList.add("회사");
		  ColumnList.add("직급");//샘플로 여기서 만듬
		
		
		List<Object> DataList = new ArrayList<Object>();
		
		Worker worker1 = new Worker();
		worker1.setUserName("홍길동123123123123123123123123123");
		worker1.setDept("SM");
		worker1.setJob("엔지니어");
		worker1.setAge(10.22);
		
		Worker worker2 = new Worker();
		worker2.setUserName("파비앙");
		worker2.setDept("AM");
		worker2.setJob("엔지니어");
		worker2.setAge(20);
		
		
		Worker worker3 = new Worker();
		worker3.setUserName("민교");
		worker3.setDept("SM");
		worker3.setJob("개발123123123123123123123123123123");
		worker3.setAge(30.1);
		
		DataList.add(worker1);
		DataList.add(worker2);
		DataList.add(worker3);
		
		
		model.addAttribute("ColumnList", ColumnList);
		model.addAttribute("DataList", DataList);
		model.addAttribute("excel_file_name", "sample");
		  
		return "excelDownload";
	}
	
	
	@RequestMapping(value = "/exceltest", method = RequestMethod.GET)
	public String excel_test(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		
	Poi_Worker_Row woker = new Poi_Worker_Row();
	woker.setName("kim");
	woker.setDept("부서");
	woker.setAge( 10 );
	
	ArrayList<Poi_Row_Interface> poi_row_list = new ArrayList<Poi_Row_Interface>();
	poi_row_list.add(woker);

		Poi_ab_sheet_model poi_model = new Test_impl(poi_row_list);
		
		model.addAttribute( Excel_View.ColumnList , poi_model.get_Column_Name_List());
		model.addAttribute( Excel_View.DataList , poi_model.get_Row_List() );
		model.addAttribute( Excel_View.Excel_File_name , "sample");
		  
		
		
		return "excelDownload";
	}
	//////////
	
		
}
