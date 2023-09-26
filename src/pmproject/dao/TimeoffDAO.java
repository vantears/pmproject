package pmproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pmproject.vo.LeaveVO;
import pmproject.vo.TimeoffVO;

public interface TimeoffDAO {

	List<TimeoffVO> selectTimeoffList();

	TimeoffVO selectTimeoff(@Param("ep_id")String ep_id);

	int updateTimeoff(@Param("dbTimeoff")TimeoffVO dbTimeoff);

	void insertTimeoff(@Param("leave")LeaveVO timeoff);

	List<LeaveVO> selectLeaveList(@Param("ep_id") String ep_id);

	boolean updateLeave(@Param("le_num") int le_num, @Param("le_start_date") String le_start_date, @Param("le_end_date") String le_end_date, @Param("le_type") String le_type);

}