package com.meteor.model;

import java.util.ArrayList;

public class Test_impl extends Poi_model_interface{

	//Data는 다른곳에서 상속 받아도..
	String Name;
	Double Age;
	String Location;
	
	
	public Test_impl() {
		ArrayList<String> column_name = new ArrayList<String>();
		column_name.add("이름");
		column_name.add("나이");
		column_name.add("사는곳");
		this.set_Column_Name_List(column_name);
		
		ArrayList<Object> Data_name = new ArrayList<Object>();
		Data_name.add( this.Name );
		Data_name.add( this.Age );
		Data_name.add( this.Location );
	}
	
}
