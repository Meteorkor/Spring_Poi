package com.meteor.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class Tester {

	
	@Test
	public void ex() throws IOException{
		Workbook wb = new HSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		Sheet sheet = wb.createSheet("테스트 페이지");
		Row row = sheet.createRow(1);
		
		//row.createCell(1, 3);
		
		Cell cell = null;
		
		
		
		CellStyle cell_top = wb.createCellStyle();
		cell_top.setBorderTop((short) 2);
		CellStyle cell_left = wb.createCellStyle();
		cell_left.setBorderLeft((short) 2);
		CellStyle cell_right = wb.createCellStyle();
		cell_right.setBorderRight((short) 2);
		//cell_right.setBorderBottom((short) 2);
		CellStyle cell_bottom = wb.createCellStyle();
		cell_bottom.setBorderBottom((short) 2);
		
		
		//sheet.setColumnWidth(1, 100);
		/**/
		sheet.setColumnWidth(1,10000);
		sheet.setColumnWidth(2,20000);
		sheet.setColumnWidth(3,30000);
		sheet.setColumnWidth(4,40000);
		
		for(String tex : get_buck_worker() ){
			
			for(int width =1; width<4; width++){
				cell = row.createCell(width);
				cell.setCellValue( tex.split(",")[width-1] );
		
				
				
				if(row.getRowNum()==1){
					cell.setCellStyle(cell_top);
					//cell.getCellStyle().setBorderTop((short) 1);
					System.out.println("top");
				}
				/**/
				if(width == 1){
					cell.setCellStyle(cell_left);
					//cell.getCellStyle().cloneStyleFrom(cell_left);
					//cell.getCellStyle().setBorderLeft((short) 2);
					System.out.println("left");
				}
				if(width ==3){
					cell.setCellStyle(cell_right);
					//cell.getCellStyle().setBorderRight((short) 2);
					System.out.println("right");
				}
				
			}
				System.out.println("row.getRowNum() : " + row.getRowNum());
				//row.setRowNum( row.getRowNum()+1 );
				row = sheet.createRow( row.getRowNum() +1  );
				
		}
		FileOutputStream fileOut = new FileOutputStream("c:/com/excel_test.xls");
	    wb.write(fileOut);
	    fileOut.close();
		
		
		
	}
	//@Test
	public void test() throws IOException{
		
		 Workbook wb = new HSSFWorkbook();
		    //Workbook wb = new XSSFWorkbook();
		    CreationHelper createHelper = wb.getCreationHelper();
		    Sheet sheet = wb.createSheet("new sheet");

		    // Create a row and put some cells in it. Rows are 0 based.
		    Row row = sheet.createRow((short)0);
		    // Create a cell and put a value in it.
		    Cell cell = row.createCell(0);
		    
		    cell.setCellValue(1);

		    CellStyle cellStyle = wb.createCellStyle();
		    
		    cellStyle.setBorderBottom((short) 1.1);
		    cell.setCellStyle( cellStyle );
		    
		    
		    // Or do it on one line.
		    row.createCell(1).setCellValue(1.2);
		    row.createCell(1).setCellValue(1.2);
		    row.createCell(2).setCellValue(
		         createHelper.createRichTextString("This is a string"));
		    row.createCell(3).setCellValue(true);
		    
		    
		    
/**/
		    
		    

	/**/	    
		    row = sheet.createRow(3);
		    row.createCell(0).setCellValue("test");
		    row.setRowStyle( cellStyle );
		    
		    
		    // Write the output to a file
		    //FileOutputStream fileOut = new FileOutputStream("workbook.xls");
		    FileOutputStream fileOut = new FileOutputStream("c:/com/workbook.xls");
		    wb.write(fileOut);
		    fileOut.close();
		
	}
	
	public List<String> get_buck_worker(){
		ArrayList<String> list = new ArrayList<String>();
		
		for(int idx=0; idx<10;idx++){
			list.add("홍길동,11,2014-10-22");	
		}
		
		
		return list; 
		
		
	}
	
}
