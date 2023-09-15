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
			System.out.println("[뒤로가기]");
			break;
		default:
			System.out.println("[잘못된 메뉴 입력]");
		
		}
	}
	
	private void LeaveModify() {
	// TODO Auto-generated method stub
	
}



	private void LeaveSearch() {
		System.out.print("휴가 조회할 직원 사번 : ");
		String ep_id = sc.next();
		List<LeaveVO> leaveList = timeoffService.selectLeaveList(ep_id);
		if(leaveList == null) {
			System.out.println("[해당 직원의 휴가 내역이 없습니다.]");
		} else {
			for(LeaveVO tmp : leaveList) {
				System.out.println(tmp);
			}
		}
	
}



	private void LeaveInsert() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		sc.nextLine();
		System.out.println("[형식 :YYYY-MM-DD]");
		System.out.print("휴가 시작일 : ");
		String le_datestr = sc.nextLine();
		System.out.println("[형식 :YYYY-MM-DD]");
		System.out.print("복귀일 : ");
		String le_end_datestr = sc.nextLine();
		System.out.print("사유 : ");
		String le_type = sc.nextLine();
		System.out.print("직원 사번 : ");
		String le_ep_id = sc.next();
		MemberVO dbMember = memberService.selectMember(le_ep_id);
		if(dbMember == null) {
			System.out.println("[직원 조회 실패!]");
			return;
		}
		LeaveVO leave = new LeaveVO(le_datestr, le_end_datestr, le_type, le_ep_id);
		System.out.println(leave);
	    if (timeoffService.insertTimeoff(leave)) {
	    	System.out.println("[직원 정보 수정 성공]");
	    } else {
	    	System.out.println("[직원 정보 수정 실패]");
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
	
		System.out.print("수정할 직원 사번 : ");
		String ep_id = sc.next();
		if(memberService.selectMember(ep_id) == null) {
			System.out.println("[직원 조회 실패]");
			return;
		}
		TimeoffVO dbTimeoff = timeoffService.selectTimeoff(ep_id);
		if(dbTimeoff == null) {
			System.out.println("[해당 직원의 퇴직 이력이 없습니다!]");
			return;
		}
		sc.nextLine();
		System.out.println("[형식 :YYYY-MM-DD]");
		System.out.print("퇴사 날짜 : ");
		String tm_datestr = sc.nextLine();
		System.out.print("사유 : ");
		String tm_reason = sc.nextLine();
		
		
		try {
	        Date tm_date = dateFormat.parse(tm_datestr);
	        dbTimeoff.setTm_date(tm_date);
	        dbTimeoff.setTm_reason(tm_reason);
	        if (timeoffService.updateTimeoff(dbTimeoff)) {
	            System.out.println("[직원 퇴사 정보 수정 성공]");
	        } else {
	            System.out.println("[직원 퇴사 정보 수정 실패]");
	        }
	    } catch (ParseException e) {
	        System.out.println("날짜 형식이 올바르지 않습니다.");
	    }
	
	}
	
	private static void TOPrintMenu() {
		System.out.println("=====게시판메뉴=====");
		System.out.println("1. 퇴사자 정보 조회");
		System.out.println("2. 퇴사자 정보 수정");
		System.out.println("3. 직원 휴가 조회");
		System.out.println("4. 직원 휴가 등록");
		System.out.println("5. 직원 휴가 수정");
		System.out.println("6. 뒤로가기");
		System.out.println("=================");
		System.out.print("메뉴 선택 : ");
	}
}
