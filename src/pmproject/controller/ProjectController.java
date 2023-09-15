package pmproject.controller;

import java.util.List;
import java.util.Scanner;

import pmproject.service.ProjectService;
import pmproject.service.ProjectServiceImp;
import pmproject.vo.ProjectVO;

public class ProjectController {

	private ProjectService projectService = new ProjectServiceImp();
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
		
	}

	private void runMenu(int menu) {
		switch(menu) {
		case 1:
			projectSearch();
			break;
		case 2:
			projectAdd();
			break;
		case 3:
			projectModify();
			break;
		case 4:
			System.out.println("[�����޴��� �̵�]");
			break;
		default:
			System.out.println("[�߸��� �޴� ����!]");
		}
		
	}


	private void projectModify() {
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
			System.out.println("�������� �ʴ� ������Ʈ");
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
			System.out.println("������Ʈ ������ ����Ǿ����ϴ�");
			System.out.println("");
		}
		
	}


	private void projectAdd() {
		//�̸� �ߺ�üũ �߰��ؾ���
		System.out.print("������Ʈ �� : ");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.print("������ : ");
		String start_date = sc.nextLine();
		System.out.print("������ : ");
		String end_date = sc.nextLine();
		System.out.print("���� : ");
		String state = sc.nextLine();
	
		ProjectVO project = new ProjectVO(name, start_date, end_date, state);
		if(projectService.insertProject(project)) {
			System.out.println("������Ʈ ��� �Ϸ�");
		}else {
			System.out.println("������Ʈ ��� ����");
		}
	}

	private void projectSearch() {
		System.out.println("");
		System.out.println("=======��ȸ=======");
		System.out.println("1. ������Ʈ ��ü��ȸ");
		System.out.println("2. �������� ������Ʈ");
		System.out.println("3. �������� ������Ʈ");
		System.out.println("4. ����� ������Ʈ");
		System.out.println("4. ���� �޴�");
		System.out.println("=================");
		System.out.print("�޴� ���� : ");
		int pjSearchMenu = sc.nextInt();
		
		switch (pjSearchMenu) {
		case 1: {
			projectAll();
			break;
		}
		case 2: {
			projectNow();
			break;
		}
		case 3: {
			projectSoon();
			break;
		}
		case 4: {
			projectDone();
			break;
		}
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
		List<ProjectVO> boardList = projectService.getPjAll();
		for(ProjectVO tmp : boardList) {
			System.out.println(tmp);
		}
	}


	private void projectDone() {
		System.out.println("");
		System.out.println("����� ������Ʈ");
		List<ProjectVO> boardList = projectService.getPjDone();
		for(ProjectVO tmp : boardList) {
			System.out.println(tmp);
		}
	}


	private void projectSoon() {
		System.out.println("");
		System.out.println("�������� ������Ʈ");
		List<ProjectVO> boardList = projectService.getPjSoon();
		for(ProjectVO tmp : boardList) {
			System.out.println(tmp);
		}
	}


	private void projectNow() {
		System.out.println("");
		System.out.println("�������� ������Ʈ");
		List<ProjectVO> boardList = projectService.getPjNow();
		for(ProjectVO tmp : boardList) {
			System.out.println(tmp);
		}
	}
	
}
