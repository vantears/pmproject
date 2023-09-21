package pmproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pmproject.vo.ProjectVO;
import pmproject.vo.Project_infoVO;

public interface ProjectDAO {

	List<ProjectVO> selectProjectNow();

	List<ProjectVO> selectProjectSoon();

	List<ProjectVO> selectProjectDone();

	List<ProjectVO> selectProjectAll();

	ProjectVO selectProject(@Param("pj_name")String pj_name);

	void insertProject1(@Param("project")ProjectVO project);
	
	void insertProject2(@Param("project")ProjectVO project);
	
	void insertProject3(@Param("project")ProjectVO project);

	void updateName(@Param("pj_num")int pj_num, @Param("pj_name")String newName);

	void updateStart_date(@Param("pj_num")int pj_num, @Param("pj_start_date")String newStart_date);

	void updateEnd_date(@Param("pj_num")int pj_num, @Param("pj_end_date")String newEnd_date);

	void updateState(@Param("pj_num")int pj_num, @Param("pj_state")String newState);

	ProjectVO selectProjectNum(@Param("pj_num")int num);

	List<ProjectVO> selectPjAllEmp1();
	
	List<Project_infoVO> selectPjAllEmp2();

	void insertProject_info(@Param("project_info")Project_infoVO project_info);

	Project_infoVO selectProject_infoNum(@Param("pi_num")int piNum);

	void updateRole(@Param("pi_num")int pi_num, @Param("pi_role")String newRole);

	void updatePjNum(@Param("pi_num")int pi_num, @Param("pi_pj_num")int newPjNum);

	boolean deleteProject_info(@Param("pi_num")int piNum);



	

	



	






}