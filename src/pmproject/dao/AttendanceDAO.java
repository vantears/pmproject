package pmproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pmproject.vo.AttendanceRecordVO;
import pmproject.vo.AttendanceVO;
import pmproject.vo.MemberVO;

public interface AttendanceDAO {
	AttendanceVO selectAttendance(@Param("ep_id") String employeeId, @Param("date") String formattedDateTime);
	AttendanceRecordVO selectAtrecord(@Param("ad_num") int ad_num);
	void updateEndTime(@Param("ar_ad_num") int ar_ad_num, @Param("date") String formattedDateTime);
	void insertAtRecord(@Param("ad_num") int ad_num, @Param("date") String formattedDateTime2);
	List<AttendanceVO> selectAllAttendance(@Param("ep_id") String employeeId);
	String selectAttendanceType(@Param("ad_at_num") int ad_at_num);
	AttendanceVO selectAttendance2(@Param("ad_num") int ad_num);
	void deleteAtRecord(@Param("ad_num") int ad_num);
	void updateAtType(@Param("ad_num") int ad_num, @Param("at_num") int at_num);
	void insertAttendance(@Param("ep_id") String ep_id, @Param("date") String formattedDateTime);
	List<AttendanceVO> selectAllAttendance2(@Param("date") String formattedDateTime);
}