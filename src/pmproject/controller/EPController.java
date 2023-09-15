package pmproject.controller;

import java.util.List;
import java.util.Scanner;

import pmproject.service.EPService;
import pmproject.service.EPServiceImp;
import pmproject.vo.MemberVO;

public class EPController {
	
	private EPService epService = new EPServiceImp();
	private static TimeoffController timeoffController = new TimeoffController();
	
	public void run() {
		int menu;
		final int EXIT = 4;
		Scanner sc = new Scanner(System.in);
		
		do {
			EPprintMenu();
			menu = sc.nextInt();
			runMenu(menu);
			System.out.println("=================");
		}while(menu != EXIT);
		
	}
	private void runMenu(int menu) {
		switch(menu) {
		case 1:
			Inquiry();
			break;
		case 2:
			Resignation();
			break;
		case 3:
			timeoffController.timeoff();
			break;
		case 4:
			System.out.println("[�ڷΰ���]");
			break;
		default:
			System.out.println("[�߸��� �޴� �Է�]");
		
		}
	}
	
	private void Resignation() {
		Scanner sc = new Scanner(System.in);
		System.out.print("���� ��ȣ : ");
		int id = sc.nextInt();
		System.out.print("������ : ");
		String name = sc.next();
		
		MemberVO ep = new MemberVO(id, name);
		if(epService.deleteEP(ep)) {
			System.out.println("[���� ���� ���� ����]");
		}else {
			System.out.println("[���� ���� ���� ����]");
		}
		
	}

	private void Inquiry() {
		List<MemberVO> epList = epService.getEPList();
		for(MemberVO tmp : epList) {
			System.out.println(tmp);
		}
	
	}
	private static void EPprintMenu() {
		System.out.println("=====�Խ��Ǹ޴�=====");
		System.out.println("1. ���� ��ü ��ȸ");
		System.out.println("2. ���� ��� ����");
		System.out.println("3. ���� �ް�/���� ����");
		System.out.println("4. �ڷΰ���");
		System.out.println("=================");
		System.out.print("�޴� ���� : ");
	}
	
}

