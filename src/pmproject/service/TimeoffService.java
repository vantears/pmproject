package pmproject.service;

import java.util.List;

import pmproject.vo.TimeoffVO;

public interface TimeoffService {

	List<TimeoffVO> getTimeoffList();

	boolean updateTimeoff(TimeoffVO timeoff);

	boolean insertTimeoff(TimeoffVO timeoff);

}