package kr.co.SilSoft.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import kr.co.SilSoft.main.OrderSystem;
import kr.co.SilSoft.obj.Customer;
import kr.co.SilSoft.obj.Member;
import kr.co.SilSoft.obj.Order;
import kr.co.SilSoft.obj.OrderDetail;
import kr.co.SilSoft.obj.Product;
import kr.co.SilSoft.obj.Stock;
import kr.co.SilSoft.obj.Warehouse;

public class OrderUI {

	//��ǰ���
	public void productList(){
		HashMap<Integer, Product> products = OrderSystem.products;
		Set<Integer> key = products.keySet();
		Iterator<Integer> it = key.iterator();
		
		System.out.println();
		System.out.println(" ��ǰ�ڵ�  ��ǰ��         �ܰ� ");
		System.out.println("==================================");
		while(it.hasNext()){
			Product p = products.get(it.next());
			System.out.printf("%8d   %-12s   %6d��\r\n", p.getCode(), p.getName(), p.getPrice());
		}
		System.out.println();
	}
	
	//��ǰ ��� ��ȸ �޴�
	public void menuProductAmount(){
		Scanner scan = new Scanner(System.in);
		String msg;
		while(true){
			System.out.println();
			System.out.println("1. ��ü ��� ��Ȳ");
			System.out.println("2. ��ǰ�� ��� ��Ȳ");
			System.out.println("3. â�� ��� ��Ȳ");
			System.out.println("5. ó������");
			System.out.println();
			System.out.print("�޴� ���� > ");
			msg = scan.nextLine();
			
			switch(Integer.valueOf(msg)){
				case 1:
					viewProductsTotalAmount();
					break;
				case 2:
					productList();
					System.out.print("��ǰ��ȣ > ");
					String text = scan.nextLine();
					Product product = OrderSystem.products.get(Integer.valueOf(text));
					productAmount(product);
					break;
				case 3:
					amountWarehouse();
					break;
				case 5:
					return;
				default :
					System.out.println();
					System.out.println("�߸��� �Է��Դϴ�.");
					break;
			}
		}
	}
	
	//â�� �����Ȳ Ȯ��
	public void amountWarehouse(){
		ArrayList<Warehouse> warehouses = OrderSystem.warehouses;
		Stock stock = null;
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.println("â����");
		System.out.println("=========================");
		System.out.println(" No   WareHouse          ");
		System.out.println("=========================");
		for(int i=0;i<warehouses.size();i++){
			Warehouse w = warehouses.get(i);
			System.out.printf("[%2d] [%-10s]\r\n", (i+1),w.getName());
		}
		System.out.println("=========================");
		System.out.println();
		
		while(true){
			
			System.out.println(" -- â������ ���÷��� list�� �Է��ϼ���.");
			System.out.println(" -- ����ȭ������ �����÷��� exit�� �Է��ϼ���.");
			System.out.print("â�� ��ȣ > ");
			String msg = scan.nextLine();
			
			if(msg.equals("exit")){
				return;
			}
			
			if(msg.equals("list")){
				System.out.println();
				System.out.println("â����");
				System.out.println("=========================");
				System.out.println(" No   WareHouse          ");
				System.out.println("=========================");
				for(int i=0;i<warehouses.size();i++){
					Warehouse w = warehouses.get(i);
					System.out.printf("[%2d] [%-10s]\r\n", (i+1),w.getName());
				}
				System.out.println("=========================");
				System.out.println();
				continue;
			}
			
			int num = Integer.valueOf(msg) - 1;
			Warehouse wa = warehouses.get(num);
			stock = wa.getStock();
			
			Set<Product> key = stock.getKeyset();
			Iterator<Product> it = key.iterator();
			
			System.out.println();
			System.out.println(wa.getName() + "�� ��� ��Ȳ");
			System.out.println();
			System.out.println("��ǰ��        ����");
			System.out.println("=========================");
			
			while(it.hasNext()){
				Product p = it.next();
				String name = p.getName();
				int amount = stock.getAmount(p);
				
				System.out.printf("%-12s  %-5s\r\n", name, amount);
			}
			System.out.println();
			
		}
		
	}
	
	//��ǰ�� ��ü ��� Ȯ��
	public void viewProductsTotalAmount(){
		HashMap<Integer, Product> products = OrderSystem.products;
		Set<Integer> key = products.keySet();
		Iterator<Integer> it = key.iterator();
		
		System.out.println();
		System.out.println(" ��ǰ�ڵ�  ��ǰ��         �ܰ�      ����");
		System.out.println("=========================================");
		
		while(it.hasNext()){
			Product p = products.get(it.next());
			ArrayList<Warehouse> warehouses = p.getWarehouses();
			
			int code = p.getCode();
			String name = p.getName();
			int price = p.getPrice();
			int sum = 0;
			for(int i=0;i<warehouses.size();i++){
				Warehouse w = warehouses.get(i);
				Stock s = w.getStock();
				sum += s.getAmount(p);
			}
			System.out.printf("%8d   %-12s   %6d��  %5d\r\n", code, name, price, sum);
			
		}
		System.out.println();
	}
	
	//��ǰ�� �����Ȳ
	public void productAmount(Product product){
		ArrayList<Warehouse> warehouse = product.getWarehouses();
		int sum = 0;
		
		System.out.println();
		System.out.println(product.getName()+"["+product.getCode()+"] �� �����Ȳ");
		System.out.println("================================");
		System.out.println(" No   WareHouse          Amount ");
		System.out.println("================================");
		for(int i=0;i<warehouse.size();i++){
			Warehouse w = warehouse.get(i);
			Stock stock = w.getStock();
			int num = stock.getAmount(product);
			sum += num;
			System.out.printf("[%2d] [%-10s] [%-6d]\r\n", (i+1),w.getName(), num);
		}
		System.out.println("================================");
		System.out.printf("          Total Amount  [%-6d]\r\n", sum);
		System.out.println();
	}
	
	//ȸ�����
	public void memberList(){
		ArrayList<Member> members = OrderSystem.members;
		System.out.println();
		System.out.println("ID        Name     Address   Phone");
		System.out.println("===============================================");
		for(int i=0;i<members.size();i++){
			Member m = members.get(i);
			
			int id = m.getId();
			String name = m.getName();
			String addr = m.getAddr();
			String phone = m.getPhone();
			
			System.out.printf("%7d  %-7s  %-8s  %-18s\r\n", id, name, addr, phone);
		}
		System.out.println("===============================================");
		System.out.println();
	}

	//�����̷� ��ȸ
	public void orderInfo(){
		Scanner scan = new Scanner(System.in);
		String msg;
		while(true){
			System.out.println();
			System.out.println("1. ��ü ���� �̷� ��ȸ");
			System.out.println("2. ��ǰ�� ���� �̷� ��ȸ");
			System.out.println("3. ȸ���� ���� �̷� ��ȸ");
			System.out.println("4. ��¥�� ���� �̷� ��ȸ");
			System.out.println("5. ó������");
			System.out.println();
			System.out.print("�޴� ���� > ");
			msg = scan.nextLine();
			
			switch(Integer.valueOf(msg)){
				case 1:
					orderList();
					break;
				case 2:
//					System.out.println();
//					System.out.println("���� �������� �ʴ� �޴��Դϴ�.");
//					break;
				case 3:
//					System.out.println();
//					System.out.println("���� �������� �ʴ� �޴��Դϴ�.");
//					break;
				case 4:
					System.out.println();
					System.out.println("���� �������� �ʴ� �޴��Դϴ�.");
					break;
				case 5:
					return;
				default :
					System.out.println();
					System.out.println("�߸��� �Է��Դϴ�.");
					break;
			}
		}
	}
	
	//��ü �����̷� Ȯ���ϱ�
	public void orderList(){
		SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date date;
		ArrayList<Order> order = OrderSystem.orders;
		System.out.println();
		System.out.println("���ֹ�ȣ ���ֳ�¥             ����   ����ȣ            �����");
		System.out.println("==========================================================================");
		for(int i=0;i<order.size();i++){
			date = order.get(i).getOrderDate().getTime();
			int orderNo = order.get(i).getOrderNumber();
			String name = order.get(i).getCustomer().getName();
			String phone = order.get(i).getCustomer().getPhone();
			System.out.printf("%-8d %19s  %-8s %-19s %-10s\r\n",orderNo, sf.format(date), name, phone, "Sil");
		}
		System.out.println();
		System.out.println("���ֹ�ȣ�� �Է��Ͻø� �ڼ��� �������� Ȯ���Ͻ� �� �ֽ��ϴ�.");
		System.out.println(" -- ���� �Է����� ������ ����ȭ������ �̵��մϴ�.");
		System.out.print("[���ֹ�ȣ] > ");
		
		Scanner scan = new Scanner(System.in);
		String msg;
		msg = scan.nextLine().trim();
		if(msg.equals("")){
			return;
		}
		
		int no = Integer.valueOf(msg);
		for(int i=0;i<order.size();i++){
			int orderNo = order.get(i).getOrderNumber();
			if(no == orderNo){
				Order ord = order.get(i);
				orderDetailView(ord);
				break;
			}
		}
	}
	
	//���� ������ Ȯ���ϱ�
	public void orderDetailView(Order order){
		ArrayList<OrderDetail> orderDetails = order.getOrderDetails();
		
		System.out.println();
		System.out.println("��ǰ��    ��ǰ�ڵ�  â���̸�       ����  ������  ����");
		for(int i=0;i<orderDetails.size();i++){
			OrderDetail ord = orderDetails.get(i);
			String name = ord.getProduct().getName();
			int code = ord.getProduct().getCode();
			String warehouse = ord.getWarehouse();
			int amount = ord.getAmount();
			int discountRate = ord.getDiscountRate();
			int price = ord.getProduct().getPrice();
			int totalprice = 0;
			if(discountRate != 0){
				totalprice = amount*(price-(price/discountRate));
			}else{
				totalprice = amount * price;
			}
			
			System.out.printf("%-8s  %-8d  %-8s %-5d %5d%%  %-7d\r\n", name, code, warehouse, amount, discountRate, totalprice);
		}
	}
	
	////////////////////////////////////////////////////
	
	//���θ޴�
 	public int mainMenu(){
		Scanner scan = new Scanner(System.in);
		String msg;
		int select = 0;
		
		System.out.println();
		System.out.println("1. ���� �Է�"); //inputData(), inputOrder()
		System.out.println("2. ���� ����"); //cancelData()
		System.out.println("3. ���� ���"); //cancelData()
		System.out.println("4. ���� �̷�"); //viewData()
		System.out.println("5. ��ǰ ��� ��Ȳ"); //
		System.out.println("0. ���α׷� ����"); //
		System.out.println();
		System.out.print("�޴����� > "); //
		
		msg = scan.nextLine().trim();
		if(!msg.equals("") && msg.length()<=1){
			select = Integer.valueOf(msg);
		}else{
			select = -1;
		}
		return select;
	}
	
	
	////////////////////////////////////////////////////
	//��Ȯ��, ��ȸ����� ��� ���õ� UI
	
	//�� Ȯ��
	public Customer setCustomer(){
		Scanner scan = new Scanner(System.in);
		String[] msg;
		Customer customer;
		while(true){
			System.out.println();
			System.out.println("���������");
			System.out.println(" -- ��ϵ� ȸ���� ����� ������ member�� �Է��ϼ���.");
			System.out.println(" -- ó������ ���÷��� prev�� �Է��ϼ���.");
			System.out.println(" -- ��ȸ���� ��� ���� �Է����� �ʾƵ� �˴ϴ�.");
			System.out.println();
			System.out.print("����ȣ > "); //
			msg = scan.nextLine().trim().split(" ");
			
			if(msg.length > 1){
				System.out.println("�߸��� �Է� ���Դϴ�.");
				continue;
			}
			
			if(msg[0].equals("prev")){
				return null;
			}else if(msg[0].equals("member")){
				memberList();
				continue;
			}else if(msg[0].equals("")){
				customer = createCustomer();
				break;
			}else if(msg[0].matches("[^A-Za-z]*") && memberCheck(Integer.valueOf(msg[0]))){
				customer = getMember(Integer.valueOf(msg[0]));
				break;
			}else{
				System.out.println("�߸��� �Է� ���Դϴ�.");
				continue;
			}
			
		}
		return customer;
	}
	
	//��ȸ�� �� ���� �Է�
	public Customer createCustomer(){
		Customer customer = null;
		Scanner scan = new Scanner(System.in);
		String[] msg = null;
		while(true){
			System.out.println();
			System.out.println("����ȸ������ ���� �Է��� �����մϴ�.");
			System.out.println("���� ���� ������ ���� �� ������ �Է��ϼ���.");
			System.out.println(" -- ó������ ���÷��� prev�� �Է��ϼ���.");
			System.out.println();
			System.out.print("[�̸�] [�ּ�] [��ȭ��ȣ] > "); //
			msg = scan.nextLine().trim().split(" ");

			if(msg.length != 3){
				if(msg.length == 1 && msg[0].equals("prev")) return null;
				System.out.println("�߸��� �Է� ���Դϴ�.");
				continue;
			}else{
				if(memberCheck(msg[2])){
					System.out.println("������ ��ȭ��ȣ�� ���� ȸ���� �ֽ��ϴ�.");
					System.out.println("�̹� ��ϵ� ȸ������ Ȯ���ϼ���.");
					continue;
				}
				customer = new Customer(OrderSystem.cid, msg[0], msg[1], msg[2]);
				break;
			}
		}
		return customer;
	}
	
	//ȸ�� �˻�
	public Member getMember(int id){
		Member result = null;
		ArrayList<Member> mem = OrderSystem.members;
		for(int i=0;i<mem.size();i++){
			Member m = mem.get(i);
			if(m.getId() == id){
				result = m;
				break;
			}
		}
		return result;
	}

	//ȸ��Ȯ��
	public boolean memberCheck(int id){
		boolean result = false;
		ArrayList<Member> mem = OrderSystem.members;
		for(int i=0;i<mem.size();i++){
			Member m = mem.get(i);
			if(m.getId() == id){
				result = true;
				break;
			}
		}
		return result;
	}
	
	//�ߺ� ȸ��Ȯ��
	public boolean memberCheck(String phone){
		boolean result = false;
		ArrayList<Member> mem = OrderSystem.members;
		for(int i=0;i<mem.size();i++){
			Member m = mem.get(i);
			if(m.getPhone().equals(phone)){
				result = true;
				break;
			}
		}
		return result;
	}
	
	////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////
	//���� �Է�, ��ϰ� ���õ� UI
	
	//���ָ� �Է��Ѵ�(��������) ���� ���� �ۼ��ϴ� ����
	public void inputOrder(Order order, Customer customer){
		Scanner scan = new Scanner(System.in);
		String msg;
		productList();
		
		while(true){
			OrderDetail orderDetail = new OrderDetail(order);
			System.out.println();
			System.out.println("��ǰ�ڵ带 �Է��Ͻø� ��� Ȯ���� �� �ֽ��ϴ�.");
			System.out.println(" -- ��ǰ����� ���÷��� list�� �Է��ϼ���.");
			System.out.println(" -- �� �ۼ��� ����Ͻ÷��� exit�� �Է��ϼ���.");
			System.out.println();
			System.out.print("��ǰ�ڵ� > ");
			msg = scan.nextLine().trim();
			
			if(msg.equals("")){
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}else if(msg.equals("exit")){
				return;
			}else if(msg.equals("list")){
				productList();
				continue;
			}else if(msg.matches("[A-Za-z]*") || !OrderSystem.products.containsKey(Integer.valueOf(msg))){
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
			
			Product product = OrderSystem.products.get(Integer.valueOf(msg));
			if(setOrderDetail(orderDetail, product)){
				order.getOrderDetails().add(orderDetail);
				product.getOrderDetails().add(orderDetail);
				customer.getOrders().add(order);
			}else{
				System.out.println("���� �� �ۼ��� ����߽��ϴ�.");
				return;
			}
			
			System.out.println();
			System.out.println("����ؼ� "+customer.getName()+" ȸ���� ���� ���� �ۼ� �Ͻðڽ��ϱ�?");
			System.out.print("Yes(anyone) / No(n) > ");
			String text = scan.nextLine().trim();
			if(text.equals("n")) break;
		}
	}
	
	//���� �� ���� ���
	public boolean setOrderDetail(OrderDetail orderDetail, Product product){
		boolean result = false;
		productAmount(product);
		
		Scanner scan = new Scanner(System.in);
		String[] msg;
		while(true){
			System.out.println();
			System.out.println("â���ȣ�� ������ �Է��ϼ���");
			System.out.println(" -- �������� ���÷��� prev�� �Է��ϼ���.");
			System.out.println();
			System.out.print("[â���ȣ] [����] > ");
			msg = scan.nextLine().trim().split(" ");
			
			if(msg.length < 2 && msg[0].equals("prev")){
				break;
			}else if(msg.length < 2){
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
			
			
			if(!msg[0].matches("[^A-Za-z]*") && !msg[1].matches("[^A-Za-z]*")){
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}else if(msg[0].matches("[^A-Za-z]*") && Integer.valueOf(msg[0])>product.getWarehouses().size()){
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
			
			Warehouse place = product.getWarehouses().get(Integer.valueOf(msg[0])-1);
			String warehouse = place.getName();
			int amount = Integer.valueOf(msg[1]);
			if(!amountCheck(product, warehouse, amount)){
				System.out.print("��ǰ�� ��� �����մϴ�.");
				continue;
			}else{
				orderDetail.register(product, warehouse, amount);
				orderDetail.info();
				setDiscountRate(orderDetail);
				amountChange(product, warehouse, amount);
				result = true;
				break;
			}
		}
		return result;
	}
	
	//������ ����
	public void setDiscountRate(OrderDetail orderDetail){
		Scanner scan = new Scanner(System.in);
		String msg;
		
		while(true){
			System.out.println();
			System.out.println("�������� �����Ͻðڽ��ϱ�?");
			System.out.print("Yes('y') / No(<enter> or 'n') > ");
			msg = scan.nextLine().trim();
			
			if(msg.equals("") || msg.equals("n")){
				break;
			}else if(msg.equals("y")){
				System.out.println();
				System.out.println("������ �������� �Է��ϼ���.");
				System.out.println(" -- ���� �Է����� ������ �⺻ �������� ����˴ϴ�.");
				System.out.println();
				System.out.print("(����:%) > ");
				String text = scan.nextLine().trim();
				if(text.matches("^[1-9][0-9]*")){
					int discount = Integer.valueOf(text);
					orderDetail.setDiscountRate(discount);
				}
				orderDetail.info();
				break;
			}else{
				System.out.println();
				System.out.println("�߸��� �Է��Դϴ�.");
				continue;
			}
		}
	}
	
	//��� ����
	public void amountChange(Product product, String warehouse, int amount){
		ArrayList<Warehouse> arrWs = product.getWarehouses();
		
		for(int i=0;i<arrWs.size();i++){
			if(arrWs.get(i).getName().equals(warehouse)){
				Warehouse ws = arrWs.get(i);
				ws.release(product, amount);
				break;
			}
		}
	}
	
	//�ֹ� ��ҽ� ��ǰ ��� ����
	public void rollback(Order order){
		ArrayList<OrderDetail> ord = order.getOrderDetails();
		for(int i=0;i<ord.size();i++){
			OrderDetail od = ord.get(i);
			
			Product product = od.getProduct();
			int amount = od.getAmount();
			String warehouse = od.getWarehouse();
			ArrayList<Warehouse> arrWs = product.getWarehouses();
			
			for(int j=0;j<arrWs.size();j++){
				if(arrWs.get(j).getName().equals(warehouse)){
					Warehouse ws = arrWs.get(j);
					ws.stock(product, amount);
					break;
				}
			}
		}
	}
	
	//��ǰ�� Ư��â���� ��� Ȯ��
	public boolean amountCheck(Product product, String warehouse, int amount){
		boolean result = false;
		int num = 0;
		for(int i=0;i<product.getWarehouses().size();i++){
			if(product.getWarehouses().get(i).getName().equals(warehouse)){
				num = product.getWarehouses().get(i).getStock().getAmount(product);
				break;
			}
		}	

		if(num >= amount){
			result = true;
		}	
		return result;
	}
	
	//���ָ� ����Ѵ�
	public void registerOrder(){
		Customer customer = setCustomer();
		if(customer == null) return;
		Order order = new Order();
		order.setCustomer(customer);
		
		System.out.println();
		System.out.println(customer.getName() + "ȸ���� ���� ���� �ۼ��մϴ�.");
		Scanner scan = new Scanner(System.in);
		while(true){
			inputOrder(order, customer);
			if(order.getOrderDetails().size()>0){
				order.register(customer);
			}
			break;
		}


		//���������� DB�� ���Ͽ� ���ָ� ����ϴ� �ܰ��̴�.
		//�ӽ÷� static �ڿ��� ArrayList<Order> orders�� �߰��Ͽ� ���� ȿ���� ������ �ߴ�.
		//OrderSystem.orders.add();
	}
	
	//////////////////////////////////////////////////////////////////
	

	//////////////////////////////////////////////////////////////////
	
	
	//���ָ� ����Ѵ�
	public void cancelOrder(){
		Scanner scan = new Scanner(System.in);
		
		while(true){
			if(OrderSystem.orders.size() == 0){
				System.out.println();
				System.out.println("���̻� ����� ���� �����Ͱ� �������� �ʽ��ϴ�.");
				break;
			}
			
			SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			Date date;
			ArrayList<Order> order = OrderSystem.orders;
			System.out.println();
			System.out.println("���ֹ�ȣ ���ֳ�¥             ����   ����ȣ            �����");
			System.out.println("==========================================================================");
			for(int i=0;i<order.size();i++){
				date = order.get(i).getOrderDate().getTime();
				int orderNo = order.get(i).getOrderNumber();
				String name = order.get(i).getCustomer().getName();
				String phone = order.get(i).getCustomer().getPhone();
				System.out.printf("%-8d %19s  %-8s %-19s %-10s\r\n",orderNo, sf.format(date), name, phone, "Sil");
			}
			
			System.out.println();
			System.out.println("����� ���ֹ�ȣ�� �Է��ϼ���.");
			System.out.println(" -- ���� ����� ���÷��� list�� �Է��ϼ���.");
			System.out.println(" -- ����ȭ������ ���÷��� exit�� �Է��ϼ���.");
			System.out.print("���ֹ�ȣ > ");
			String msg = scan.nextLine();
			
			if(msg.equals("list")){
				continue;
			}
			if(msg.equals("exit")){
				break;
			}
			
			int sel = Integer.valueOf(msg);
			
			for(int i=0;i<OrderSystem.orders.size();i++){
				if(OrderSystem.orders.get(i).getOrderNumber() == sel){
					OrderSystem.orders.remove(i);
					break;
				}
			}
			
			System.out.println();
			System.out.println("������Ұ� �ݿ��Ǿ����ϴ�.");
			
			
		}
	}
	
	
}
