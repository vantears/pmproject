package pmproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import pmproject.dao.AttendanceDAO;
import pmproject.service.AttendanceService;
import pmproject.service.AttendanceServiceImp;
import pmproject.vo.AttendanceVO;
import pmproject.vo.MemberVO;
import pmproject.vo.AttendanceRecordVO;

public class AttendanceController {
    private AttendanceService attendanceService = new AttendanceServiceImp();
    Scanner sc = new Scanner(System.in);

    public void run() {
        int menu;
        final int EXIT = 5;

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
                    changeAttendance();
                    break;
                case EXIT:
                    System.out.println("[��� ���� ����]");
                    break;
                default:
                    System.out.println("[�߸��� �޴� ����]");
            }
        } while (menu != EXIT);

        sc.close();
    }

    private void printMenu() {
        System.out.println("=====��� ���� �޴�=====");
        System.out.println("1. ���� ��� ó��");
        System.out.println("2. ��� ó��");
        System.out.println("3. ��� ��ȸ");
        System.out.println("4. ��� ����");
        System.out.println("5. �޴��� ���ư���");
        System.out.println("=====================");
        System.out.print("�޴� ���� : ");
    }
    
    private void printAttendanceMenu() {
        System.out.println("1. ���");
        System.out.println("2. ����");
        System.out.println("3. ����");
        System.out.print("��� ���� ���� : ");
    }

    private void recordAttendance() {
    	LocalDate nowDate = LocalDate.now();
		String formattedDateTime = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.print("���� ID�� �Է��ϼ���: ");
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
    		System.out.println("[��� ��� �Ϸ�]");
    	} else {
    		AttendanceVO dbAt = attendanceService.getAttendance(employeeId, formattedDateTime);
    		attendanceService.changeAttendance(dbAt.getAd_num(), ad_at_num + 1);
    		System.out.println("[��� ��� �Ϸ�]");
    	}    		
    }

    private void recordLeave() {     
        System.out.print("���� ID�� �Է��ϼ���: ");
        String employeeId = sc.next();

        if(attendanceService.recordLeave(employeeId)) {
        	System.out.println("[��� ó�� �Ϸ�]");
        } else {
        	System.out.println("[��� ó�� ����]");
        };
    }

    private void viewAttendance() {
        System.out.print("���� ID �Է�: ");
        String employeeId = sc.next();

        List<AttendanceVO> dbAtList = attendanceService.viewAttendance(employeeId);
        for(AttendanceVO tmp : dbAtList) {
        	if(tmp.getAd_at_num() == 2) {
        		AttendanceRecordVO dbAtRecord = attendanceService.viewAttendanceRecord(tmp.getAd_num());
        		System.out.println(tmp.getAd_num() + ". "  + tmp.getAd_date_str() + " ���� ID " + employeeId + "�� ��� ����: " + attendanceService.getAttendanceType(tmp.getAd_at_num()) + " / ��ٽð� : " + dbAtRecord.getAr_st_time_str() + " / ��� �ð� : " + dbAtRecord.getAr_end_time_str());
        		return;
        	}
        	System.out.println(tmp.getAd_num() + ". "  + tmp.getAd_date_str() + " ���� ID " + employeeId + "�� ��� ����: " + attendanceService.getAttendanceType(tmp.getAd_at_num()));        	
        }
    }

    private void changeAttendance() {
        System.out.print("���� ID�� �Է��ϼ���: ");
        String employeeId = sc.next();
        List<AttendanceVO> dbAtList = attendanceService.viewAttendance(employeeId);
        for(AttendanceVO tmp : dbAtList) {
        	if(tmp.getAd_at_num() == 2) {
        		AttendanceRecordVO dbAtRecord = attendanceService.viewAttendanceRecord(tmp.getAd_num());
        		System.out.println(tmp.getAd_num() + ". "  + tmp.getAd_date_str() + " ���� ID " + employeeId + "�� ��� ����: " + attendanceService.getAttendanceType(tmp.getAd_at_num()) + " / ��ٽð� : " + dbAtRecord.getAr_st_time_str() + " / ��� �ð� : " + dbAtRecord.getAr_end_time_str());
        		break;
        	}
        	System.out.println(tmp.getAd_num() + ". "  + tmp.getAd_date_str() + " ���� ID " + employeeId + "�� ��� ����: " + attendanceService.getAttendanceType(tmp.getAd_at_num()));        	
        }
        System.out.print("������ ������ ��� ��� ��ȣ�� �Է��ϼ���: ");
        int ad_num = sc.nextInt();
        System.out.println("��� ���� ����");
        printAttendanceMenu();
        int at_num = sc.nextInt();

        attendanceService.changeAttendance(ad_num, at_num);
        System.out.println("��� ���°� ����Ǿ����ϴ�.");
    }
}