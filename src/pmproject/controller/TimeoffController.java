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
			System.out.println("[뒤로가기]");
			break;
		default:
			System.out.println("[잘못된 메뉴 입력]");
		
		}
	}
	
	private void TOInsert() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[형식 :YYYY-MM-DD]");
		System.out.print("퇴사/휴직 날짜 : ");
		String tm_datestr = sc.nextLine();
		System.out.println("[형식 :YYYY-MM-DD]");
		System.out.println("[퇴직 :0000-00-00]");
		System.out.print("복직 날짜 : ");
		String tm_return_datestr = sc.nextLine();
		System.out.print("사유 : ");
		String tm_reason = sc.nextLine();
		System.out.print("직원 id : ");
		String tm_ep_id = sc.next();
		
		try {
	        Date tm_date = dateFormat.parse(tm_datestr);
	        Date tm_return_date = dateFormat.parse(tm_return_datestr); 

	        TimeoffVO timeoff = new TimeoffVO(tm_date, tm_return_date, tm_reason, tm_ep_id);
	        if (timeoffService.insertTimeoff(timeoff)) {
	            System.out.println("[직원 정보 수정 성공]");
	        } else {
	            System.out.println("[직원 정보 수정 실패]");
	        }
	    } catch (ParseException e) {
	        System.out.println("날짜 형식이 올바르지 않습니다.");
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
		System.out.print("퇴사/휴직 번호 : ");
		int tm_num = sc.nextInt();
		sc.nextLine();
		System.out.println("[형식 :YYYY-MM-DD]");
		System.out.print("퇴사/휴직 날짜 : ");
		String tm_datestr = sc.nextLine();
		System.out.println("[형식 :YYYY-MM-DD]");
		System.out.println("[퇴직 :0000-00-00]");
		System.out.print("복직 날짜 : ");
		String tm_return_datestr = sc.nextLine();
		System.out.print("사유 : ");
		String tm_reason = sc.nextLine();
		System.out.print("직원 id : ");
		String tm_ep_id = sc.next();
		
		
		try {
	        Date tm_date = dateFormat.parse(tm_datestr);
	        Date tm_return_date = dateFormat.parse(tm_return_datestr); 

	        TimeoffVO timeoff = new TimeoffVO(tm_num, tm_date, tm_return_date, tm_reason, tm_ep_id);
	        if (timeoffService.updateTimeoff(timeoff)) {
	            System.out.println("[직원 정보 수정 성공]");
	        } else {
	            System.out.println("[직원 정보 수정 실패]");
	        }
	    } catch (ParseException e) {
	        System.out.println("날짜 형식이 올바르지 않습니다.");
	    }
	
	}
	
	private static void TOPrintMenu() {
		System.out.println("=====게시판메뉴=====");
		System.out.println("1. 휴가자/퇴사자 정보 조회");
		System.out.println("2. 휴가자/퇴사자 정보 수정");
		System.out.println("3. 휴가자/퇴사자 정보 추가");
		System.out.println("4. 뒤로가기");
		System.out.println("=================");
		System.out.print("메뉴 선택 : ");
	}
}
