package pmproject.service;

import java.util.List;

import pmproject.vo.ProjectVO;

public interface ProjectService {

	List<ProjectVO> getPjNow();

	List<ProjectVO> getPjSoon();

	List<ProjectVO> getPjDone();

	List<ProjectVO> getPjAll();

	boolean insertProject(ProjectVO project);

	ProjectVO selectProject(String name);

	boolean updateName(String name, String newName);

	boolean updateStart_date(String name, String newStart_date);

	boolean updateEnd_date(String name, String newEnd_date);

	boolean updateState(String name, String newState);


	



}
