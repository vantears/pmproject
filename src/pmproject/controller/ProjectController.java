package pmproject.controller;

import java.util.List;
import java.util.Scanner;

import pmproject.service.MemberService;
import pmproject.service.MemberServiceImp;
import pmproject.service.ProjectService;
import pmproject.service.ProjectServiceImp;
import pmproject.vo.MemberVO;
import pmproject.vo.ProjectVO;
import pmproject.vo.Project_infoVO;

public class ProjectController {

	private ProjectService projectService = new ProjectServiceImp();
	private MemberService memberService = new MemberServiceImp();
	Scanner sc = new Scanner(System.in);

	public void run() {
		int menu;
		final int EXIT = 3;
		do {
			printMenu();
			menu = sc.nextInt();
			runMenu(menu);
		}while(menu != EXIT);
		
	}


	private void printMenu() {
		System.out.println("");
		System.out.println("=====프로젝트=====");
		System.out.println("1. 프로젝트 정보메뉴");
		System.out.println("2. 프로젝트 직원메뉴");
		System.out.println("3. 이전 메뉴");
		System.out.println("=================");
		System.out.print("메뉴 선택 : ");
		
	}

	private void runMenu(int menu) {
		switch(menu) {
		case 1:
			projectMenu1();
			break;
		case 2:
			projectMenu2();
			break;
		case 3:
			System.out.println("[이전메뉴로 이동]");
			break;
		default:
			System.out.println("[잘못된 메뉴 선택!]");
		}
		
	}
	
	private void projectMenu1() {
		System.out.println("");
		System.out.println("=====프로젝트=====");
		System.out.println("1. 프로젝트 정보조회");
		System.out.println("2. 프로젝트 정보추가");
		System.out.println("3. 프로젝트 정보수정");
		//프로젝트 인원 검색
		//조회,추가,수정
		System.out.println("4. 이전 메뉴");
		System.out.println("=================");
		System.out.print("메뉴 선택 : ");
		int pjMenu = sc.nextInt();
		
		switch(pjMenu) {
		case 1:
			projectInfoSearch();
			break;
		case 2:
			projectInfoAdd();
			break;
		case 3:
			projectInfoModify();
			break;
		case 4:
			System.out.println("[이전메뉴로 이동]");
			break;
		default:
			System.out.println("[잘못된 메뉴 선택!]");
		}
	}
	
	private void projectInfoModify() {
		List<ProjectVO> boardList = projectService.getPjAll();
		for(ProjectVO tmp : boardList) {
			System.out.println(tmp);
		}
		
		System.out.print("기존 프로젝트 명 : ");
		sc.nextLine();
		String name = sc.nextLine();
		ProjectVO dbProject = projectService.selectProject(name);
		if(dbProject == null) {
			System.out.println("");
			System.out.println("[존재하지 않는 프로젝트]");
			System.out.println("");
		}else {
			System.out.println("=====================================");
			System.out.println("수정할 값을 넣어주세요 - 변경 내용이 없을 시 엔터");
			System.out.println("=====================================");
			System.out.print("프로젝트 명 : ");
			String newName = sc.nextLine();
			System.out.print("시작일 : ");
			String newStart_date = sc.nextLine();
			System.out.print("종료일 : ");
			String newEnd_date = sc.nextLine();
			System.out.print("상태 : ");
			String newState = sc.nextLine();
			
			
			if(newStart_date != "") {
				projectService.updateStart_date(name, newStart_date);
			}
			if(newEnd_date != "") {
				projectService.updateEnd_date(name, newEnd_date);
			}
			if(newState != "") {
				projectService.updateState(name, newState);
			}
			//이게 가장 아래 있어야 위 조건식이 작동함 > 프로젝트명이 바뀌면 작동하지 않기 때문
			if(newName != "") {
				projectService.updateName(name, newName);		
			}
			System.out.println("");
			System.out.println("[정보가 변경되었습니다]");
			System.out.println("");
		}
		
	}


	private void projectInfoAdd() {
		System.out.print("프로젝트 명 : ");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.print("시작일 : ");
		String start_date = sc.nextLine();
		System.out.print("종료일 : ");
		String end_date = sc.nextLine();
		
		if(name == "" || (start_date == "" && end_date != "")) {
			System.out.println("[프로젝트 등록 실패]");
		}else {
			if(start_date == "" && end_date == "") {
				ProjectVO project = new ProjectVO(name);
				if(projectService.insertProject1(project)) {
					System.out.println("[프로젝트 등록 완료]");
				}else {
					System.out.println("[프로젝트 등록 실패]");
				}
			}
			if(start_date != "" && end_date == "") {
				ProjectVO project = new ProjectVO(name, start_date);
				if(projectService.insertProject2(project)) {
					System.out.println("[프로젝트 등록 완료]");
				}else {
					System.out.println("프로젝트 등록 실패]");
				}
			}
			if(start_date != "" && end_date != "") {
				ProjectVO project = new ProjectVO(name, start_date, end_date);
				if(projectService.insertProject3(project)) {
					System.out.println("[프로젝트 등록 완료]");
				}else {
					System.out.println("[프로젝트 등록 실패]");
				}
			}
		}
	}

	private void projectInfoSearch() {
		System.out.println("");
		System.out.println("=======조회=======");
		System.out.println("1. 프로젝트 전체조회");
		System.out.println("2. 진행중인 프로젝트");
		System.out.println("3. 예정중인 프로젝트");
		System.out.println("4. 종료된 프로젝트");
		System.out.println("5. 이전 메뉴");
		System.out.println("=================");
		System.out.print("메뉴 선택 : ");
		int pjSearchMenu = sc.nextInt();
		
		switch (pjSearchMenu) {
		case 1: 
			projectAll();
			break;
		case 2: 
			projectNow();
			break;
		case 3: 
			projectSoon();
			break;
		case 4: 
			projectDone();
			break;
		case 5:
			System.out.println("[이전메뉴로 이동]");
			break;
		default:
			System.out.println("[잘못된 메뉴 선택!]");
		}
	}


	private void projectAll() {
		System.out.println("");
		System.out.println("전체 프로젝트");
		List<ProjectVO> projectList = projectService.getPjAll();
		for(ProjectVO tmp : projectList) {
			System.out.println(tmp);
		}
	}


	private void projectDone() {
		System.out.println("");
		System.out.println("종료된 프로젝트");
		List<ProjectVO> projectList = projectService.getPjDone();
		for(ProjectVO tmp : projectList) {
			System.out.println(tmp);
		}
	}


	private void projectSoon() {
		System.out.println("");
		System.out.println("예정중인 프로젝트");
		List<ProjectVO> projectList = projectService.getPjSoon();
		for(ProjectVO tmp : projectList) {
			System.out.println(tmp);
		}
	}


	private void projectNow() {
		System.out.println("");
		System.out.println("진행중인 프로젝트");
		List<ProjectVO> projectList = projectService.getPjNow();
		for(ProjectVO tmp : projectList) {
			System.out.println(tmp);
		}
	}
	
	
	//=============================프로젝트 인원===================================================
	
	
	private void projectMenu2() {
		System.out.println("");
		System.out.println("=====프로젝트=====");
		System.out.println("1. 프로젝트 직원조회");
		System.out.println("2. 프로젝트 직원추가");
		System.out.println("3. 프로젝트 직원수정");
		System.out.println("4. 프로젝트 직원삭제");
		System.out.println("5. 이전 메뉴");
		System.out.println("=================");
		System.out.print("메뉴 선택 : ");
		int pjMenu = sc.nextInt();
		
		switch(pjMenu) {
		case 1:
			projectEmpSearch();
			break;
		case 2:
			projectEmpAdd();
			break;
		case 3:
			projectEmpModify();
			break;
		case 4:
			projectEmpDelete();
			break;
		case 5:
			System.out.println("[이전메뉴로 이동]");
			break;
		default:
			System.out.println("[잘못된 메뉴 선택!]");
		}
	}
	
	


	private void projectEmpDelete() {
		System.out.print("직원ID : ");
		sc.nextLine();
		String id = sc.nextLine();
		int num = 0;
		if(memberService.selectMember(id) == null) {
			System.out.println("[직원 조회 실패]");
		}else {
			System.out.println("");
			System.out.println("[참여한 프로젝트]");
			List<Project_infoVO> infoList = projectService.getPjAllEmp2();
			for(Project_infoVO tmp1 : infoList) {
				if(tmp1.getPi_ep_id().equals(id)){
					num = tmp1.getPi_pj_num();
					List<ProjectVO> List = projectService.getPjAllEmp1();
					for(ProjectVO tmp : List) {
						if(tmp.getPj_num() == num) {
							System.out.println(tmp);
						}
					}
					System.out.print("등록번호 : " + tmp1.getPi_num() + ", ");
					List<MemberVO> memberList = memberService.getMemberList();
					for(MemberVO tmp2 : memberList) {
						if(tmp2.getEp_id().equals(tmp1.getPi_ep_id())) {
							System.out.print("직원이름 : " + tmp2.getEp_name() + ", ");
						}
					}
					System.out.println(tmp1);
				}
			}
			System.out.print("삭제할 등록번호 : ");
			int piNum = sc.nextInt();
			Project_infoVO dbProject_info = projectService.selectProject_info(piNum);
			if(dbProject_info == null) {
				System.out.println("");
				System.out.println("[존재하지 않는 등록번호]");
				System.out.println("");
			}else {
				projectService.deleteProject_info(piNum);
				System.out.println("[정보가 삭제되었습니다]");
			}
		}
		
	}


	private void projectEmpModify() {
		System.out.print("직원ID : ");
		sc.nextLine();
		String id = sc.nextLine();
		int num = 0;
		if(memberService.selectMember(id) == null) {
			System.out.println("[직원 조회 실패]");
		}else {
			System.out.println("");
			System.out.println("[참여한 프로젝트]");
			List<Project_infoVO> infoList = projectService.getPjAllEmp2();
			for(Project_infoVO tmp1 : infoList) {
				if(tmp1.getPi_ep_id().equals(id)){
					num = tmp1.getPi_pj_num();
					List<ProjectVO> List = projectService.getPjAllEmp1();
					for(ProjectVO tmp : List) {
						if(tmp.getPj_num() == num) {
							System.out.println(tmp);
						}
					}
					System.out.print("등록번호 : " + tmp1.getPi_num() + ", ");
					List<MemberVO> memberList = memberService.getMemberList();
					for(MemberVO tmp2 : memberList) {
						if(tmp2.getEp_id().equals(tmp1.getPi_ep_id())) {
							System.out.print("직원이름 : " + tmp2.getEp_name() + ", ");
						}
					}
					System.out.println(tmp1);
				}
			}
			System.out.println("");
			System.out.print("수정할 등록번호 : ");
			int piNum = sc.nextInt();
			Project_infoVO dbProject_info = projectService.selectProject_info(piNum);
			if(dbProject_info == null) {
				System.out.println("");
				System.out.println("[존재하지 않는 등록번호]");
				System.out.println("");
			}else {
				System.out.println("=====================================");
				System.out.println("수정할 값을 넣어주세요 - 변경 내용이 없을 시 엔터");
				System.out.println("=====================================");
				System.out.print("프로젝트 번호 : ");
				sc.nextLine();
				String newPjNum = sc.nextLine();
				System.out.print("역할 : ");
				String newRole = sc.nextLine();
				
				if(newRole != "") {
					projectService.updateRole(piNum, newRole);
				}
				if(newPjNum != "") {
					int iv = Integer.parseInt(newPjNum);
					ProjectVO dbProject = projectService.selectProject(iv);
					if(dbProject == null) {
						System.out.println("[존재하지 않는 프로젝트]");
						return;
					}else {
						projectService.updatePjNum(piNum, iv);
					}
				}
				System.out.println("");
				System.out.println("[정보가 변경되었습니다]");
				System.out.println("");
			}
		}
		
	}


	private void projectEmpAdd() {
		List<ProjectVO> boardList = projectService.getPjAll();
		for(ProjectVO tmp : boardList) {
			System.out.println(tmp);
		}System.out.println("");
		
		System.out.print("직원ID : ");
		sc.nextLine();
		String id = sc.nextLine();
		System.out.print("프로젝트 번호 : ");
		int num = sc.nextInt();
		System.out.print("직원역할 : ");
		sc.nextLine();
		String role = sc.nextLine();
		
		if(id == "" || num == 0 || role == "") {
			System.out.println("[잘못 입력했습니다]");
		}else {
			Project_infoVO project_info = new Project_infoVO(id, num, role);
			if(projectService.insertProject_info(project_info)) {
				System.out.println("[프로젝트 등록 완료]");
			}else {
				System.out.println("[프로젝트 등록 실패]");
			}
		}
		
	}


	private void projectEmpSearch() {
		System.out.println("");
		System.out.println("=======조회=======");
		System.out.println("1. 프로젝트 직원 전체조회");
		System.out.println("2. 프로젝트 번호 조회 ");
		System.out.println("3. 프로젝트 직원ID 조회");
		System.out.println("4. 이전 메뉴");
		System.out.println("=================");
		System.out.print("메뉴 선택 : ");
		int pjSearchMenu = sc.nextInt();
		
		switch (pjSearchMenu) {
		case 1: 
			projectAllEmp();
			break;
		case 2: 
			projectNumEmp();
			break;
		case 3: 
			projectIdEmp();
			break;
		case 4: 
			System.out.println("[이전메뉴로 이동]");
			break;
		default:
			System.out.println("[잘못된 메뉴 선택!]");
		}
		
	}
	
	private void projectAllEmp() {
		List<ProjectVO> projectList = projectService.getPjAllEmp1();
		List<Project_infoVO> infoList = projectService.getPjAllEmp2();
		List<MemberVO> memberList = memberService.getMemberList();
		
		int i = 1;
		for(Project_infoVO tmp1 : infoList) {
			if(tmp1.getPi_pj_num() == i) {
				for(ProjectVO tmp : projectList) {
					if(tmp.getPj_num() == i) {
						System.out.println(tmp);
					}
				}i++;
			}
			for(MemberVO tmp2 : memberList) {
				if(tmp2.getEp_id().equals(tmp1.getPi_ep_id())) {
					System.out.print("직원이름 : " + tmp2.getEp_name() + ", ");
				}
			}
			System.out.println(tmp1);
		}	
	}
	
	private void projectNumEmp() {
		List<ProjectVO> boardList = projectService.getPjAll();
		for(ProjectVO tmp : boardList) {
			System.out.println(tmp);
		}System.out.println("");
		
		System.out.print("프로젝트 번호 : ");
		int num = sc.nextInt();
		ProjectVO dbProject = projectService.selectProject(num);
		if(dbProject == null) {
			System.out.println("[존재하지않는 프로젝트]");
		}else {
			List<ProjectVO> List = projectService.getPjAllEmp1();
			List<Project_infoVO> infoList = projectService.getPjAllEmp2();
			List<MemberVO> memberList = memberService.getMemberList();
			
			for(ProjectVO tmp : List) {
				if(tmp.getPj_num() == num) {
					System.out.println(tmp);
				}
			}
			for(Project_infoVO tmp1 : infoList) {
				if(tmp1.getPi_pj_num() == num) {
					for(MemberVO tmp2 : memberList) {
						if(tmp2.getEp_id().equals(tmp1.getPi_ep_id())) {
							System.out.print("직원이름 : " + tmp2.getEp_name() + ", ");
						}
					}
					System.out.println(tmp1);
				}
			}	
		}
		
	}

	private void projectIdEmp() {
		System.out.print("직원ID : ");
		sc.nextLine();
		String id = sc.nextLine();
		int num = 0;
		if(memberService.selectMember(id) == null) {
			System.out.println("[직원 조회 실패]");
		}else {
			System.out.println("");
			System.out.println("[참여한 프로젝트]");
			List<Project_infoVO> infoList = projectService.getPjAllEmp2();
			List<MemberVO> memberList = memberService.getMemberList();
			for(Project_infoVO tmp1 : infoList) {
				if(tmp1.getPi_ep_id().equals(id)){
					num = tmp1.getPi_pj_num();
					List<ProjectVO> List = projectService.getPjAllEmp1();
					for(ProjectVO tmp : List) {
						if(tmp.getPj_num() == num) {
							System.out.println(tmp);
						}
					}
					for(MemberVO tmp2 : memberList) {
						if(tmp2.getEp_id().equals(tmp1.getPi_ep_id())) {
							System.out.print("직원이름 : " + tmp2.getEp_name() + ", ");
						}
					}
					System.out.println(tmp1);
				}
			}	
		}
	}

}
