package pmproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import pmproject.service.MemberService;
import pmproject.service.MemberServiceImp;
import pmproject.vo.MemberVO;
import pmproject.vo.SalaryHistoryVO;
import pmproject.vo.SalaryVO;
import pmproject.vo.TransferVO;
import pmproject.vo.DeptVO;

public class MemberController {

	private MemberService memberService = new MemberServiceImp();
	private EPController epController = new EPController();
	Scanner sc = new Scanner(System.in);

	public void run() {
		int menu;
		final int EXIT = 4;
		do {
			printMenu();
			menu = sc.nextInt();
			runMenu(menu);
		}while(menu != EXIT);
		
	}


	private void printMenu() {
		System.out.println("=====���� ����=====");
		System.out.println("1. ���� ���");
		System.out.println("2. ���� ���� ����");
		System.out.println("3. ���� ��� �� �ް� ����");
		System.out.println("4. ���� �޴�");
		System.out.println("=================");
		System.out.print("�޴� ���� : ");
		
	}

	private void runMenu(int menu) {
		switch(menu) {
		case 1:
			register();
			break;
		case 2:
			infoManagementMenu();
			break;
		case 3:
			epController.run();
			break;
		case 4:
			System.out.println("[�����޴��� �̵�]");
			break;
		default:
			System.out.println("[�߸��� �޴� ����!]");
		}
		
	}


	private void infoManagementMenu() {
		int menu;
		final int EXIT = 8;
		do {
			System.out.println("=====���� ���� ����=====");
			System.out.println("1. ���� ��ȸ");
			System.out.println("2. ���� ����ó ����");
			System.out.println("3. ���� �μ� �̵�");
			System.out.println("4. ���� �μ� �̵� ���� ��ȸ");
			System.out.println("5. ���� �޿� ����");
			System.out.println("6. �޿� ����");
			System.out.println("7. ���� �޿� �̷� ��ȸ");
			System.out.println("8. ���� �޴�");
			System.out.println("=================");
			System.out.print("�޴� ���� : ");
			menu = sc.nextInt();
			switch(menu) {
			case 1:
				searchMember();
				break;
			case 2:
				modifyContact();
				break;
			case 3:
				modifyDepartment();
				break;
			case 4:
				searchTransfer();
				break;
			case 5:
				modifySalary();
				break;
			case 6:
				giveSalary();
				break;
			case 7:
				searchSalaryHistory();
				break;
			case 8:
				System.out.println("[���� �޴��� �̵�]");
				break;
			default:
				System.out.println("[�߸��� �޴� ����!]");
			}
		}while(menu != EXIT);
		
	}


	private void searchTransfer() {
		System.out.println("�μ� �̵� ���� ��ȸ�� ���� ��� : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[���� ��ȸ ����]");
		} else {
			List<TransferVO> transferHistory = memberService.SearchTransfer(id);
			if(transferHistory == null) {
				System.out.println("�μ��̵� ������ �����ϴ�.");
				return;
			}
			for(TransferVO tmp : transferHistory) {
				System.out.println((transferHistory.indexOf(tmp) + 1) + ". " + tmp);
			}
	
		}
		
	}


	private void giveSalary() {
		System.out.println("�޿� ������ ���� ��� : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[���� ��ȸ ����]");
		} else {
			MemberVO dbMember = memberService.selectMember(id);
			System.out.println("[���� ��ȸ���]");
			System.out.println("��� : " + dbMember.getEp_id());
			System.out.println("�̸� : " + dbMember.getEp_name());
			System.out.println("�̸��� : " + dbMember.getEp_email());
			System.out.println("����ó : " + dbMember.getEp_phone_num());
			System.out.println("���޿�(����) : " + dbMember.getEp_salary());
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String today = date.format(formatter);
			System.out.println("���� ��¥ : " + today);
			System.out.println("-���� ������ �޿� ����-");
			List<SalaryVO> SalaryTypeList = memberService.selectSalaryType();
			for(SalaryVO tmp : SalaryTypeList) {
				System.out.println((SalaryTypeList.lastIndexOf(tmp) + 1) + ". " + tmp.getPa_type());
			}
			sc.nextLine();
			System.out.print("������ �޿� ���� : ");
			String salaryType = sc.nextLine();
			SalaryVO salaryVO = new SalaryVO(salaryType);
			if(!SalaryTypeList.contains(salaryVO)) {
				System.out.println("[�������� �ʴ� �޿� �����Դϴ�!]");
				return;
			}
			System.out.print("�޿� �� : ");
			String salaryDetail = sc.nextLine();
			System.out.println("�޿���(����) : ");
			int salary = sc.nextInt();
			if(memberService.insertSalaryHistory(dbMember.getEp_id(), salaryType, salaryDetail, today, salary)) {
				System.out.println("[�޿� ���� �Ϸ�]");
			} else {
				System.out.println("[�޿� ���� ����]");
			}			
		}
		
	}


	private void searchSalaryHistory() {
		System.out.println("�޿� ���� ��ȸ�� ���� ��� : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[���� ��ȸ ����]");
		} else {
			List<SalaryHistoryVO> salaryHistory = memberService.SearchSalaryHistory(id);
			if(salaryHistory == null) {
				System.out.println("�޿� ���� �̷��� �����ϴ�.");
				return;
			}
			for(SalaryHistoryVO tmp : salaryHistory) {
				System.out.println((salaryHistory.indexOf(tmp) + 1) + ". " + tmp);
			}
	
		}
		
	}


	private void modifySalary() {
		System.out.print("������ ���� ��� : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[���� ��ȸ ����]");
		} else {
			MemberVO dbMember = memberService.selectMember(id);
			System.out.println("[���� ��ȸ���]");
			System.out.println("��� : " + dbMember.getEp_id());
			System.out.println("�̸� : " + dbMember.getEp_name());
			System.out.println("�̸��� : " + dbMember.getEp_email());
			System.out.println("����ó : " + dbMember.getEp_phone_num());
			System.out.println("���޿�(����) : " + dbMember.getEp_salary());
			System.out.print("����� ���޿�(����) : ");
			int salary = sc.nextInt();
			if(memberService.updateSalary(id, salary)) {
				System.out.println("[�޿� ���� �Ϸ�]");
			} else {
				System.out.println("[�޿� ���� ����]");
			}			
		}
	}


	private void modifyDepartment() {
		System.out.print("������ ���� ��� : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[���� ��ȸ ����]");
		} else {
			MemberVO dbMember = memberService.selectMember(id);
			System.out.println("[���� ��ȸ���]");
			System.out.println("��� : " + dbMember.getEp_id());
			System.out.println("�̸� : " + dbMember.getEp_name());
			System.out.println("�̸��� : " + dbMember.getEp_email());
			System.out.println("����ó : " + dbMember.getEp_phone_num());
			System.out.println("�μ� : " + dbMember.getEp_dept());
			System.out.println("-�μ� ���-");
			List<DeptVO> deptList = memberService.selectAllDept();
			for(DeptVO tmp : deptList) {
				System.out.println(tmp.getDm_num() + ". " + tmp.getDm_name());
			}
			System.out.print("����� �μ� ��ȣ : ");
			int dept = sc.nextInt();
			sc.nextLine();
			System.out.print("�μ� �̵� ���� : ");
			String reason = sc.nextLine();
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String today = date.format(formatter);
			if(memberService.updateDept(id, dept, reason, today, dbMember.getEp_id())) {
				System.out.println("[�μ� ���� �Ϸ�]");
			} else {
				System.out.println("[�μ� ���� ����]");
			}
			
		}
			
	}


	private void modifyContact() {
		System.out.print("������ ���� ��� : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[���� ��ȸ ����]");
		} else {
			MemberVO dbMember = memberService.selectMember(id);
			System.out.println("[���� ��ȸ���]");
			System.out.println("��� : " + dbMember.getEp_id());
			System.out.println("�̸� : " + dbMember.getEp_name());
			System.out.println("�̸��� : " + dbMember.getEp_email());
			System.out.println("����ó : " + dbMember.getEp_phone_num());
			System.out.print("����� ����ó : ");
			String newPhone = sc.next();
			if(memberService.updatePhone(id, newPhone)) {
				System.out.println("[����ó ���� �Ϸ�]");
			} else {
				System.out.println("[����ó ���� ����]");
				}
				
			}
			
		}
		


	private void searchMember() {
		System.out.print("��ȸ�� ���� ��� : ");
		String id = sc.next();
		MemberVO dbMember = memberService.selectMember(id);
		if(dbMember == null) {
			System.out.println("[���� ��ȸ ����]");
		} else {
			System.out.println(dbMember);
		}
		
	}


	private void leaveManagementMenu() {
		int menu;
		final int EXIT = 3;
		do {
			System.out.println("=====���� �ް� ����=====");
			System.out.println("1. ���� �ް� ��ȸ");
			System.out.println("2. ���� �ް� ��û");
			System.out.println("3. ���� �޴�");
			System.out.println("=================");
			System.out.print("�޴� ���� : ");
			menu = sc.nextInt();
			switch(menu) {
			case 1:
				searchLeave();
				break;
			case 2:
				useLeave();
				break;
			case 3:
				System.out.println("[���� �޴��� �̵�]");
				break;
			default:
				System.out.println("[�߸��� �޴� ����!]");
			}
		}while(menu != EXIT);
		
	}


	private void useLeave() {
		// TODO Auto-generated method stub
		
	}


	private void searchLeave() {
		// TODO Auto-generated method stub
		
	}


	private void register() {
		
		System.out.print("���� �̸� : ");
		String name = sc.next();
		System.out.print("���� �̸��� : ");
		String email = sc.next();
		System.out.print("���� ����ó : ");
		String phone = sc.next();
		System.out.print("���� ���޿�(����) : ");
		int salary = sc.nextInt();
		MemberVO member = new MemberVO(name, email, phone, salary);
		int memberCount = memberService.selectMemberCount() + 1;
		String firstLetter = "PM";
		String num = "" + memberCount;
		for(int i = 0; i < (8 - num.length()); i++) {
			num = "0" + num;
		}
		member.setEp_id(firstLetter + num);
		if(memberService.insertMember(member)) {
			System.out.println("[���� ��� �Ϸ�]");
		} else {
			System.out.println("[���� ��� ����]");
		}	
		
	}
	
	private void retirementManagementMenu() {
		int menu;
		final int EXIT = 4;
		do {
			System.out.println("=====���� ���/���� ����=====");
			System.out.println("1. ���� ���/���� ��ȸ");
			System.out.println("2. ���� ��� ó��");
			System.out.println("3. ���� ���� ó��");
			System.out.println("4. ���� �޴�");
			System.out.println("=================");
			System.out.print("�޴� ���� : ");
			menu = sc.nextInt();
			switch(menu) {
			case 1:
				searchRetirement();
				break;
			case 2:
				doRetirement();
				break;
			case 3:
				doBreak();
				break;
			case 4:
				System.out.println("[���� �޴��� �̵�]");
				break;
			default:
				System.out.println("[�߸��� �޴� ����!]");
			}
		}while(menu != EXIT);
		
	}


	private void doBreak() {
		// TODO Auto-generated method stub
		
	}


	private void doRetirement() {
		// TODO Auto-generated method stub
		
	}


	private void searchRetirement() {
		// TODO Auto-generated method stub
		
	}



}
