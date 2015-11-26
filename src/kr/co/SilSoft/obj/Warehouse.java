package kr.co.SilSoft.obj;

public class Warehouse {
	
	private Stock stock;
	private String name;
	private String addr;
	private String phone;
	
	public Warehouse(String name, String addr, String phone){
		this.name = name;
		this.addr = addr;
		this.phone = phone;
		this.stock = new Stock();
	}
	
	public Stock getStock() {
		return stock;
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

	public void stock(Product product, int amount){
		int num = amount;
		if(stock.getAmount(product) != 0){
			num += stock.getAmount(product);
		}
		stock.setAmount(product, num);
		product.setWarehouses(this);
	}
	
	public void release(Product product, int amount){
		int num = amount;
		int getAmount = stock.getAmount(product);
		if(getAmount < num)	return;
		stock.setAmount(product, getAmount-num);
	}
}
