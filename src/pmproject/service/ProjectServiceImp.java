package pmproject.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pmproject.dao.ProjectDAO;
import pmproject.vo.ProjectVO;
import pmproject.vo.Project_infoVO;

public class ProjectServiceImp implements ProjectService{

	private ProjectDAO projectDao;
	private final String MYBATIS_CONFIG_PATH = "pmproject/config/mybatis-config.xml";
	
	public ProjectServiceImp() {
		try {
			InputStream is = Resources.getResourceAsStream(MYBATIS_CONFIG_PATH);
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
			SqlSession session = sf.openSession(true);
			projectDao = session.getMapper(ProjectDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ProjectVO> getPjNow() {
		return projectDao.selectProjectNow();
	}

	@Override
	public List<ProjectVO> getPjSoon() {
		return projectDao.selectProjectSoon();
	}

	@Override
	public List<ProjectVO> getPjDone() {
		return projectDao.selectProjectDone();
	}

	@Override
	public List<ProjectVO> getPjAll() {
		return projectDao.selectProjectAll();
	}

	@Override
	public boolean insertProject1(ProjectVO project) {
		if(project == null || project.getPj_name() == null) {
			return false;
		}
		ProjectVO dbProject = projectDao.selectProject(project.getPj_name());
		if(dbProject == null) {
			projectDao.insertProject1(project);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean insertProject2(ProjectVO project) {
		if(project == null || project.getPj_name() == null) {
			return false;
		}
		ProjectVO dbProject = projectDao.selectProject(project.getPj_name());
		if(dbProject == null) {
			projectDao.insertProject2(project);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean insertProject3(ProjectVO project) {
		if(project == null || project.getPj_name() == null) {
			return false;
		}
		ProjectVO dbProject = projectDao.selectProject(project.getPj_name());
		if(dbProject == null) {
			projectDao.insertProject3(project);
			return true;
		}
		
		return false;
	}

	@Override
	public ProjectVO selectProject(String name) {
		if(name == null) {
			return null;
		}
		ProjectVO dbProject = projectDao.selectProject(name);
		if(dbProject == null) {
			return null;
		}
		return dbProject;
	}

	@Override
	public boolean updateName(String name, String newName) {
		
		if(name == null || newName == null) {
			return false;
		}
		ProjectVO dbProject = projectDao.selectProject(name);
		if(dbProject == null) {
			return false;
		}
		projectDao.updateName(dbProject.getPj_num(), newName);
		return true;
	}

	@Override
	public boolean updateStart_date(String name, String newStart_date) {
		if(name == null || newStart_date == null) {
			return false;
		}
		ProjectVO dbProject = projectDao.selectProject(name);
		if(dbProject == null) {
			return false;
		}
		projectDao.updateStart_date(dbProject.getPj_num(), newStart_date);
		return true;
	}

	@Override
	public boolean updateEnd_date(String name, String newEnd_date) {
		if(name == null || newEnd_date == null) {
			return false;
		}
		ProjectVO dbProject = projectDao.selectProject(name);
		if(dbProject == null) {
			return false;
		}
		projectDao.updateEnd_date(dbProject.getPj_num(), newEnd_date);
		return true;
	}

	@Override
	public boolean updateState(String name, String newState) {
		if(name == null || newState == null) {
			return false;
		}
		ProjectVO dbProject = projectDao.selectProject(name);
		if(dbProject == null) {
			return false;
		}
		projectDao.updateState(dbProject.getPj_num(), newState);
		return true;
	}

	@Override
	public ProjectVO selectProject(int num) {
		
		ProjectVO dbProject = projectDao.selectProjectNum(num);
		if(dbProject == null) {
			return null;
		}
		return dbProject;
	}
	
	@Override
	public List<ProjectVO> getPjAllEmp1() {
		return projectDao.selectPjAllEmp1();
	}

	@Override
	public List<Project_infoVO> getPjAllEmp2() {
		return projectDao.selectPjAllEmp2();
	}

	@Override
	public boolean insertProject_info(Project_infoVO project_info) {
		if(project_info == null || project_info.getPi_ep_id() == null || project_info.getPi_role() == null) {
			return false;
		}
		projectDao.insertProject_info(project_info);
		return true;
	}

	@Override
	public Project_infoVO selectProject_info(int piNum) {
		Project_infoVO dbProject_info = projectDao.selectProject_infoNum(piNum);
		if(dbProject_info == null) {
			return null;
		}
		return dbProject_info;
	}

	@Override
	public boolean updateRole(int piNum, String newRole) {
		Project_infoVO dbProject_piNum_info = projectDao.selectProject_infoNum(piNum);
		if(dbProject_piNum_info == null) {
			return false;
		}
		projectDao.updateRole(dbProject_piNum_info.getPi_num(), newRole);
		return true;
	}

	@Override
	public boolean updatePjNum(int piNum, int newPjNum) {
		Project_infoVO dbProject_piNum_info = projectDao.selectProject_infoNum(piNum);
		if(dbProject_piNum_info == null) {
			return false;
		}
		Project_infoVO dbProject_newpjNum_info = projectDao.selectProject_infoNum(newPjNum);
		if(dbProject_newpjNum_info == null) {
			return false;
		}
		projectDao.updatePjNum(dbProject_piNum_info.getPi_num(), newPjNum);
		return true;
	}

	@Override
	public boolean deleteProject_info(int piNum) {
		return projectDao.deleteProject_info(piNum);
	}

	
	
	
	

}