package com.meteor.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractExcelView;

@Service("excelDownload")
public class Excel_View extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		  String userAgent = req.getHeader("User-Agent");
		  String fileName = "test.xls";
		  
		  if(userAgent.indexOf("MSIE") > -1){
		   fileName = URLEncoder.encode(fileName, "utf-8");
		  }else{
		   fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		  }
		  
		  res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		  res.setHeader("Content-Transfer-Encoding", "binary");
		    
		  HSSFSheet sheet = createFirstSheet(workbook);
		  
		  ArrayList<String> column_list = new ArrayList<String>();
		  column_list.add("이름");
		  column_list.add("회사");
		  column_list.add("직급");
		  
		  createColumnLabel( sheet, column_list );
		  
		  List<String> menuList = (List<String>)model.get("menuList");
		  for(int i=0; i <= menuList.size()-1; i++){
		   createPageRow(sheet, menuList, i);
		  }
		
	}

	/**
	 * Sheet 생성
	 * @param workbook
	 * @return
	 */
	 private HSSFSheet createFirstSheet(HSSFWorkbook workbook){
	  HSSFSheet sheet = workbook.createSheet();
	  workbook.setSheetName(0, "테스트");//시트 이름 지정
	  
	  sheet.setColumnWidth(1, 256*30);
	  //sheet.setColumnWidth(1, 256*254);//컬럼 사이즈 지정
	  
	  return sheet;
	 }

	 /**
	  * 엑셀 헤더 생성( th )
	  * @param sheet
	  */
	 private void createColumnLabel(HSSFSheet sheet, List<String> column_names ){
	  HSSFRow firstRow = sheet.createRow(0);
	  
	  //HSSFCell cell = firstRow.createCell(0);
	  HSSFCell cell = null;
	  /*
	  cell.setCellValue("순위");//첫번째
	  
	  cell = firstRow.createCell(1);
	  cell.setCellValue("페이지");//두번째
	 */
	  for(int idx=0; idx<column_names.size(); idx++){
		  cell = firstRow.createCell( idx );
		  cell.setCellValue( column_names.get(idx) );
	  }
	  
	  
	 }
	 
	 /**
	  * Row 하나씩 입력
	  * @param sheet
	  * @param menuList
	  * @param rowNum
	  */
	 private void createPageRow(HSSFSheet sheet, List<String> menuList, int rowNum){
	  HSSFRow row = sheet.createRow(rowNum + 1);
	  
	  //HSSFCell cell = row.createCell(0);
	  HSSFCell cell = null;
	  
	 

	  for(int idx=0; idx < menuList.size(); idx++){
		  cell = row.createCell( idx );
		  cell.setCellValue( menuList.get( idx ) );
	  }
	  //cell = row.createCell(1);
	   
	 }
	 
}
