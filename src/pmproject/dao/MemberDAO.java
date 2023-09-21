package pmproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pmproject.vo.DeptVO;
import pmproject.vo.MemberVO;
import pmproject.vo.SalaryHistoryVO;
import pmproject.vo.SalaryVO;
import pmproject.vo.TransferVO;

public interface MemberDAO {

	MemberVO selectMember(@Param("id") String id);
	
	MemberVO selectMemberPhone(@Param("phone") String phone);

	void insertMember(@Param("member") MemberVO member);

	String selectDept(@Param("ep_dm_num") int ep_dm_num);

	String selectPos(@Param("ep_po_num") int ep_po_num);

	String selectSt(@Param("ep_st_num") int ep_st_num);

	void updatePhone(@Param("ep_id") String ep_id, @Param("ep_phone_num") String newPhone);

	List<DeptVO> selectAllDept();

	void updateDept(@Param("id") String id, @Param("dept") int dept);

	void updateSalary(@Param("id") String id, @Param("salary") int salary);

	List<SalaryVO> selectSalaryType();

	void insertSalaryHistory(@Param("sh_ep_id") String ep_id, @Param("sh_pa_type") String salaryType, @Param("sh_type_detail") String salaryDetail, @Param("sh_payday") String today, @Param("sh_salary") int salary);

	List<SalaryHistoryVO> selectSalaryHistory(@Param("sh_ep_id") String ep_id);

	void insertTransfer(@Param("tr_date") String today, @Param("tr_reason") String reason, @Param("tr_ep_id") String ep_id, @Param("tr_dm_num") int dept);

	List<TransferVO> selectTransfer(@Param("ep_id") String ep_id);

	int selectMemberCount();
	
	List<MemberVO> selectMemberList();

}
