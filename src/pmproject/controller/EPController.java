package pmproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import pmproject.service.EPService;
import pmproject.service.EPServiceImp;
import pmproject.service.MemberService;
import pmproject.service.MemberServiceImp;
import pmproject.vo.MemberVO;
import pmproject.vo.SalaryVO;

public class EPController {
	
	private EPService epService = new EPServiceImp();
	private MemberService memberService = new MemberServiceImp();
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
		System.out.print("퇴사처리할 직원 사번 : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[직원 조회 실패]");
		} else if(memberService.selectMember(id).getEp_st_num() == 2){
			System.out.println("[이미 퇴직처리 된 직원입니다.]");
		} else {
			MemberVO dbMember = memberService.selectMember(id);
			System.out.println("[직원 조회결과]");
			System.out.println("사번 : " + dbMember.getEp_id());
			System.out.println("이름 : " + dbMember.getEp_name());
			System.out.println("이메일 : " + dbMember.getEp_email());
			System.out.println("연락처 : " + dbMember.getEp_phone_num());
			if(epService.deleteEP(dbMember)) {
				System.out.println("[직원 퇴사 처리 완료]");
			}else {
				System.out.println("[직원 퇴사 처리 실패]");
			}
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
		System.out.println("2. 직원 퇴사 처리");
		System.out.println("3. 직원 휴가/퇴사 관리");
		System.out.println("4. 뒤로가기");
		System.out.println("=================");
		System.out.print("메뉴 선택 : ");
	}
	
}

