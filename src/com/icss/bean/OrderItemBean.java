package com.icss.bean;

public class OrderItemBean {
    private int id;
    private int order_form_item;
    private int menu_num;
    private int amount;
    private double total;
    
	public OrderItemBean() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrder_form_item() {
		return order_form_item;
	}
	public void setOrder_form_item(int order_form_item) {
		this.order_form_item = order_form_item;
	}
	public int getMenu_num() {
		return menu_num;
	}
	public void setMenu_num(int menu_num) {
		this.menu_num = menu_num;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
    
}
