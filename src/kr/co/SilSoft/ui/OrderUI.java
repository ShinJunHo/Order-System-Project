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

	//상품목록
	public void productList(){
		HashMap<Integer, Product> products = OrderSystem.products;
		Set<Integer> key = products.keySet();
		Iterator<Integer> it = key.iterator();
		
		System.out.println();
		System.out.println(" 상품코드  상품명         단가 ");
		System.out.println("==================================");
		while(it.hasNext()){
			Product p = products.get(it.next());
			System.out.printf("%8d   %-12s   %6d원\r\n", p.getCode(), p.getName(), p.getPrice());
		}
		System.out.println();
	}
	
	//상품 재고 조회 메뉴
	public void menuProductAmount(){
		Scanner scan = new Scanner(System.in);
		String msg;
		while(true){
			System.out.println();
			System.out.println("1. 전체 재고 현황");
			System.out.println("2. 상품별 재고 현황");
			System.out.println("3. 창고별 재고 현황");
			System.out.println("5. 처음으로");
			System.out.println();
			System.out.print("메뉴 선택 > ");
			msg = scan.nextLine();
			
			switch(Integer.valueOf(msg)){
				case 1:
					viewProductsTotalAmount();
					break;
				case 2:
					productList();
					System.out.print("상품번호 > ");
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
					System.out.println("잘못된 입력입니다.");
					break;
			}
		}
	}
	
	//창고별 재고현황 확인
	public void amountWarehouse(){
		ArrayList<Warehouse> warehouses = OrderSystem.warehouses;
		Stock stock = null;
		Scanner scan = new Scanner(System.in);
		
		System.out.println();
		System.out.println("창고목록");
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
			
			System.out.println(" -- 창고목록을 보시려면 list를 입력하세요.");
			System.out.println(" -- 이전화면으로 나가시려면 exit를 입력하세요.");
			System.out.print("창고 번호 > ");
			String msg = scan.nextLine();
			
			if(msg.equals("exit")){
				return;
			}
			
			if(msg.equals("list")){
				System.out.println();
				System.out.println("창고목록");
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
			System.out.println(wa.getName() + "의 재고 현황");
			System.out.println();
			System.out.println("상품명        수량");
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
	
	//상품의 전체 재고 확인
	public void viewProductsTotalAmount(){
		HashMap<Integer, Product> products = OrderSystem.products;
		Set<Integer> key = products.keySet();
		Iterator<Integer> it = key.iterator();
		
		System.out.println();
		System.out.println(" 상품코드  상품명         단가      수량");
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
			System.out.printf("%8d   %-12s   %6d원  %5d\r\n", code, name, price, sum);
			
		}
		System.out.println();
	}
	
	//상품별 재고현황
	public void productAmount(Product product){
		ArrayList<Warehouse> warehouse = product.getWarehouses();
		int sum = 0;
		
		System.out.println();
		System.out.println(product.getName()+"["+product.getCode()+"] 의 재고현황");
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
	
	//회원목록
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

	//수주이력 조회
	public void orderInfo(){
		Scanner scan = new Scanner(System.in);
		String msg;
		while(true){
			System.out.println();
			System.out.println("1. 전체 수주 이력 조회");
			System.out.println("2. 상품별 수주 이력 조회");
			System.out.println("3. 회원별 수주 이력 조회");
			System.out.println("4. 날짜별 수주 이력 조회");
			System.out.println("5. 처음으로");
			System.out.println();
			System.out.print("메뉴 선택 > ");
			msg = scan.nextLine();
			
			switch(Integer.valueOf(msg)){
				case 1:
					orderList();
					break;
				case 2:
//					System.out.println();
//					System.out.println("현재 지원하지 않는 메뉴입니다.");
//					break;
				case 3:
//					System.out.println();
//					System.out.println("현재 지원하지 않는 메뉴입니다.");
//					break;
				case 4:
					System.out.println();
					System.out.println("현재 지원하지 않는 메뉴입니다.");
					break;
				case 5:
					return;
				default :
					System.out.println();
					System.out.println("잘못된 입력입니다.");
					break;
			}
		}
	}
	
	//전체 수주이력 확인하기
	public void orderList(){
		SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Date date;
		ArrayList<Order> order = OrderSystem.orders;
		System.out.println();
		System.out.println("수주번호 수주날짜             고객명   고객번호            담당자");
		System.out.println("==========================================================================");
		for(int i=0;i<order.size();i++){
			date = order.get(i).getOrderDate().getTime();
			int orderNo = order.get(i).getOrderNumber();
			String name = order.get(i).getCustomer().getName();
			String phone = order.get(i).getCustomer().getPhone();
			System.out.printf("%-8d %19s  %-8s %-19s %-10s\r\n",orderNo, sf.format(date), name, phone, "Sil");
		}
		System.out.println();
		System.out.println("수주번호를 입력하시면 자세한 명세내역를 확인하실 수 있습니다.");
		System.out.println(" -- 값을 입력하지 않으면 이전화면으로 이동합니다.");
		System.out.print("[수주번호] > ");
		
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
	
	//수주 명세내역 확인하기
	public void orderDetailView(Order order){
		ArrayList<OrderDetail> orderDetails = order.getOrderDetails();
		
		System.out.println();
		System.out.println("상품명    상품코드  창고이름       수량  할인율  가격");
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
	
	//메인메뉴
 	public int mainMenu(){
		Scanner scan = new Scanner(System.in);
		String msg;
		int select = 0;
		
		System.out.println();
		System.out.println("1. 수주 입력"); //inputData(), inputOrder()
		System.out.println("2. 수주 수정"); //cancelData()
		System.out.println("3. 수주 취소"); //cancelData()
		System.out.println("4. 수주 이력"); //viewData()
		System.out.println("5. 상품 재고 현황"); //
		System.out.println("0. 프로그램 종료"); //
		System.out.println();
		System.out.print("메뉴선택 > "); //
		
		msg = scan.nextLine().trim();
		if(!msg.equals("") && msg.length()<=1){
			select = Integer.valueOf(msg);
		}else{
			select = -1;
		}
		return select;
	}
	
	
	////////////////////////////////////////////////////
	//고객확인, 비회원등록 등과 관련된 UI
	
	//고객 확인
	public Customer setCustomer(){
		Scanner scan = new Scanner(System.in);
		String[] msg;
		Customer customer;
		while(true){
			System.out.println();
			System.out.println("고객정보등록");
			System.out.println(" -- 등록된 회원의 목록을 보려면 member를 입력하세요.");
			System.out.println(" -- 처음으로 가시려면 prev를 입력하세요.");
			System.out.println(" -- 비회원인 경우 값을 입력하지 않아도 됩니다.");
			System.out.println();
			System.out.print("고객번호 > "); //
			msg = scan.nextLine().trim().split(" ");
			
			if(msg.length > 1){
				System.out.println("잘못된 입력 값입니다.");
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
				System.out.println("잘못된 입력 값입니다.");
				continue;
			}
			
		}
		return customer;
	}
	
	//비회원 고객 정보 입력
	public Customer createCustomer(){
		Customer customer = null;
		Scanner scan = new Scanner(System.in);
		String[] msg = null;
		while(true){
			System.out.println();
			System.out.println("비등록회원으로 수주 입력을 진행합니다.");
			System.out.println("수주 정보 관리를 위한 고객 정보를 입력하세요.");
			System.out.println(" -- 처음으로 가시려면 prev를 입력하세요.");
			System.out.println();
			System.out.print("[이름] [주소] [전화번호] > "); //
			msg = scan.nextLine().trim().split(" ");

			if(msg.length != 3){
				if(msg.length == 1 && msg[0].equals("prev")) return null;
				System.out.println("잘못된 입력 값입니다.");
				continue;
			}else{
				if(memberCheck(msg[2])){
					System.out.println("동일한 전화번호를 가진 회원이 있습니다.");
					System.out.println("이미 등록된 회원인지 확인하세요.");
					continue;
				}
				customer = new Customer(OrderSystem.cid, msg[0], msg[1], msg[2]);
				break;
			}
		}
		return customer;
	}
	
	//회원 검색
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

	//회원확인
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
	
	//중복 회원확인
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
	//수주 입력, 등록과 관련된 UI
	
	//수주를 입력한다(수주정보) 수주 명세를 작성하는 과정
	public void inputOrder(Order order, Customer customer){
		Scanner scan = new Scanner(System.in);
		String msg;
		productList();
		
		while(true){
			OrderDetail orderDetail = new OrderDetail(order);
			System.out.println();
			System.out.println("상품코드를 입력하시면 재고를 확인할 수 있습니다.");
			System.out.println(" -- 상품목록을 보시려면 list를 입력하세요.");
			System.out.println(" -- 명세 작성을 취소하시려면 exit를 입력하세요.");
			System.out.println();
			System.out.print("상품코드 > ");
			msg = scan.nextLine().trim();
			
			if(msg.equals("")){
				System.out.println("잘못된 입력입니다.");
				continue;
			}else if(msg.equals("exit")){
				return;
			}else if(msg.equals("list")){
				productList();
				continue;
			}else if(msg.matches("[A-Za-z]*") || !OrderSystem.products.containsKey(Integer.valueOf(msg))){
				System.out.println("잘못된 입력입니다.");
				continue;
			}
			
			Product product = OrderSystem.products.get(Integer.valueOf(msg));
			if(setOrderDetail(orderDetail, product)){
				order.getOrderDetails().add(orderDetail);
				product.getOrderDetails().add(orderDetail);
				customer.getOrders().add(order);
			}else{
				System.out.println("수주 명세 작성을 취소했습니다.");
				return;
			}
			
			System.out.println();
			System.out.println("계속해서 "+customer.getName()+" 회원의 수주 명세를 작성 하시겠습니까?");
			System.out.print("Yes(anyone) / No(n) > ");
			String text = scan.nextLine().trim();
			if(text.equals("n")) break;
		}
	}
	
	//수주 명세 정보 등록
	public boolean setOrderDetail(OrderDetail orderDetail, Product product){
		boolean result = false;
		productAmount(product);
		
		Scanner scan = new Scanner(System.in);
		String[] msg;
		while(true){
			System.out.println();
			System.out.println("창고번호와 수량을 입력하세요");
			System.out.println(" -- 이전으로 가시려면 prev를 입력하세요.");
			System.out.println();
			System.out.print("[창고번호] [수량] > ");
			msg = scan.nextLine().trim().split(" ");
			
			if(msg.length < 2 && msg[0].equals("prev")){
				break;
			}else if(msg.length < 2){
				System.out.println("잘못된 입력입니다.");
				continue;
			}
			
			
			if(!msg[0].matches("[^A-Za-z]*") && !msg[1].matches("[^A-Za-z]*")){
				System.out.println("잘못된 입력입니다.");
				continue;
			}else if(msg[0].matches("[^A-Za-z]*") && Integer.valueOf(msg[0])>product.getWarehouses().size()){
				System.out.println("잘못된 입력입니다.");
				continue;
			}
			
			Warehouse place = product.getWarehouses().get(Integer.valueOf(msg[0])-1);
			String warehouse = place.getName();
			int amount = Integer.valueOf(msg[1]);
			if(!amountCheck(product, warehouse, amount)){
				System.out.print("상품의 재고가 부족합니다.");
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
	
	//할인율 설정
	public void setDiscountRate(OrderDetail orderDetail){
		Scanner scan = new Scanner(System.in);
		String msg;
		
		while(true){
			System.out.println();
			System.out.println("할인율을 변경하시겠습니까?");
			System.out.print("Yes('y') / No(<enter> or 'n') > ");
			msg = scan.nextLine().trim();
			
			if(msg.equals("") || msg.equals("n")){
				break;
			}else if(msg.equals("y")){
				System.out.println();
				System.out.println("변경할 할인율을 입력하세요.");
				System.out.println(" -- 값을 입력하지 않으면 기본 할인율로 적용됩니다.");
				System.out.println();
				System.out.print("(단위:%) > ");
				String text = scan.nextLine().trim();
				if(text.matches("^[1-9][0-9]*")){
					int discount = Integer.valueOf(text);
					orderDetail.setDiscountRate(discount);
				}
				orderDetail.info();
				break;
			}else{
				System.out.println();
				System.out.println("잘못된 입력입니다.");
				continue;
			}
		}
	}
	
	//재고량 변경
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
	
	//주문 취소시 상품 재고량 변경
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
	
	//상품의 특정창고의 재고 확인
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
	
	//수주를 등록한다
	public void registerOrder(){
		Customer customer = setCustomer();
		if(customer == null) return;
		Order order = new Order();
		order.setCustomer(customer);
		
		System.out.println();
		System.out.println(customer.getName() + "회원의 수주 명세를 작성합니다.");
		Scanner scan = new Scanner(System.in);
		while(true){
			inputOrder(order, customer);
			if(order.getOrderDetails().size()>0){
				order.register(customer);
			}
			break;
		}


		//최종적으로 DB나 파일에 수주를 등록하는 단계이다.
		//임시로 static 자원인 ArrayList<Order> orders에 추가하여 같은 효과를 내도록 했다.
		//OrderSystem.orders.add();
	}
	
	//////////////////////////////////////////////////////////////////
	

	//////////////////////////////////////////////////////////////////
	
	
	//수주를 취소한다
	public void cancelOrder(){
		Scanner scan = new Scanner(System.in);
		
		while(true){
			if(OrderSystem.orders.size() == 0){
				System.out.println();
				System.out.println("더이상 취소할 수주 데이터가 존재하지 않습니다.");
				break;
			}
			
			SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			Date date;
			ArrayList<Order> order = OrderSystem.orders;
			System.out.println();
			System.out.println("수주번호 수주날짜             고객명   고객번호            담당자");
			System.out.println("==========================================================================");
			for(int i=0;i<order.size();i++){
				date = order.get(i).getOrderDate().getTime();
				int orderNo = order.get(i).getOrderNumber();
				String name = order.get(i).getCustomer().getName();
				String phone = order.get(i).getCustomer().getPhone();
				System.out.printf("%-8d %19s  %-8s %-19s %-10s\r\n",orderNo, sf.format(date), name, phone, "Sil");
			}
			
			System.out.println();
			System.out.println("취소할 수주번호를 입력하세요.");
			System.out.println(" -- 수주 목록을 보시려면 list를 입력하세요.");
			System.out.println(" -- 이전화면으로 가시려면 exit를 입력하세요.");
			System.out.print("수주번호 > ");
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
			System.out.println("수주취소가 반영되었습니다.");
			
			
		}
	}
	
	
}
