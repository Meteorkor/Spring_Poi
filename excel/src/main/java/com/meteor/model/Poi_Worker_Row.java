package com.meteor.model;

/**
 * Poi_Row_Interface 예제 구현체
 * @author kimunseok
 *
 */
public class Poi_Worker_Row implements Poi_Row_Interface{


	String name;
	String dept;
	int age;
	
	@Override
	public Object get(int idx) {
		// TODO Auto-generated method stub
		
		if(idx==0){
			return this.name;
		}else if(idx==1){
			return this.dept;
		}else {
			return this.age;
		}
		
	}

	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 3;//manual
	}


	public String getName() {
		return name;
	}

	public String getDept() {
		return dept;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	
}
