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
			System.out.println("=====메뉴=====");
			System.out.println("1. 직원 관리");
			System.out.println("2. 프로젝트 관리");
			System.out.println("3. 출결 관리");
			System.out.println("4. 프로그램 종료");
			System.out.println("==============");
			System.out.print("메뉴 선택 : ");
			
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
				System.out.println("[프로그램 종료]");
				break;
			default:
				System.out.println("[잘못된 메뉴 선택!]");
			}
			
		}

	}
