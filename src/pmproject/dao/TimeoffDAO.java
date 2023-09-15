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

}