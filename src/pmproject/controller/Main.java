package pmproject.controller;

import java.util.Scanner;

public class Main {
		
		private static MemberController memberController = new MemberController();
		private static ProjectController projectController = new ProjectController();
		private static AttendanceController attendanceController = new AttendanceController();
		
		public static void main(String[] args) {
			int menu;
			final int EXIT = 4;
			Scanner sc = new Scanner(System.in);
			do {
				printMenu();
				menu = sc.nextInt();
				runMenu(menu);
			}while(menu != EXIT);
			sc.close();
		}

		private static void printMenu() {
			System.out.println("=====�޴�=====");
			System.out.println("1. ���� ����");
			System.out.println("2. ������Ʈ ����");
			System.out.println("3. ��� ����");
			System.out.println("4. ���α׷� ����");
			System.out.println("==============");
			System.out.print("�޴� ���� : ");
			
		}

		private static void runMenu(int menu) {
			switch(menu) {
			case 1:
				memberController.run();
				break;
			case 2:
				projectController.run();
				break;
			case 3:
				attendanceController.run();
				break;
			case 4:
				System.out.println("[���α׷� ����]");
				break;
			default:
				System.out.println("[�߸��� �޴� ����!]");
			}
			
		}

	}
