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
			System.out.println("[뒤로가기]");
			break;
		default:
			System.out.println("[잘못된 메뉴 입력]");
		
		}
	}
	
	private void Resignation() {
		Scanner sc = new Scanner(System.in);
		System.out.print("직원 번호 : ");
		int id = sc.nextInt();
		System.out.print("직원명 : ");
		String name = sc.next();
		
		MemberVO ep = new MemberVO(id, name);
		if(epService.deleteEP(ep)) {
			System.out.println("[직원 정보 삭제 성공]");
		}else {
			System.out.println("[직원 정보 삭제 실패]");
		}
		
	}

	private void Inquiry() {
		List<MemberVO> epList = epService.getEPList();
		for(MemberVO tmp : epList) {
			System.out.println(tmp);
		}
	
	}
	private static void EPprintMenu() {
		System.out.println("=====게시판메뉴=====");
		System.out.println("1. 직원 전체 조회");
		System.out.println("2. 직원 퇴사 관리");
		System.out.println("3. 직원 휴가/휴직 관리");
		System.out.println("4. 뒤로가기");
		System.out.println("=================");
		System.out.print("메뉴 선택 : ");
	}
	
}

