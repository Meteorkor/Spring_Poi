package com.meteor.model;

import java.util.List;

//public abstract class Poi_model_interface {
public abstract class Poi_model_interface {

	protected List<String> Column_Name_List = null;
	protected List<Object> Data_List = null;
	
	/**
	 * 각각 Data를 반환
	 * @param idx
	 * @return
	 */
	public Object get_Data(int idx){
		
		return this.Data_List.get(idx); 
	}
	/**
	 * 각각 컬럼 반환
	 * @param idx
	 * @return
	 */
	public String get_Column(int idx){
		
		return this.Column_Name_List.get(idx); 
	}
	/**
	 * Data 리스트 반환
	 * @return
	 */
	public List<Object> get_Data_List(){
		return this.Data_List;
	}
	/**
	 * Data 리스트 설정
	 * @param lst
	 * @return
	 */
	
	protected List<Object> set_Data_List(List<Object> lst){
		this.Data_List = lst;
		
		return this.Data_List;
	}
	/**
	 * Column 리스트 설정
	 * @param lst
	 * @return
	 */
	protected List<String> set_Column_Name_List(List<String> lst){
		this.Column_Name_List = lst;
		
		return this.Column_Name_List;
	}
	/**
	 * Column 리스트 리턴
	 * @return
	 */
	public List<String> get_Column_Name_List(){
		return this.Column_Name_List;
	}
	
}
