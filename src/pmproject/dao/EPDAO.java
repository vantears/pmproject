package pmproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pmproject.vo.MemberVO;

public interface EPDAO {

	List<MemberVO> selectEPList();

	MemberVO selectEP(@Param("ep_id") String ep_id);

	boolean deleteEP(@Param("ep_id") String ep_id, @Param("stNum") int stNum);

	int updateEP(@Param("dbEp") MemberVO dbEp);

	void insertTm(@Param("ep_id") String ep_id, @Param("today") String today);

}