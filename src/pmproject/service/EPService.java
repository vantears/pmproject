package pmproject.service;

import java.util.List;

import pmproject.vo.MemberVO;

public interface EPService {

	List<MemberVO> getEPList();

	boolean deleteEP(MemberVO ep);

	boolean updateEP(MemberVO ep);
	
}