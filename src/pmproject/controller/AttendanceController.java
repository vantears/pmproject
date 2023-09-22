package pmproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import pmproject.dao.AttendanceDAO;
import pmproject.service.AttendanceService;
import pmproject.service.AttendanceServiceImp;
import pmproject.service.MemberService;
import pmproject.service.MemberServiceImp;
import pmproject.vo.AttendanceVO;
import pmproject.vo.MemberVO;
import pmproject.vo.AttendanceRecordVO;

public class AttendanceController {
    private AttendanceService attendanceService = new AttendanceServiceImp();
    private MemberService memberService = new MemberServiceImp();
    Scanner sc = new Scanner(System.in);

    public void run() {
        int menu;
        final int EXIT = 6;

        do {
            printMenu();
            menu = sc.nextInt();

            switch(menu) {
                case 1:
                    recordAttendance();
                    break;
                case 2:
                    recordLeave();
                    break;
                case 3:
                    viewAttendance();
                    break;
                case 4:
                    viewAllAttendance();
                    break;
                case 5:
                    changeAttendance();
                    break;
                case EXIT:
                    System.out.println("[출결 관리 종료]");
                    break;
                default:
                    System.out.println("[잘못된 메뉴 선택]");
            }
        } while (menu != EXIT);
    }

    private void viewAllAttendance() {
    	 System.out.print("조회할 날짜 입력: ");
         String date = sc.next();

         List<AttendanceVO> dbAtList = attendanceService.viewAllAttendance(date);
         for(AttendanceVO tmp : dbAtList) {
        	MemberVO dbMember = memberService.selectMember(tmp.getAd_ep_id());
         	if(tmp.getAd_at_num() == 2) {
         		AttendanceRecordVO dbAtRecord = attendanceService.viewAttendanceRecord(tmp.getAd_num());
        		System.out.println(tmp.getAd_num() + ". "  + tmp.getAd_date_str() + " 직원 " + dbMember.getEp_name() + "(" + tmp.getAd_ep_id() + ")" + "의 출결 상태: " + attendanceService.getAttendanceType(tmp.getAd_at_num()) + " / 출근시간 : " + dbAtRecord.getAr_st_time_str() + " / 퇴근 시간 : " + dbAtRecord.getAr_end_time_str());
         		continue;
         	}
         	System.out.println(tmp.getAd_num() + ". "  + tmp.getAd_date_str() + " 직원 " + dbMember.getEp_name() + "(" + tmp.getAd_ep_id() + ")" + "의 출결 상태: " + attendanceService.getAttendanceType(tmp.getAd_at_num()));        	
         }
		
	}

	private void printMenu() {
        System.out.println("=====출결 관리 메뉴=====");
        System.out.println("1. 직원 출결 처리");
        System.out.println("2. 퇴근 처리");
        System.out.println("3. 직원 출결 조회");
        System.out.println("4. 날짜별 출결 전체 조회");
        System.out.println("5. 출결 변경");
        System.out.println("6. 메뉴로 돌아가기");
        System.out.println("=====================");
        System.out.print("메뉴 선택 : ");
    }
    
    private void printAttendanceMenu() {
        System.out.println("1. 출근");
        System.out.println("2. 재택");
        System.out.println("3. 출장");
        System.out.print("출결 종류 선택 : ");
    }

    private void recordAttendance() {
    	LocalDate nowDate = LocalDate.now();
		String formattedDateTime = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.print("직원 ID를 입력하세요: ");
        String employeeId = sc.next();
        printAttendanceMenu();
    	int ad_at_num = sc.nextInt();
    	List<AttendanceVO> dbAtList = attendanceService.checkAttendanceDate(formattedDateTime);
    	if(dbAtList.isEmpty()) {
    		List<MemberVO> dbMemberList = attendanceService.getAllMember();
    		attendanceService.recordAttendance(dbMemberList, formattedDateTime);
    	}
    	if(ad_at_num == 1) {
    		AttendanceVO dbAt = attendanceService.getAttendance(employeeId, formattedDateTime);
    		attendanceService.changeAttendance(dbAt.getAd_num(), ad_at_num + 1);
    		System.out.println("[출결 등록 완료]");
    	} else {
    		AttendanceVO dbAt = attendanceService.getAttendance(employeeId, formattedDateTime);
    		attendanceService.changeAttendance(dbAt.getAd_num(), ad_at_num + 1);
    		System.out.println("[출결 등록 완료]");
    	}    		
    }

    private void recordLeave() {     
        System.out.print("직원 ID를 입력하세요: ");
        String employeeId = sc.next();

        if(attendanceService.recordLeave(employeeId)) {
        	System.out.println("[퇴근 처리 완료]");
        } else {
        	System.out.println("[퇴근 처리 실패]");
        };
    }

    private void viewAttendance() {
        System.out.print("직원 ID 입력: ");
        String employeeId = sc.next();

        List<AttendanceVO> dbAtList = attendanceService.viewAttendance(employeeId);
        for(AttendanceVO tmp : dbAtList) {
        	MemberVO dbMember = memberService.selectMember(tmp.getAd_ep_id());
        	if(tmp.getAd_at_num() == 2) {
        		AttendanceRecordVO dbAtRecord = attendanceService.viewAttendanceRecord(tmp.getAd_num());
        		System.out.println(tmp.getAd_num() + ". "  + tmp.getAd_date_str() + " 직원 " + dbMember.getEp_name() + "(" + tmp.getAd_ep_id() + ")" + "의 출결 상태: " + attendanceService.getAttendanceType(tmp.getAd_at_num()) + " / 출근시간 : " + dbAtRecord.getAr_st_time_str() + " / 퇴근 시간 : " + dbAtRecord.getAr_end_time_str());
        		continue;
        	}
        	System.out.println(tmp.getAd_num() + ". "  + tmp.getAd_date_str() + " 직원 " + dbMember.getEp_name() + "(" + tmp.getAd_ep_id() + ")" + "의 출결 상태: " + attendanceService.getAttendanceType(tmp.getAd_at_num()));        	
        }
    }

    private void changeAttendance() {
        System.out.print("직원 ID를 입력하세요: ");
        String employeeId = sc.next();
        List<AttendanceVO> dbAtList = attendanceService.viewAttendance(employeeId);
        for(AttendanceVO tmp : dbAtList) {
        	MemberVO dbMember = memberService.selectMember(tmp.getAd_ep_id());
        	if(tmp.getAd_at_num() == 2) {
        		AttendanceRecordVO dbAtRecord = attendanceService.viewAttendanceRecord(tmp.getAd_num());
        		System.out.println(tmp.getAd_num() + ". "  + tmp.getAd_date_str() + " 직원 " + dbMember.getEp_name() + "(" + tmp.getAd_ep_id() + ")" + "의 출결 상태: " + attendanceService.getAttendanceType(tmp.getAd_at_num()) + " / 출근시간 : " + dbAtRecord.getAr_st_time_str() + " / 퇴근 시간 : " + dbAtRecord.getAr_end_time_str());
        		continue;
        	}
        	System.out.println(tmp.getAd_num() + ". "  + tmp.getAd_date_str() + " 직원 " + dbMember.getEp_name() + "(" + tmp.getAd_ep_id() + ")" + attendanceService.getAttendanceType(tmp.getAd_at_num()));        	
        }
        System.out.print("변경할 직원의 출결 기록 번호를 입력하세요: ");
        int ad_num = sc.nextInt();
        System.out.println("출결 변경 종류");
        printAttendanceMenu();
        int at_num = sc.nextInt();

        attendanceService.changeAttendance(ad_num, at_num);
        System.out.println("출결 상태가 변경되었습니다.");
    }
}