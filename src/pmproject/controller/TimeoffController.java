package pmproject.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;import pmproject.service.MemberService;
import pmproject.service.MemberServiceImp;
import pmproject.service.TimeoffService;
import pmproject.service.TimeoffServiceImp;
import pmproject.vo.LeaveVO;
import pmproject.vo.MemberVO;
import pmproject.vo.TimeoffVO;

public class TimeoffController {
	
	private TimeoffService timeoffService = new TimeoffServiceImp();
	private MemberService memberService = new MemberServiceImp();
	Scanner sc = new Scanner(System.in);
	
	public void timeoff() {
		int menu2;
		final int EXIT2 = 6;
		do {
			TOPrintMenu();
			menu2 = sc.nextInt();
			timeoff2(menu2);
			System.out.println("=================");
		}while(menu2 != EXIT2);
		
	}
	
	

private void timeoff2(int menu) {
		switch(menu) {
		case 1:
			TOInquiry();
			break;
		case 2:
			TOModify();
			break;
		case 3:
			LeaveSearch();
			break;
		case 4:
			LeaveInsert();
			break;
		case 5:
			LeaveModify();
			break;
		case 6:
			System.out.println("[�ڷΰ���]");
			break;
		default:
			System.out.println("[�߸��� �޴� �Է�]");
		
		}
	}
	
	private void LeaveModify() {
	// TODO Auto-generated method stub
	
}



	private void LeaveSearch() {
		System.out.print("�ް� ��ȸ�� ���� ��� : ");
		String ep_id = sc.next();
		List<LeaveVO> leaveList = timeoffService.selectLeaveList(ep_id);
		if(leaveList == null) {
			System.out.println("[�ش� ������ �ް� ������ �����ϴ�.]");
		} else {
			for(LeaveVO tmp : leaveList) {
				System.out.println(tmp);
			}
		}
	
}



	private void LeaveInsert() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		sc.nextLine();
		System.out.println("[���� :YYYY-MM-DD]");
		System.out.print("�ް� ������ : ");
		String le_datestr = sc.nextLine();
		System.out.println("[���� :YYYY-MM-DD]");
		System.out.print("������ : ");
		String le_end_datestr = sc.nextLine();
		System.out.print("���� : ");
		String le_type = sc.nextLine();
		System.out.print("���� ��� : ");
		String le_ep_id = sc.next();
		MemberVO dbMember = memberService.selectMember(le_ep_id);
		if(dbMember == null) {
			System.out.println("[���� ��ȸ ����!]");
			return;
		}
		LeaveVO leave = new LeaveVO(le_datestr, le_end_datestr, le_type, le_ep_id);
		System.out.println(leave);
	    if (timeoffService.insertTimeoff(leave)) {
	    	System.out.println("[���� ���� ���� ����]");
	    } else {
	    	System.out.println("[���� ���� ���� ����]");
	    }
		
}



	private void TOInquiry() {
		List<TimeoffVO> timeoffList = timeoffService.getTimeoffList();
		for(TimeoffVO tmp2 : timeoffList) {
			System.out.println(tmp2);
		}
	}
	private void TOModify() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
		System.out.print("������ ���� ��� : ");
		String ep_id = sc.next();
		if(memberService.selectMember(ep_id) == null) {
			System.out.println("[���� ��ȸ ����]");
			return;
		}
		TimeoffVO dbTimeoff = timeoffService.selectTimeoff(ep_id);
		if(dbTimeoff == null) {
			System.out.println("[�ش� ������ ���� �̷��� �����ϴ�!]");
			return;
		}
		sc.nextLine();
		System.out.println("[���� :YYYY-MM-DD]");
		System.out.print("��� ��¥ : ");
		String tm_datestr = sc.nextLine();
		System.out.print("���� : ");
		String tm_reason = sc.nextLine();
		
		
		try {
	        Date tm_date = dateFormat.parse(tm_datestr);
	        dbTimeoff.setTm_date(tm_date);
	        dbTimeoff.setTm_reason(tm_reason);
	        if (timeoffService.updateTimeoff(dbTimeoff)) {
	            System.out.println("[���� ��� ���� ���� ����]");
	        } else {
	            System.out.println("[���� ��� ���� ���� ����]");
	        }
	    } catch (ParseException e) {
	        System.out.println("��¥ ������ �ùٸ��� �ʽ��ϴ�.");
	    }
	
	}
	
	private static void TOPrintMenu() {
		System.out.println("=====�Խ��Ǹ޴�=====");
		System.out.println("1. ����� ���� ��ȸ");
		System.out.println("2. ����� ���� ����");
		System.out.println("3. ���� �ް� ��ȸ");
		System.out.println("4. ���� �ް� ���");
		System.out.println("5. ���� �ް� ����");
		System.out.println("6. �ڷΰ���");
		System.out.println("=================");
		System.out.print("�޴� ���� : ");
	}
}
