package pmproject.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import pmproject.service.AttendanceService;
import pmproject.service.AttendanceServiceImp;

public class AttendanceController {
    private AttendanceService attendanceService = new AttendanceServiceImp();

    public void run() {
        int menu;
        final int EXIT = 7;
        Scanner sc = new Scanner(System.in);

        do {
            printAttendanceMenu();
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
                case 5:
                    applyLeave();
                    break;
                case 6:
                    viewLeave();
                    break;
                case 7:
                    System.out.println("[출결 관리 종료]");
                    break;
                default:
                    System.out.println("[잘못된 메뉴 선택]");
            }
        } while (menu != EXIT);
    }

    private void printAttendanceMenu() {
        System.out.println("=====출결 관리 메뉴=====");
        System.out.println("1. 직원 출근 기록");
        System.out.println("2. 직원 퇴근 기록");
        System.out.println("3. 출결 조회");
        System.out.println("4. 출결 변경");
        System.out.println("5. 휴가 신청");
        System.out.println("6. 휴가 조회");
        System.out.println("7. 메뉴로 돌아가기");
        System.out.println("=====================");
        System.out.print("메뉴 선택 : ");
    }

    private void recordAttendance() {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("직원 ID를 입력하세요: ");
        int employeeId = sc.nextInt();
        sc.nextLine();
        
        LocalDateTime now = LocalDateTime.now();
        System.out.println("직원 ID " + employeeId + "의 출근 기록: " + now);
        attendanceService.recordAttendance(employeeId);
    }

    private void recordLeave() {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("직원 ID를 입력하세요: ");
        int employeeId = sc.nextInt();
        sc.nextLine();
        
        LocalDateTime now = LocalDateTime.now();
        System.out.println("직원 ID " + employeeId + "의 퇴근 기록: " + now);
        attendanceService.recordLeave(employeeId);
    }

    private void viewAttendance() {
        Scanner sc = new Scanner(System.in);
        System.out.print("직원 ID 입력: ");
        int employeeId = sc.nextInt();

        String attendanceStatus = attendanceService.viewAttendance(employeeId);

        System.out.println("직원 ID " + employeeId + "의 출결 상태: " + attendanceStatus);
    }

    private void changeAttendance() {
        Scanner sc = new Scanner(System.in);
        System.out.print("직원 ID를 입력하세요: ");
        int employeeId = sc.nextInt();
        sc.nextLine();

        System.out.print("변경할 출결 상태를 입력하세요: ");
        String newStatus = sc.nextLine();

        attendanceService.changeAttendance(employeeId, newStatus);
        System.out.println("출결 상태가 변경되었습니다.");
    }

    private void applyLeave() {
        Scanner sc = new Scanner(System.in);
        System.out.print("직원 ID를 입력하세요: ");
        int employeeId = sc.nextInt();
        sc.nextLine();

        System.out.print("휴가 타입을 입력하세요: ");
        String leaveType = sc.nextLine();

        System.out.print("휴가 시작일을 입력하세요 (yyyy-MM-dd): ");
        String startDate = sc.nextLine();

        System.out.print("휴가 종료일을 입력하세요 (yyyy-MM-dd): ");
        String endDate = sc.nextLine();

        attendanceService.applyLeave(employeeId, leaveType, startDate, endDate);
        System.out.println("휴가 신청이 완료되었습니다.");
    }

    private void viewLeave() {
        Scanner sc = new Scanner(System.in);
        System.out.print("직원 ID를 입력하세요: ");
        int employeeId = sc.nextInt();

        String leaveStatus = attendanceService.viewLeave(employeeId);

        System.out.println("직원 ID " + employeeId + "의 휴가 상태: " + leaveStatus);
    }

}