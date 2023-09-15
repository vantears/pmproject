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
	public boolean insertProject(ProjectVO project) {
		if(project == null || project.getPj_name() == null) {
			return false;
		}
		ProjectVO dbProject = projectDao.selectProject(project.getPj_name());
		if(dbProject == null) {
			projectDao.insertProject(project);
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

	
	

}