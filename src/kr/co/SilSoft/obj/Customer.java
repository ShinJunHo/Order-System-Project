package kr.co.SilSoft.obj;

import java.util.ArrayList;

public class Customer {
	private int id;
	private String name;
	private String addr;
	private String phone;
	private ArrayList<Order> orders;
	
	public Customer(int id, String name, String addr, String phone){
		this.id = id;
		this.name = name;
		this.addr = addr;
		this.phone = phone;
		this.orders = new ArrayList<Order>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddr() {
		return addr;
	}

	public String getPhone() {
		return phone;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}
}
