package pmproject.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pmproject.dao.TimeoffDAO;
import pmproject.vo.TimeoffVO;

public class TimeoffServiceImp implements TimeoffService{
	
	private TimeoffDAO timeoffDao;
	
	private final String MYBATIS_CONFIG_PATH = "pmproject/config/mybatis-config.xml";
	
	public TimeoffServiceImp() {
		try {
			InputStream is = Resources.getResourceAsStream(MYBATIS_CONFIG_PATH);
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
			SqlSession session = sf.openSession(true);
			timeoffDao = session.getMapper(TimeoffDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TimeoffServiceImp(TimeoffDAO timeoffDao) {
		this.timeoffDao = timeoffDao;
	}

	@Override
	public List<TimeoffVO> getTimeoffList() {
		return timeoffDao.selectTimeoffList();
	}

	@Override
	public boolean updateTimeoff(TimeoffVO timeoff) {
		if(timeoff == null) {
			return false;
		}
		TimeoffVO dbTimeoff = timeoffDao.selectTimeoff(timeoff.getTm_num());
		if(dbTimeoff == null) {
			return false;
		} else {
			dbTimeoff.setTm_date(timeoff.getTm_date());
			dbTimeoff.setTm_return_date(timeoff.getTm_return_date());
			dbTimeoff.setTm_reason(timeoff.getTm_reason());
			dbTimeoff.setTm_ep_id(timeoff.getTm_ep_id());
			if(timeoffDao.updateTimeoff(dbTimeoff) != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean insertTimeoff(TimeoffVO timeoff) {
		if(timeoff == null) {
			return false;
		}
		System.out.println("확인1");
		TimeoffVO timeOffID = timeoffDao.selectTimeoff(timeoff.getTm_num());
		if(timeOffID == null) {
			return false;
		} 
		System.out.println("확인2");
		if(timeoffDao.insertTimeoff(timeoff) != 0) {
			return true;
		}
		System.out.println("확인3");
		return false;
	}	
}
