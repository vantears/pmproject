package pmproject.service;

import java.util.List;

import pmproject.vo.ProjectVO;
import pmproject.vo.Project_infoVO;

public interface ProjectService {

	List<ProjectVO> getPjNow();

	List<ProjectVO> getPjSoon();

	List<ProjectVO> getPjDone();

	List<ProjectVO> getPjAll();

	boolean insertProject1(ProjectVO project);
	
	boolean insertProject2(ProjectVO project);
	
	boolean insertProject3(ProjectVO project);

	ProjectVO selectProject(String name);

	boolean updateName(String name, String newName);

	boolean updateStart_date(String name, String newStart_date);

	boolean updateEnd_date(String name, String newEnd_date);

	boolean updateState(String name, String newState);

	ProjectVO selectProject(int num);

	List<Project_infoVO> getPjAllEmp2();

	List<ProjectVO> getPjAllEmp1();

	boolean insertProject_info(Project_infoVO project_info);

	Project_infoVO selectProject_info(int piNum);

	boolean updateRole(int piNum, String newRole);

	boolean updatePjNum(int piNum, int newPjNum);

	boolean deleteProject_info(int piNum);


	



}
