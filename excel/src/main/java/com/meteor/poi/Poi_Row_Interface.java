package com.meteor.poi;

/**
 * Poi Row Interface
 * @author kimunseok
 *
 */
public interface Poi_Row_Interface {
	
	/**
	 * Row Cell Number
	 * @param idx
	 * @return
	 */
	public Object get(int idx);
	
	/**
	 * Row Cell Size
	 * @return
	 */
	public int size();
	
	
	
}
