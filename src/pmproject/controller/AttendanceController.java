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
                    System.out.println("[��� ���� ����]");
                    break;
                default:
                    System.out.println("[�߸��� �޴� ����]");
            }
        } while (menu != EXIT);
    }

    private void printAttendanceMenu() {
        System.out.println("=====��� ���� �޴�=====");
        System.out.println("1. ���� ��� ���");
        System.out.println("2. ���� ��� ���");
        System.out.println("3. ��� ��ȸ");
        System.out.println("4. ��� ����");
        System.out.println("5. �ް� ��û");
        System.out.println("6. �ް� ��ȸ");
        System.out.println("7. �޴��� ���ư���");
        System.out.println("=====================");
        System.out.print("�޴� ���� : ");
    }

    private void recordAttendance() {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("���� ID�� �Է��ϼ���: ");
        int employeeId = sc.nextInt();
        sc.nextLine();
        
        LocalDateTime now = LocalDateTime.now();
        System.out.println("���� ID " + employeeId + "�� ��� ���: " + now);
        attendanceService.recordAttendance(employeeId);
    }

    private void recordLeave() {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("���� ID�� �Է��ϼ���: ");
        int employeeId = sc.nextInt();
        sc.nextLine();
        
        LocalDateTime now = LocalDateTime.now();
        System.out.println("���� ID " + employeeId + "�� ��� ���: " + now);
        attendanceService.recordLeave(employeeId);
    }

    private void viewAttendance() {
        Scanner sc = new Scanner(System.in);
        System.out.print("���� ID �Է�: ");
        int employeeId = sc.nextInt();

        String attendanceStatus = attendanceService.viewAttendance(employeeId);

        System.out.println("���� ID " + employeeId + "�� ��� ����: " + attendanceStatus);
    }

    private void changeAttendance() {
        Scanner sc = new Scanner(System.in);
        System.out.print("���� ID�� �Է��ϼ���: ");
        int employeeId = sc.nextInt();
        sc.nextLine();

        System.out.print("������ ��� ���¸� �Է��ϼ���: ");
        String newStatus = sc.nextLine();

        attendanceService.changeAttendance(employeeId, newStatus);
        System.out.println("��� ���°� ����Ǿ����ϴ�.");
    }

    private void applyLeave() {
        Scanner sc = new Scanner(System.in);
        System.out.print("���� ID�� �Է��ϼ���: ");
        int employeeId = sc.nextInt();
        sc.nextLine();

        System.out.print("�ް� Ÿ���� �Է��ϼ���: ");
        String leaveType = sc.nextLine();

        System.out.print("�ް� �������� �Է��ϼ��� (yyyy-MM-dd): ");
        String startDate = sc.nextLine();

        System.out.print("�ް� �������� �Է��ϼ��� (yyyy-MM-dd): ");
        String endDate = sc.nextLine();

        attendanceService.applyLeave(employeeId, leaveType, startDate, endDate);
        System.out.println("�ް� ��û�� �Ϸ�Ǿ����ϴ�.");
    }

    private void viewLeave() {
        Scanner sc = new Scanner(System.in);
        System.out.print("���� ID�� �Է��ϼ���: ");
        int employeeId = sc.nextInt();

        String leaveStatus = attendanceService.viewLeave(employeeId);

        System.out.println("���� ID " + employeeId + "�� �ް� ����: " + leaveStatus);
    }

}