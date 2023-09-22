package pmproject.service;

import pmproject.vo.AttendanceVO;
import pmproject.vo.MemberVO;

import java.util.List;

import pmproject.vo.AttendanceRecordVO;

public interface AttendanceService {
    void recordAttendance(List<MemberVO> dbMemberList, String formattedDateTime);
    boolean recordLeave(String employeeId);
    List<AttendanceVO> viewAttendance(String employeeId);
    void changeAttendance(int ad_num, int at_num);
	AttendanceRecordVO viewAttendanceRecord(int ad_num);
	String getAttendanceType(int ad_at_num);
	void recordAttendanceRecord(String employeeId);
	List<AttendanceVO> checkAttendanceDate(String formattedDateTime);
	List<MemberVO> getAllMember();
	AttendanceVO getAttendance(String employeeId, String formattedDateTime);
	List<AttendanceVO> viewAllAttendance(String date);
}