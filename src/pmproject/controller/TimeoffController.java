package pmproject.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import pmproject.service.TimeoffService;
import pmproject.service.TimeoffServiceImp;
import pmproject.vo.TimeoffVO;

public class TimeoffController {
	
	private TimeoffService timeoffService = new TimeoffServiceImp();
	
	public void timeoff() {
		int menu2;
		final int EXIT2 = 4;
		Scanner sc = new Scanner(System.in);
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
			TOInsert();
		case 4:
			System.out.println("[�ڷΰ���]");
			break;
		default:
			System.out.println("[�߸��� �޴� �Է�]");
		
		}
	}
	
	private void TOInsert() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[���� :YYYY-MM-DD]");
		System.out.print("���/���� ��¥ : ");
		String tm_datestr = sc.nextLine();
		System.out.println("[���� :YYYY-MM-DD]");
		System.out.println("[���� :0000-00-00]");
		System.out.print("���� ��¥ : ");
		String tm_return_datestr = sc.nextLine();
		System.out.print("���� : ");
		String tm_reason = sc.nextLine();
		System.out.print("���� id : ");
		int tm_ep_id = sc.nextInt();
		
		try {
	        Date tm_date = dateFormat.parse(tm_datestr);
	        Date tm_return_date = dateFormat.parse(tm_return_datestr); 

	        TimeoffVO timeoff = new TimeoffVO(tm_date, tm_return_date, tm_reason, tm_ep_id);
	        if (timeoffService.insertTimeoff(timeoff)) {
	            System.out.println("[���� ���� ���� ����]");
	        } else {
	            System.out.println("[���� ���� ���� ����]");
	        }
	    } catch (ParseException e) {
	        System.out.println("��¥ ������ �ùٸ��� �ʽ��ϴ�.");
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
		
		Scanner sc = new Scanner(System.in);
		System.out.print("���/���� ��ȣ : ");
		int tm_num = sc.nextInt();
		sc.nextLine();
		System.out.println("[���� :YYYY-MM-DD]");
		System.out.print("���/���� ��¥ : ");
		String tm_datestr = sc.nextLine();
		System.out.println("[���� :YYYY-MM-DD]");
		System.out.println("[���� :0000-00-00]");
		System.out.print("���� ��¥ : ");
		String tm_return_datestr = sc.nextLine();
		System.out.print("���� : ");
		String tm_reason = sc.nextLine();
		System.out.print("���� id : ");
		int tm_ep_id = sc.nextInt();
		
		
		try {
	        Date tm_date = dateFormat.parse(tm_datestr);
	        Date tm_return_date = dateFormat.parse(tm_return_datestr); 

	        TimeoffVO timeoff = new TimeoffVO(tm_num, tm_date, tm_return_date, tm_reason, tm_ep_id);
	        if (timeoffService.updateTimeoff(timeoff)) {
	            System.out.println("[���� ���� ���� ����]");
	        } else {
	            System.out.println("[���� ���� ���� ����]");
	        }
	    } catch (ParseException e) {
	        System.out.println("��¥ ������ �ùٸ��� �ʽ��ϴ�.");
	    }
	
	}
	
	private static void TOPrintMenu() {
		System.out.println("=====�Խ��Ǹ޴�=====");
		System.out.println("1. �ް���/����� ���� ��ȸ");
		System.out.println("2. �ް���/����� ���� ����");
		System.out.println("3. �ް���/����� ���� �߰�");
		System.out.println("4. �ڷΰ���");
		System.out.println("=================");
		System.out.print("�޴� ���� : ");
	}
}
