package pmproject.service;

import java.util.List;

import pmproject.vo.LeaveVO;
import pmproject.vo.TimeoffVO;

public interface TimeoffService {

	List<TimeoffVO> getTimeoffList();

	boolean updateTimeoff(TimeoffVO timeoff);

	boolean insertTimeoff(LeaveVO leave);

	TimeoffVO selectTimeoff(String ep_id);

	List<LeaveVO> selectLeaveList(String ep_id);

}