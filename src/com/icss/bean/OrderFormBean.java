package com.icss.bean;

public class OrderFormBean {
	private int num;
	private int tb_num;
	private int desk_num;
	private String dateTime;
	private double money;
	private int user_id;

	public OrderFormBean(){
		
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getTb_num() {
		return tb_num;
	}
	public void setTb_num(int tb_num) {
		this.tb_num = tb_num;
	}
	public int getDesk_num() {
		return desk_num;
	}
	public void setDesk_num(int desk_num) {
		this.desk_num = desk_num;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
