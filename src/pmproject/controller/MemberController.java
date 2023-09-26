package pmproject.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		System.out.println("=====직원 관리=====");
		System.out.println("1. 직원 등록");
		System.out.println("2. 직원 정보 관리");
		System.out.println("3. 직원 퇴사 및 휴가 관리");
		System.out.println("4. 이전 메뉴");
		System.out.println("=================");
		System.out.print("메뉴 선택 : ");
		
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
			System.out.println("[이전메뉴로 이동]");
			break;
		default:
			System.out.println("[잘못된 메뉴 선택!]");
		}
		
	}


	private void infoManagementMenu() {
		int menu;
		final int EXIT = 8;
		do {
			System.out.println("=====직원 정보 관리=====");
			System.out.println("1. 직원 조회");
			System.out.println("2. 직원 연락처 수정");
			System.out.println("3. 직원 부서 이동");
			System.out.println("4. 직원 부서 이동 내역 조회");
			System.out.println("5. 직원 급여 수정");
			System.out.println("6. 급여 지급");
			System.out.println("7. 직원 급여 이력 조회");
			System.out.println("8. 이전 메뉴");
			System.out.println("=================");
			System.out.print("메뉴 선택 : ");
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
				System.out.println("[이전 메뉴로 이동]");
				break;
			default:
				System.out.println("[잘못된 메뉴 선택!]");
			}
		}while(menu != EXIT);
		
	}


	private void searchTransfer() {
		System.out.print("부서 이동 내역 조회할 직원 사번 : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[직원 조회 실패]");
		} else {
			List<TransferVO> transferHistory = memberService.SearchTransfer(id);
			if(transferHistory == null) {
				System.out.println("부서이동 내역이 없습니다.");
				return;
			}
			for(TransferVO tmp : transferHistory) {
				System.out.println((transferHistory.indexOf(tmp) + 1) + ". " + tmp);
			}
	
		}
		
	}


	private void giveSalary() {
		System.out.print("급여 지급할 직원 사번 : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[직원 조회 실패]");
		} else {
			MemberVO dbMember = memberService.selectMember(id);
			System.out.println("[직원 조회결과]");
			System.out.println("사번 : " + dbMember.getEp_id());
			System.out.println("이름 : " + dbMember.getEp_name());
			System.out.println("이메일 : " + dbMember.getEp_email());
			System.out.println("연락처 : " + dbMember.getEp_phone_num());
			System.out.println("월급여(만원) : " + dbMember.getEp_salary());
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String today = date.format(formatter);
			System.out.println("오늘 날짜 : " + today);
			System.out.println("-지급 가능한 급여 유형-");
			List<SalaryVO> SalaryTypeList = memberService.selectSalaryType();
			for(SalaryVO tmp : SalaryTypeList) {
				System.out.println((SalaryTypeList.lastIndexOf(tmp) + 1) + ". " + tmp.getPa_type());
			}
			sc.nextLine();
			System.out.print("지급할 급여 유형 : ");
			String salaryType = sc.nextLine();
			SalaryVO salaryVO = new SalaryVO(salaryType);
			if(!SalaryTypeList.contains(salaryVO)) {
				System.out.println("[존재하지 않는 급여 유형입니다!]");
				return;
			}
			System.out.print("급여 상세 : ");
			String salaryDetail = sc.nextLine();
			System.out.print("급여액(만원) : ");
			int salary = sc.nextInt();
			if(memberService.insertSalaryHistory(dbMember.getEp_id(), salaryType, salaryDetail, today, salary)) {
				System.out.println("[급여 지급 완료]");
			} else {
				System.out.println("[급여 지급 실패]");
			}			
		}
		
	}


	private void searchSalaryHistory() {
		System.out.print("급여 내역 조회할 직원 사번 : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[직원 조회 실패]");
		} else {
			List<SalaryHistoryVO> salaryHistory = memberService.SearchSalaryHistory(id);
			if(salaryHistory == null) {
				System.out.println("급여 지급 이력이 없습니다.");
				return;
			}
			for(SalaryHistoryVO tmp : salaryHistory) {
				System.out.println((salaryHistory.indexOf(tmp) + 1) + ". " + tmp);
			}
	
		}
		
	}


	private void modifySalary() {
		System.out.print("수정할 직원 사번 : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[직원 조회 실패]");
		} else {
			MemberVO dbMember = memberService.selectMember(id);
			System.out.println("[직원 조회결과]");
			System.out.println("사번 : " + dbMember.getEp_id());
			System.out.println("이름 : " + dbMember.getEp_name());
			System.out.println("이메일 : " + dbMember.getEp_email());
			System.out.println("연락처 : " + dbMember.getEp_phone_num());
			System.out.println("월급여(만원) : " + dbMember.getEp_salary());
			System.out.print("변경된 월급여(만원) : ");
			int salary = sc.nextInt();
			if(memberService.updateSalary(id, salary)) {
				System.out.println("[급여 변경 완료]");
			} else {
				System.out.println("[급여 변경 실패]");
			}			
		}
	}


	private void modifyDepartment() {
		System.out.print("수정할 직원 사번 : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[직원 조회 실패]");
		} else {
			MemberVO dbMember = memberService.selectMember(id);
			System.out.println("[직원 조회결과]");
			System.out.println("사번 : " + dbMember.getEp_id());
			System.out.println("이름 : " + dbMember.getEp_name());
			System.out.println("이메일 : " + dbMember.getEp_email());
			System.out.println("연락처 : " + dbMember.getEp_phone_num());
			System.out.println("부서 : " + dbMember.getEp_dept());
			System.out.println("-부서 목록-");
			List<DeptVO> deptList = memberService.selectAllDept();
			for(DeptVO tmp : deptList) {
				System.out.println(tmp.getDm_num() + ". " + tmp.getDm_name());
			}
			System.out.print("변경된 부서 번호 : ");
			int dept = sc.nextInt();
			sc.nextLine();
			System.out.print("부서 이동 사유 : ");
			String reason = sc.nextLine();
			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String today = date.format(formatter);
			if(memberService.updateDept(id, dept, reason, today, dbMember.getEp_id())) {
				System.out.println("[부서 변경 완료]");
			} else {
				System.out.println("[부서 변경 실패]");
			}
			
		}
			
	}


	private void modifyContact() {
		System.out.print("수정할 직원 사번 : ");
		String id = sc.next();
		if(memberService.selectMember(id) == null) {
			System.out.println("[직원 조회 실패]");
		} else {
			MemberVO dbMember = memberService.selectMember(id);
			System.out.println("[직원 조회결과]");
			System.out.println("사번 : " + dbMember.getEp_id());
			System.out.println("이름 : " + dbMember.getEp_name());
			System.out.println("이메일 : " + dbMember.getEp_email());
			System.out.println("연락처 : " + dbMember.getEp_phone_num());
			System.out.print("변경된 연락처 : ");
			String newPhone = sc.next();
			if(memberService.updatePhone(id, newPhone)) {
				System.out.println("[연락처 변경 완료]");
			} else {
				System.out.println("[연락처 변경 실패]");
				}
				
			}
			
		}
		


	private void searchMember() {
		System.out.print("조회할 직원 사번 : ");
		String id = sc.next();
		MemberVO dbMember = memberService.selectMember(id);
		if(dbMember == null) {
			System.out.println("[직원 조회 실패]");
		} else {
			System.out.println(dbMember);
		}
		
	}

	private void register() {
		
		System.out.print("직원 이름 : ");
		String name = sc.next();
		System.out.print("직원 이메일 : ");
		String email = sc.next();
		System.out.print("직원 연락처 : ");
		String phone = sc.next();
		System.out.print("협상 월급여(만원) : ");
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
			System.out.println("[직원 등록 완료]");
		} else {
			System.out.println("[직원 등록 실패]");
		}	
		
	}



}