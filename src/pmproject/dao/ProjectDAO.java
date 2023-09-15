package pmproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pmproject.vo.ProjectVO;

public interface ProjectDAO {

	List<ProjectVO> selectProjectNow();

	List<ProjectVO> selectProjectSoon();

	List<ProjectVO> selectProjectDone();

	List<ProjectVO> selectProjectAll();

	ProjectVO selectProject(@Param("pj_name")String pj_name);

	void insertProject(@Param("project")ProjectVO project);

	void updateName(@Param("pj_num")int pj_num, @Param("pj_name")String newName);

	void updateStart_date(@Param("pj_num")int pj_num, @Param("pj_start_date")String newStart_date);

	void updateEnd_date(@Param("pj_num")int pj_num, @Param("pj_end_date")String newEnd_date);

	void updateState(@Param("pj_num")int pj_num, @Param("pj_state")String newState);


	






}
