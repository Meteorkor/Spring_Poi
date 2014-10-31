package com.meteor.poi;

import java.util.ArrayList;
import java.util.List;


public class Test_impl extends Poi_sheet_model{

	public Test_impl() {
		
	}
	
	public Test_impl(List<Poi_Row_Interface> row_Inter_Row) {
		super(row_Inter_Row);
		
		this.set_Column_Name_List( this.create_column_list() );
		
		// TODO Auto-generated constructor stub
	}

	
	public List<String> create_column_list(){
		ArrayList<String> column_name = new ArrayList<String>();
		column_name.add("이름");
		column_name.add("나이");
		column_name.add("사는곳");
		
		return  column_name;
	}
	
}
