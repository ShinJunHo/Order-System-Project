package kr.co.SilSoft.main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import kr.co.SilSoft.obj.Customer;
import kr.co.SilSoft.obj.Member;
import kr.co.SilSoft.obj.Order;
import kr.co.SilSoft.obj.Product;
import kr.co.SilSoft.obj.Warehouse;
import kr.co.SilSoft.ui.OrderUI;


public class OrderSystem {
	
	static public ArrayList<Order> orders;//주문목록 - 주문목록을 여기에 추가
	
	//Data Loading...
	static public HashMap<Integer, Product> products; //상품목록
	static public ArrayList<Warehouse> warehouses;//창고목록
	
	//Data Making...
	static public HashMap<Integer, Customer> customers; //회원목록 - 회원을 여기에 추가
	static public ArrayList<Member> members;
	
	public static int cid; //고객번호
	public static int orderNumber; //주문번호
	
	
	public OrderSystem(){
		init();
	}
	
	public void init(){
		cid = 10422401;
		orderNumber = 22412;
		orders = new ArrayList<Order>();
		
		//회원 목록
		members = new ArrayList<Member>();
		members.add(new Member(71020245, "Chang", "Korea", "+82-010-3424-2212"));
		members.add(new Member(71050273, "David", "America", "+1-224-0632-9963"));
		members.add(new Member(71080639, "Rionel", "France", "+33-225-354-6373"));
		
		//발주자 목록
		customers = new HashMap<Integer, Customer>();

		//상품 설정
		products = new HashMap<Integer, Product>();
		products.put(1021, new Product(1021, "Apple", 1000));
		products.put(1022, new Product(1022, "Orange", 1000));
		products.put(1023, new Product(1023, "WaterMelon", 1000));
		products.put(1024, new Product(1024, "Banana", 1000));
		products.put(1025, new Product(1025, "Peach", 1000));
		products.put(1026, new Product(1026, "Cherry", 1000));
		products.put(1027, new Product(1027, "Grape", 1000));
		products.put(1028, new Product(1028, "Strawberry", 1000));
		products.put(1029, new Product(1029, "Melon", 1000));
		products.put(1030, new Product(1030, "Pear", 1000));
		
		//창고 설정
		warehouses = new ArrayList<Warehouse>();
		warehouses.add(new Warehouse("종합물류창고", "수원", "031-3324-4251"));
		warehouses.add(new Warehouse("순환물류창고", "광명", "031-5525-4251"));
		warehouses.add(new Warehouse("서부물류창고", "인천", "032-7905-4251"));
		warehouses.add(new Warehouse("남부물류창고", "광주", "062-9492-4251"));
		
		//창고에 물건을 추가
		//종합물류창고
		warehouses.get(0).stock(products.get(1021), 563);
		warehouses.get(0).stock(products.get(1022), 557);
		warehouses.get(0).stock(products.get(1023), 623);
		warehouses.get(0).stock(products.get(1024), 378);
		warehouses.get(0).stock(products.get(1025), 743);
		warehouses.get(0).stock(products.get(1026), 332);
		warehouses.get(0).stock(products.get(1027), 665);
		warehouses.get(0).stock(products.get(1028), 341);
		warehouses.get(0).stock(products.get(1029), 684);
		warehouses.get(0).stock(products.get(1030), 378);
		
		//순환물류창고
		warehouses.get(1).stock(products.get(1021), 152);
		warehouses.get(1).stock(products.get(1022), 226);
		warehouses.get(1).stock(products.get(1023), 178);
		warehouses.get(1).stock(products.get(1024), 103);
		warehouses.get(1).stock(products.get(1025), 213);
		warehouses.get(1).stock(products.get(1026), 209);
		warehouses.get(1).stock(products.get(1028), 176);
		warehouses.get(1).stock(products.get(1029), 78);
		
		//서부물류창고
		warehouses.get(2).stock(products.get(1021), 341);
		warehouses.get(2).stock(products.get(1022), 273);
		warehouses.get(2).stock(products.get(1023), 351);
		warehouses.get(2).stock(products.get(1024), 231);
		warehouses.get(2).stock(products.get(1025), 225);
		warehouses.get(2).stock(products.get(1026), 304);
		warehouses.get(2).stock(products.get(1027), 421);
		warehouses.get(2).stock(products.get(1028), 329);
		warehouses.get(2).stock(products.get(1029), 167);
		warehouses.get(2).stock(products.get(1030), 328);
		
		//남부물류창고
		warehouses.get(3).stock(products.get(1022), 174);
		warehouses.get(3).stock(products.get(1023), 321);
		warehouses.get(3).stock(products.get(1024), 121);
		warehouses.get(3).stock(products.get(1025), 895);
		warehouses.get(3).stock(products.get(1027), 90);
		warehouses.get(3).stock(products.get(1028), 502);
		warehouses.get(3).stock(products.get(1030), 124);
	}
			
	public static void main(String[] args) {
		OrderSystem os = new OrderSystem();
		OrderUI ui = new OrderUI();
		
		//상품의 목록을 얻을때
		//ui.productList(os.products);
		
		//상품의 재고를 얻을때
		//ui.productAmount(os.products.get(1027));
		
		//회원목록을 얻을 때
		//ui.memberList(os.members);
		
		System.out.println("==================================================");
		System.out.println();
		System.out.println("              Sil Order Manager 1.0");
		System.out.println();
		System.out.println("==================================================");
		System.out.println("# Made by ChangSungSil, 2014-04-30");
		System.out.println();

		while(true){
			switch(ui.mainMenu()){
				case 1: //수주 입력
					ui.registerOrder();
					break;
				case 2: //수주 수정
					System.out.println();
					System.out.println("현재 지원하지 않는 기능입니다.");
					break;
				case 3: //수주 취소
					if(orders.size()>0){
						ui.cancelOrder();
					}else{
						System.out.println();
						System.out.println("수주 이력이 존재하지 않습니다.");
					}
					break;
				case 4: //수주 이력
					if(orders.size()>0){
						ui.orderInfo();
					}else{
						System.out.println();
						System.out.println("수주 이력이 존재하지 않습니다.");
					}
					break;
				case 5: //상품 재고 현황
					ui.menuProductAmount();
					break;
				case 0:
					System.out.println();
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
					break;
				default:
					System.out.println();
					System.out.println("올바르지 않은 입력입니다.");
					break;
			}
		}
	}
}
