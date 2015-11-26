package kr.co.SilSoft.obj;

public class OrderDetail {
	
	private Order order;
	private Product product;
	private int amount;
	private int discountRate;
	private String warehouse;
	
	public OrderDetail(Order order){
		this.order = order;
		this.amount = 0;
		this.warehouse = "";
		setDiscountRate();
	}
	
	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate() {
		if(order.getCustomer() == null){
			this.discountRate = 0;
		}else if(order.getCustomer() instanceof Member){
			this.discountRate = 10;
		}
		
	}
	
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getWarehouse() {
		return warehouse;
	}
	
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	
	public void register(Product product, String warehouse, int amount){
		this.product = product;
		this.amount = amount;
		this.warehouse = warehouse;
	}
	
	public void info(){
		System.out.println();
		System.out.println("===========================");
		System.out.printf("Product:%s[%d]\r\n", product.getName(), product.getCode());
		System.out.printf("Warehouse:%s\r\n", warehouse);
		System.out.printf("Amount:%d\r\n", amount);
		System.out.println("DiscountRate:"+discountRate+"%");
		System.out.println();
	}

}
