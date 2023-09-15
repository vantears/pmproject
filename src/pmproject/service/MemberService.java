package pmproject.service;

import java.util.List;

import pmproject.vo.DeptVO;
import pmproject.vo.MemberVO;
import pmproject.vo.SalaryHistoryVO;
import pmproject.vo.SalaryVO;
import pmproject.vo.TransferVO;

public interface MemberService {

	boolean insertMember(MemberVO member);

	MemberVO selectMember(String phone);

	boolean updatePhone(String phone, String newPhone);

	List<DeptVO> selectAllDept();

	boolean updateDept(String phone, int dept, String reason, String today, int ep_id);

	boolean updateSalary(String phone, int salary);

	List<SalaryVO> selectSalaryType();

	boolean insertSalaryHistory(int ep_id, String salaryType, String salaryDetail, String today, int salary);

	List<SalaryHistoryVO> SearchSalaryHistory(int ep_id);

	List<TransferVO> SearchTransfer(int ep_id);

}
