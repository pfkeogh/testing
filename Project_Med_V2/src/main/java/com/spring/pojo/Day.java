package com.spring.pojo;

public enum Day {
	Monday(0),
	Tuesday(1),
	Wednesday(2),
	Thursday(3),
	Friday(4),
	Saturday(5),
	Sunday(6);
	
	public int num;
	
	Day(int num){
		this.num=num;
	}
	public int getNum(){
		return this.num;
	}
}
