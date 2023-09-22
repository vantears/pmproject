package pmproject.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pmproject.dao.AttendanceDAO;
import pmproject.dao.MemberDAO;
import pmproject.dao.AttendanceDAO;
import pmproject.dao.ProjectDAO;
import pmproject.vo.AttendanceVO;
import pmproject.vo.MemberVO;
import pmproject.vo.AttendanceRecordVO;

public class AttendanceServiceImp implements AttendanceService {

    private AttendanceDAO attendanceDao; // AttendanceDAO를 사용하기 위한 인스턴스 생성
    private MemberDAO memberDao;
    // 생성자를 통해 AttendanceDAO를 주입받도록 변경
    private final String MYBATIS_CONFIG_PATH = "pmproject/config/mybatis-config.xml";
    
    public AttendanceServiceImp() {
		try {
			InputStream is = Resources.getResourceAsStream(MYBATIS_CONFIG_PATH);
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
			SqlSession session = sf.openSession(true);
			attendanceDao = session.getMapper(AttendanceDAO.class);
			memberDao = session.getMapper(MemberDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean recordLeave(String employeeId) {
		if(employeeId == null) {
			return false;
		}
		LocalDate nowDate = LocalDate.now();
		LocalTime nowTime = LocalTime.now();
		String formattedDateTime = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String formattedDateTime2 = nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	    AttendanceVO dbAt = attendanceDao.selectAttendance(employeeId, formattedDateTime);
	    if(dbAt == null) {
	    	return false;	    	
	    }
	    if(dbAt.getAd_at_num() != 2) {
	    	return false;
	    }
	    AttendanceRecordVO dbAtRecord = attendanceDao.selectAtrecord(dbAt.getAd_num());
	    if(dbAtRecord == null) {
	    	return false;
	    }
	    attendanceDao.updateEndTime(dbAtRecord.getAr_ad_num(), formattedDateTime2);
	    return true;
	}

	@Override
	public List<AttendanceVO> viewAttendance(String employeeId) {
		if(employeeId == null) {
			return null;
		}
		return attendanceDao.selectAllAttendance(employeeId);
	}

	@Override
	public void changeAttendance(int ad_num, int at_num) {
		if(ad_num == 0 || at_num == 0) {
			return;
		}
		AttendanceVO dbAt = attendanceDao.selectAttendance2(ad_num);
		if(dbAt.getAd_at_num() == 2 && at_num != 2) {
			attendanceDao.deleteAtRecord(ad_num);
		}
		if(dbAt.getAd_at_num() != 2 && at_num == 2) {
			LocalTime nowTime = LocalTime.now();
			String formattedDateTime2 = nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
			attendanceDao.insertAtRecord(ad_num, formattedDateTime2);
		}
		attendanceDao.updateAtType(ad_num, at_num);
		
	}

	@Override
	public AttendanceRecordVO viewAttendanceRecord(int ad_num) {
		if(ad_num == 0) {
			return null;
		}
		return attendanceDao.selectAtrecord(ad_num);
	}

	@Override
	public String getAttendanceType(int ad_at_num) {
		if(ad_at_num == 0) {
			return null;
		}
		return attendanceDao.selectAttendanceType(ad_at_num);
	}

	@Override
	public void recordAttendanceRecord(String employeeId) {
		if(employeeId == null) {
			return;
		}
		LocalDate nowDate = LocalDate.now();
		LocalTime nowTime = LocalTime.now();
		String formattedDateTime = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String formattedDateTime2 = nowTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		AttendanceVO dbAt = attendanceDao.selectAttendance(employeeId, formattedDateTime);
	    attendanceDao.insertAtRecord(dbAt.getAd_num(), formattedDateTime2);
	}

	@Override
	public List<AttendanceVO> checkAttendanceDate(String formattedDateTime) {
		if(formattedDateTime == null) {
			return null;
		}
		return attendanceDao.selectAllAttendance2(formattedDateTime);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return memberDao.selectMemberList();
	}



	@Override
	public void recordAttendance(List<MemberVO> dbMemberList, String formattedDateTime) {
		if(dbMemberList == null) {
			return;
		}
		for(MemberVO tmp : dbMemberList) {
			attendanceDao.insertAttendance(tmp.getEp_id(), formattedDateTime);
		}
	}

	@Override
	public AttendanceVO getAttendance(String employeeId, String formattedDateTime) {
		if(employeeId == null) {
			return null;	
		}
		return attendanceDao.selectAttendance(employeeId, formattedDateTime);
	}

	@Override
	public List<AttendanceVO> viewAllAttendance(String date) {
		if(date == null) {
			return null;
		}
		return attendanceDao.selectAllAttendance2(date);
	}

}