package com.meteor.poi;

import java.net.URLEncoder;
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

/**
 * 엑셀 생성 View
 * @author kimunseok
 *
 */
@Service("excelDownload")
public class Excel_View extends AbstractExcelView{

	public final static String Poi_Model = "Poi_Model";
	
	public final static String Excel_File_name = "Excel_File_name";
	
	
	@Deprecated
	public final static String ColumnList = "ColumnList";
	@Deprecated
	public final static String DataList = "DataList";
	
	@Override
	/**
	 * 뷰에서 호출
	 * 
	 */
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		  String userAgent = req.getHeader("User-Agent");
		  String fileName = (String)model.get( Excel_File_name );//엑셀 파일 명
		  
		  if(userAgent.indexOf("MSIE") > -1){
			  fileName = URLEncoder.encode(fileName, "utf-8");
		  }else{
			  fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		  }
		  res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xls\";");
		  res.setHeader("Content-Transfer-Encoding", "binary");
		    
		  HSSFSheet sheet = createFirstSheet(workbook);//시트 생성
		  
		  
		  Poi_sheet_model poi_model = (Poi_sheet_model)model.get( Poi_Model );//Poi Model
		  
		  List<String> column_list =  poi_model.get_Column_Name_List();//컬럼 리스트
		  createColumnLabel( sheet, poi_model );
		  
		  List<Poi_Row_Interface> menuList = poi_model.get_Row_List();//데이터 리스트
		  
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
	  
	  //sheet.setColumnWidth(1, 256*30);
	  
	  return sheet;
	 }

	 /**
	  * 엑셀 헤더 생성( th )
	  * @param sheet
	  */
	//private void createColumnLabel(HSSFSheet sheet, List<String> column_names) {
	 private void createColumnLabel(HSSFSheet sheet, Poi_sheet_model poi_sheet_model) {
		
		List<String> column_names = poi_sheet_model.get_Column_Name_List();
		
		HSSFRow firstRow = sheet.createRow(0);
		HSSFCell cell = null;

		for (int idx = 0; idx < column_names.size(); idx++) {
			cell = firstRow.createCell(idx);
			cell.setCellValue(column_names.get(idx));
			//cell.getCellStyle().setFont( new F );
			
		}

	}
	 
	 /**
	  * Row 하나씩 입력
	  * @param sheet
	  * @param menuList
	  * @param rowNum
	  */
	 private void createPageRow(HSSFSheet sheet, Poi_Row_Interface row_data, int rowNum){
	  HSSFRow row = sheet.createRow(rowNum + 1);
	  
	  //HSSFCell cell = row.createCell(0);
	  HSSFCell cell = null;
	 
	  for(int idx=0; idx < row_data.size(); idx++){
		  cell = row.createCell( idx );
		  
		  
		  if( row_data.get(idx) instanceof Double ){//Double 형이면
			  cell.setCellValue( Double.valueOf( String.valueOf( row_data.get( idx ) ) ) );
		  }else if( row_data.get(idx) instanceof Integer ){//Double 형이면
			  cell.setCellValue( Integer.valueOf( String.valueOf( row_data.get( idx ) ) ) );
		  }else{
			  cell.setCellValue( String.valueOf( row_data.get( idx ) ) );
		  }

	  }//end for

	   
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
