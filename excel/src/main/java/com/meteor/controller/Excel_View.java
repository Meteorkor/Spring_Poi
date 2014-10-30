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

import com.meteor.model.Worker;

/**
 * 엑셀 파일을 생성
 * @author kimunseok
 *
 */
@Service("excelDownload")
public class Excel_View extends AbstractExcelView{

	
	@Override
	/**
	 * 뷰에서 호출
	 * 
	 */
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		  String userAgent = req.getHeader("User-Agent");
		  String fileName = (String)model.get("excel_file_name");//엑셀 파일 명
		  
		  if(userAgent.indexOf("MSIE") > -1){
			  fileName = URLEncoder.encode(fileName, "utf-8");
		  }else{
			  fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		  }
		  res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		  res.setHeader("Content-Transfer-Encoding", "binary");
		    
		  HSSFSheet sheet = createFirstSheet(workbook);//시트 생성
		  
		  ArrayList<String> column_list = (ArrayList<String>)model.get("ColumnList");//컬럼 리스트
		  createColumnLabel( sheet, column_list );
		  
		  List<Worker> menuList = (List<Worker>)model.get("DataList");//데이터 리스트
		  for (int i = 0; i < menuList.size(); i++) {
			createPageRow(sheet, menuList.get(i), i);

		}
		  
		  for (int i = 0; i < column_list.size(); i++) {//컬럼을 데이터에 따라 동적으로
			  set_autoSizeColumn(sheet, i);  
		  }
		  
		
	}////

	/**
	 * Sheet 생성
	 * @param workbook
	 * @return
	 */
	 private HSSFSheet createFirstSheet(HSSFWorkbook workbook){
	  HSSFSheet sheet = workbook.createSheet();
	  workbook.setSheetName(0, "테스트");//시트 이름 지정
	  
	  sheet.setColumnWidth(1, 256*30);
	  
	  //sheet.autoSizeColumn(0);
	  //sheet. setColumnWidth(0 , sheet.getColumnWidth(0) + 512);
	  
	  //sheet.autoSizeColumn(1);
	  
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
	 private void createPageRow(HSSFSheet sheet, Worker worker, int rowNum){
	  HSSFRow row = sheet.createRow(rowNum + 1);
	  
	  //HSSFCell cell = row.createCell(0);
	  HSSFCell cell = null;
	 
	  for(int idx=0; idx < worker.Var_Size; idx++){
		  cell = row.createCell( idx );
		  
		  
		  if( worker.get(idx) instanceof Double ){//Double 형이면
			  cell.setCellValue( Double.valueOf( String.valueOf( worker.get( idx ) ) ) );
		  }else{
			  cell.setCellValue( String.valueOf( worker.get( idx ) ) );
		  }

	  }
	  //cell = row.createCell(1);
	   
	 }
	 /**
	  * 컬럼 넓이를 넣은 값에 맞게 변경
	  * @param sheet
	  * @param colNum
	  */
	 private void set_autoSizeColumn(HSSFSheet sheet, int colNum){
		 sheet.autoSizeColumn( colNum ); 
	 }
	 
}
