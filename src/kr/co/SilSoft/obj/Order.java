package kr.co.SilSoft.obj;

import java.util.ArrayList;
import java.util.Calendar;

import kr.co.SilSoft.main.OrderSystem;

public class Order {
	private ArrayList<OrderDetail> orderDetails;
	private Customer customer;
	private int orderNumber;
	private Calendar orderDate;
	
	public Order(){
		this.customer = null;
		this.orderNumber = -1;
		this.orderDate = null;
		this.orderDetails = new ArrayList<OrderDetail>();
	}
	
	public void register(Customer customer){
		this.customer = customer;
		this.orderNumber = OrderSystem.orderNumber;
		this.orderDate = getOrderDate();
		OrderSystem.orders.add(this);
		OrderSystem.orderNumber++;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public Calendar getOrderDate() {
		return Calendar.getInstance();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

}
