package com.meteor.model;

public class Worker {

	String UserName;
	String Dept;
	String Job;
	int age;
	public final int Var_Size = 4;//변수 수
	
	public Object get(int idx){
		
		Object[] st = {this.UserName, this.Dept, this.Job, this.age};
		
		return st[idx];	
	}
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getDept() {
		return Dept;
	}
	public void setDept(String dept) {
		Dept = dept;
	}
	public String getJob() {
		return Job;
	}
	public void setJob(String job) {
		Job = job;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
