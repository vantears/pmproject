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
			System.out.println("[�ڷΰ���]");
			break;
		default:
			System.out.println("[�߸��� �޴� �Է�]");
		
		}
	}
	
	private void Resignation() {
		Scanner sc = new Scanner(System.in);
		System.out.print("���ó���� ���� ��� : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[���� ��ȸ ����]");
		} else if(memberService.selectMember(id).getEp_st_num() == 2){
			System.out.println("[�̹� ����ó�� �� �����Դϴ�.]");
		} else {
			MemberVO dbMember = memberService.selectMember(id);
			System.out.println("[���� ��ȸ���]");
			System.out.println("��� : " + dbMember.getEp_id());
			System.out.println("�̸� : " + dbMember.getEp_name());
			System.out.println("�̸��� : " + dbMember.getEp_email());
			System.out.println("����ó : " + dbMember.getEp_phone_num());
			if(epService.deleteEP(dbMember)) {
				System.out.println("[���� ��� ó�� �Ϸ�]");
			}else {
				System.out.println("[���� ��� ó�� ����]");
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
		System.out.println("=====�Խ��Ǹ޴�=====");
		System.out.println("1. ���� ��ü ��ȸ");
		System.out.println("2. ���� ��� ó��");
		System.out.println("3. ���� �ް�/��� ����");
		System.out.println("4. �ڷΰ���");
		System.out.println("=================");
		System.out.print("�޴� ���� : ");
	}
	
}

