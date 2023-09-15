package pmproject.service;

import java.util.List;

import pmproject.vo.DeptVO;
import pmproject.vo.MemberVO;
import pmproject.vo.SalaryHistoryVO;
import pmproject.vo.SalaryVO;
import pmproject.vo.TransferVO;

public interface MemberService {

	boolean insertMember(MemberVO member);

	MemberVO selectMember(String id);

	boolean updatePhone(String id, String newPhone);

	List<DeptVO> selectAllDept();

	boolean updateDept(String id, int dept, String reason, String today, String ep_id);

	boolean updateSalary(String id, int salary);

	List<SalaryVO> selectSalaryType();

	boolean insertSalaryHistory(String ep_id, String salaryType, String salaryDetail, String today, int salary);

	List<SalaryHistoryVO> SearchSalaryHistory(String ep_id);

	List<TransferVO> SearchTransfer(String ep_id);

	int selectMemberCount();

}
