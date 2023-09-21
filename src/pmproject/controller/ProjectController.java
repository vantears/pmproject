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
		System.out.println("=====������Ʈ=====");
		System.out.println("1. ������Ʈ �����޴�");
		System.out.println("2. ������Ʈ �����޴�");
		System.out.println("3. ���� �޴�");
		System.out.println("=================");
		System.out.print("�޴� ���� : ");
		
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
			System.out.println("[�����޴��� �̵�]");
			break;
		default:
			System.out.println("[�߸��� �޴� ����!]");
		}
		
	}
	
	private void projectMenu1() {
		System.out.println("");
		System.out.println("=====������Ʈ=====");
		System.out.println("1. ������Ʈ ������ȸ");
		System.out.println("2. ������Ʈ �����߰�");
		System.out.println("3. ������Ʈ ��������");
		//������Ʈ �ο� �˻�
		//��ȸ,�߰�,����
		System.out.println("4. ���� �޴�");
		System.out.println("=================");
		System.out.print("�޴� ���� : ");
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
			System.out.println("[�����޴��� �̵�]");
			break;
		default:
			System.out.println("[�߸��� �޴� ����!]");
		}
	}
	
	private void projectInfoModify() {
		List<ProjectVO> boardList = projectService.getPjAll();
		for(ProjectVO tmp : boardList) {
			System.out.println(tmp);
		}
		
		System.out.print("���� ������Ʈ �� : ");
		sc.nextLine();
		String name = sc.nextLine();
		ProjectVO dbProject = projectService.selectProject(name);
		if(dbProject == null) {
			System.out.println("");
			System.out.println("[�������� �ʴ� ������Ʈ]");
			System.out.println("");
		}else {
			System.out.println("=====================================");
			System.out.println("������ ���� �־��ּ��� - ���� ������ ���� �� ����");
			System.out.println("=====================================");
			System.out.print("������Ʈ �� : ");
			String newName = sc.nextLine();
			System.out.print("������ : ");
			String newStart_date = sc.nextLine();
			System.out.print("������ : ");
			String newEnd_date = sc.nextLine();
			System.out.print("���� : ");
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
			//�̰� ���� �Ʒ� �־�� �� ���ǽ��� �۵��� > ������Ʈ���� �ٲ�� �۵����� �ʱ� ����
			if(newName != "") {
				projectService.updateName(name, newName);		
			}
			System.out.println("");
			System.out.println("[������ ����Ǿ����ϴ�]");
			System.out.println("");
		}
		
	}


	private void projectInfoAdd() {
		System.out.print("������Ʈ �� : ");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.print("������ : ");
		String start_date = sc.nextLine();
		System.out.print("������ : ");
		String end_date = sc.nextLine();
		
		if(name == "" || (start_date == "" && end_date != "")) {
			System.out.println("[������Ʈ ��� ����]");
		}else {
			if(start_date == "" && end_date == "") {
				ProjectVO project = new ProjectVO(name);
				if(projectService.insertProject1(project)) {
					System.out.println("[������Ʈ ��� �Ϸ�]");
				}else {
					System.out.println("[������Ʈ ��� ����]");
				}
			}
			if(start_date != "" && end_date == "") {
				ProjectVO project = new ProjectVO(name, start_date);
				if(projectService.insertProject2(project)) {
					System.out.println("[������Ʈ ��� �Ϸ�]");
				}else {
					System.out.println("������Ʈ ��� ����]");
				}
			}
			if(start_date != "" && end_date != "") {
				ProjectVO project = new ProjectVO(name, start_date, end_date);
				if(projectService.insertProject3(project)) {
					System.out.println("[������Ʈ ��� �Ϸ�]");
				}else {
					System.out.println("[������Ʈ ��� ����]");
				}
			}
		}
	}

	private void projectInfoSearch() {
		System.out.println("");
		System.out.println("=======��ȸ=======");
		System.out.println("1. ������Ʈ ��ü��ȸ");
		System.out.println("2. �������� ������Ʈ");
		System.out.println("3. �������� ������Ʈ");
		System.out.println("4. ����� ������Ʈ");
		System.out.println("5. ���� �޴�");
		System.out.println("=================");
		System.out.print("�޴� ���� : ");
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
			System.out.println("[�����޴��� �̵�]");
			break;
		default:
			System.out.println("[�߸��� �޴� ����!]");
		}
	}


	private void projectAll() {
		System.out.println("");
		System.out.println("��ü ������Ʈ");
		List<ProjectVO> projectList = projectService.getPjAll();
		for(ProjectVO tmp : projectList) {
			System.out.println(tmp);
		}
	}


	private void projectDone() {
		System.out.println("");
		System.out.println("����� ������Ʈ");
		List<ProjectVO> projectList = projectService.getPjDone();
		for(ProjectVO tmp : projectList) {
			System.out.println(tmp);
		}
	}


	private void projectSoon() {
		System.out.println("");
		System.out.println("�������� ������Ʈ");
		List<ProjectVO> projectList = projectService.getPjSoon();
		for(ProjectVO tmp : projectList) {
			System.out.println(tmp);
		}
	}


	private void projectNow() {
		System.out.println("");
		System.out.println("�������� ������Ʈ");
		List<ProjectVO> projectList = projectService.getPjNow();
		for(ProjectVO tmp : projectList) {
			System.out.println(tmp);
		}
	}
	
	
	//=============================������Ʈ �ο�===================================================
	
	
	private void projectMenu2() {
		System.out.println("");
		System.out.println("=====������Ʈ=====");
		System.out.println("1. ������Ʈ ������ȸ");
		System.out.println("2. ������Ʈ �����߰�");
		System.out.println("3. ������Ʈ ��������");
		System.out.println("4. ������Ʈ ��������");
		System.out.println("5. ���� �޴�");
		System.out.println("=================");
		System.out.print("�޴� ���� : ");
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
			System.out.println("[�����޴��� �̵�]");
			break;
		default:
			System.out.println("[�߸��� �޴� ����!]");
		}
	}
	
	


	private void projectEmpDelete() {
		System.out.print("����ID : ");
		sc.nextLine();
		String id = sc.nextLine();
		int num = 0;
		if(memberService.selectMember(id) == null) {
			System.out.println("[���� ��ȸ ����]");
		}else {
			System.out.println("");
			System.out.println("[������ ������Ʈ]");
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
					System.out.print("��Ϲ�ȣ : " + tmp1.getPi_num() + ", ");
					List<MemberVO> memberList = memberService.getMemberList();
					for(MemberVO tmp2 : memberList) {
						if(tmp2.getEp_id().equals(tmp1.getPi_ep_id())) {
							System.out.print("�����̸� : " + tmp2.getEp_name() + ", ");
						}
					}
					System.out.println(tmp1);
				}
			}
			System.out.print("������ ��Ϲ�ȣ : ");
			int piNum = sc.nextInt();
			Project_infoVO dbProject_info = projectService.selectProject_info(piNum);
			if(dbProject_info == null) {
				System.out.println("");
				System.out.println("[�������� �ʴ� ��Ϲ�ȣ]");
				System.out.println("");
			}else {
				projectService.deleteProject_info(piNum);
				System.out.println("[������ �����Ǿ����ϴ�]");
			}
		}
		
	}


	private void projectEmpModify() {
		System.out.print("����ID : ");
		sc.nextLine();
		String id = sc.nextLine();
		int num = 0;
		if(memberService.selectMember(id) == null) {
			System.out.println("[���� ��ȸ ����]");
		}else {
			System.out.println("");
			System.out.println("[������ ������Ʈ]");
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
					System.out.print("��Ϲ�ȣ : " + tmp1.getPi_num() + ", ");
					List<MemberVO> memberList = memberService.getMemberList();
					for(MemberVO tmp2 : memberList) {
						if(tmp2.getEp_id().equals(tmp1.getPi_ep_id())) {
							System.out.print("�����̸� : " + tmp2.getEp_name() + ", ");
						}
					}
					System.out.println(tmp1);
				}
			}
			System.out.println("");
			System.out.print("������ ��Ϲ�ȣ : ");
			int piNum = sc.nextInt();
			Project_infoVO dbProject_info = projectService.selectProject_info(piNum);
			if(dbProject_info == null) {
				System.out.println("");
				System.out.println("[�������� �ʴ� ��Ϲ�ȣ]");
				System.out.println("");
			}else {
				System.out.println("=====================================");
				System.out.println("������ ���� �־��ּ��� - ���� ������ ���� �� ����");
				System.out.println("=====================================");
				System.out.print("������Ʈ ��ȣ : ");
				sc.nextLine();
				String newPjNum = sc.nextLine();
				System.out.print("���� : ");
				String newRole = sc.nextLine();
				
				if(newRole != "") {
					projectService.updateRole(piNum, newRole);
				}
				if(newPjNum != "") {
					int iv = Integer.parseInt(newPjNum);
					ProjectVO dbProject = projectService.selectProject(iv);
					if(dbProject == null) {
						System.out.println("[�������� �ʴ� ������Ʈ]");
						return;
					}else {
						projectService.updatePjNum(piNum, iv);
					}
				}
				System.out.println("");
				System.out.println("[������ ����Ǿ����ϴ�]");
				System.out.println("");
			}
		}
		
	}


	private void projectEmpAdd() {
		List<ProjectVO> boardList = projectService.getPjAll();
		for(ProjectVO tmp : boardList) {
			System.out.println(tmp);
		}System.out.println("");
		
		System.out.print("����ID : ");
		sc.nextLine();
		String id = sc.nextLine();
		System.out.print("������Ʈ ��ȣ : ");
		int num = sc.nextInt();
		System.out.print("�������� : ");
		sc.nextLine();
		String role = sc.nextLine();
		
		if(id == "" || num == 0 || role == "") {
			System.out.println("[�߸� �Է��߽��ϴ�]");
		}else {
			Project_infoVO project_info = new Project_infoVO(id, num, role);
			if(projectService.insertProject_info(project_info)) {
				System.out.println("[������Ʈ ��� �Ϸ�]");
			}else {
				System.out.println("[������Ʈ ��� ����]");
			}
		}
		
	}


	private void projectEmpSearch() {
		System.out.println("");
		System.out.println("=======��ȸ=======");
		System.out.println("1. ������Ʈ ���� ��ü��ȸ");
		System.out.println("2. ������Ʈ ��ȣ ��ȸ ");
		System.out.println("3. ������Ʈ ����ID ��ȸ");
		System.out.println("4. ���� �޴�");
		System.out.println("=================");
		System.out.print("�޴� ���� : ");
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
			System.out.println("[�����޴��� �̵�]");
			break;
		default:
			System.out.println("[�߸��� �޴� ����!]");
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
					System.out.print("�����̸� : " + tmp2.getEp_name() + ", ");
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
		
		System.out.print("������Ʈ ��ȣ : ");
		int num = sc.nextInt();
		ProjectVO dbProject = projectService.selectProject(num);
		if(dbProject == null) {
			System.out.println("[���������ʴ� ������Ʈ]");
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
							System.out.print("�����̸� : " + tmp2.getEp_name() + ", ");
						}
					}
					System.out.println(tmp1);
				}
			}	
		}
		
	}

	private void projectIdEmp() {
		System.out.print("����ID : ");
		sc.nextLine();
		String id = sc.nextLine();
		int num = 0;
		if(memberService.selectMember(id) == null) {
			System.out.println("[���� ��ȸ ����]");
		}else {
			System.out.println("");
			System.out.println("[������ ������Ʈ]");
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
							System.out.print("�����̸� : " + tmp2.getEp_name() + ", ");
						}
					}
					System.out.println(tmp1);
				}
			}	
		}
	}

}
