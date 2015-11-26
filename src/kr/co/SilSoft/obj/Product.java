package kr.co.SilSoft.obj;

import java.util.ArrayList;

public class Product {
	private ArrayList<OrderDetail> orderDetails;
	private ArrayList<Warehouse> warehouses;
	private int code;
	private String name;
	private int price;
	
	public Product(int code, String name, int price){
		this.code = code;
		this.name = name;
		this.price = price;
		this.orderDetails = new ArrayList<OrderDetail>();
		this.warehouses = new ArrayList<Warehouse>(); 
	}

	public ArrayList<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public ArrayList<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(Warehouse warehouse) {
		warehouses.add(warehouse);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
