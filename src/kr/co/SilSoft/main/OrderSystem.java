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
/*
 * Git 시작!
 * */

public class OrderSystem {
	
	static public ArrayList<Order> orders;//�ֹ���� - �ֹ������ ���⿡ �߰�
	
	//Data Loading...
	static public HashMap<Integer, Product> products; //��ǰ���
	static public ArrayList<Warehouse> warehouses;//â����
	
	//Data Making...
	static public HashMap<Integer, Customer> customers; //ȸ����� - ȸ���� ���⿡ �߰�
	static public ArrayList<Member> members;
	
	public static int cid; //����ȣ
	public static int orderNumber; //�ֹ���ȣ
	
	
	public OrderSystem(){
		init();
	}
	
	public void init(){
		cid = 10422401;
		orderNumber = 22412;
		orders = new ArrayList<Order>();
		
		//ȸ�� ���
		members = new ArrayList<Member>();
		members.add(new Member(71020245, "Chang", "Korea", "+82-010-3424-2212"));
		members.add(new Member(71050273, "David", "America", "+1-224-0632-9963"));
		members.add(new Member(71080639, "Rionel", "France", "+33-225-354-6373"));
		
		//������ ���
		customers = new HashMap<Integer, Customer>();

		//��ǰ ����
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
		
		//â�� ����
		warehouses = new ArrayList<Warehouse>();
		warehouses.add(new Warehouse("���չ���â��", "����", "031-3324-4251"));
		warehouses.add(new Warehouse("��ȯ����â��", "����", "031-5525-4251"));
		warehouses.add(new Warehouse("���ι���â��", "��õ", "032-7905-4251"));
		warehouses.add(new Warehouse("���ι���â��", "����", "062-9492-4251"));
		
		//â�� ������ �߰�
		//���չ���â��
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
		
		//��ȯ����â��
		warehouses.get(1).stock(products.get(1021), 152);
		warehouses.get(1).stock(products.get(1022), 226);
		warehouses.get(1).stock(products.get(1023), 178);
		warehouses.get(1).stock(products.get(1024), 103);
		warehouses.get(1).stock(products.get(1025), 213);
		warehouses.get(1).stock(products.get(1026), 209);
		warehouses.get(1).stock(products.get(1028), 176);
		warehouses.get(1).stock(products.get(1029), 78);
		
		//���ι���â��
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
		
		//���ι���â��
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
		
		//��ǰ�� ����� ������
		//ui.productList(os.products);
		
		//��ǰ�� ��� ������
		//ui.productAmount(os.products.get(1027));
		
		//ȸ������� ���� ��
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
				case 1: //���� �Է�
					ui.registerOrder();
					break;
				case 2: //���� ����
					System.out.println();
					System.out.println("���� �������� �ʴ� ����Դϴ�.");
					break;
				case 3: //���� ���
					if(orders.size()>0){
						ui.cancelOrder();
					}else{
						System.out.println();
						System.out.println("���� �̷��� �������� �ʽ��ϴ�.");
					}
					break;
				case 4: //���� �̷�
					if(orders.size()>0){
						ui.orderInfo();
					}else{
						System.out.println();
						System.out.println("���� �̷��� �������� �ʽ��ϴ�.");
					}
					break;
				case 5: //��ǰ ��� ��Ȳ
					ui.menuProductAmount();
					break;
				case 0:
					System.out.println();
					System.out.println("���α׷��� �����մϴ�.");
					System.exit(0);
					break;
				default:
					System.out.println();
					System.out.println("�ùٸ��� ���� �Է��Դϴ�.");
					break;
			}
		}
	}
}
