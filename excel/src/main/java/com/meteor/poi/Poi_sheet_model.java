package com.meteor.poi;

import java.util.List;

/**
 * 
 * @author kimunseok
 *
 */
public abstract class Poi_sheet_model {

	protected List<String> Column_Name_List = null;
	protected List<Poi_Row_Interface> Row_List = null;
	
	/**
	 * 데이터 삽입
	 * @param row_Inter_Row
	 */
	public Poi_sheet_model( List<Poi_Row_Interface> row_Inter_Row ) {
		this.set_Row_List( row_Inter_Row );
	}
	
	public Poi_sheet_model(  ) {
		
	}
	
	
	/**
	 * 각각 Data를 반환
	 * @param idx
	 * @return
	 */
	public Poi_Row_Interface get_Row(int idx){
		
		return this.Row_List.get(idx); 
	}
	public Object get_cell(int row_idx, int cell_idx){
		
		return this.Row_List.get(row_idx).get(cell_idx);
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
	public List<Poi_Row_Interface> get_Row_List(){
		return this.Row_List;
	}
	/**
	 * Data 리스트 설정
	 * @param lst
	 * @return
	 */
	
	protected List<Poi_Row_Interface> set_Row_List(List<Poi_Row_Interface> lst){
		this.Row_List = lst;
		
		return this.Row_List;
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
