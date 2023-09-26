package pmproject.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pmproject.dao.TimeoffDAO;
import pmproject.vo.LeaveVO;
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
		TimeoffVO dbTimeoff = timeoffDao.selectTimeoff(timeoff.getTm_ep_id());
		if(dbTimeoff == null) {
			return false;
		} else {
			dbTimeoff.setTm_date(timeoff.getTm_date());
			dbTimeoff.setTm_reason(timeoff.getTm_reason());
			if(timeoffDao.updateTimeoff(dbTimeoff) != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean insertTimeoff(LeaveVO timeoff) {
		if(timeoff == null) {
			return false;
		}
		timeoffDao.insertTimeoff(timeoff);
		return true;
	}

	@Override
	public TimeoffVO selectTimeoff(String ep_id) {
		if(ep_id == null) {
			return null;
		}
		return timeoffDao.selectTimeoff(ep_id);
	}

	@Override
	public List<LeaveVO> selectLeaveList(String ep_id) {
		if(ep_id == null) {
			return null;
		}
		return timeoffDao.selectLeaveList(ep_id);
	}

	@Override
	public void updateLeave(int le_num, String le_start_date, String le_end_date, String le_type) {
		if(le_start_date == null || le_end_date == null || le_type == null) {
			return;
		}
		if(timeoffDao.updateLeave(le_num, le_start_date, le_end_date, le_type)) {
			System.out.println("[휴가 수정 완료]");
		} else {
			System.out.println("[휴가 수정 실패]");
		}
		
	}	
}