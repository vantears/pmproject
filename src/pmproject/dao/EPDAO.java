package pmproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pmproject.vo.MemberVO;

public interface EPDAO {

	List<MemberVO> selectEPList();

	MemberVO selectEP(@Param("ep_id")int ep_id);

	boolean deleteEP(@Param("ep_id")int ep_id);

	int updateEP(@Param("dbEp")MemberVO dbEp);

	


}