package pmproject.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pmproject.dao.MemberDAO;
import pmproject.vo.DeptVO;
import pmproject.vo.MemberVO;
import pmproject.vo.SalaryHistoryVO;
import pmproject.vo.SalaryVO;
import pmproject.vo.TransferVO;

public class MemberServiceImp implements MemberService{
	
	private MemberDAO memberDao;
	private final String MYBATIS_CONFIG_PATH = "pmproject/config/mybatis-config.xml";
	
	public MemberServiceImp() {
		try {
			InputStream is = Resources.getResourceAsStream(MYBATIS_CONFIG_PATH);
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
			//true의 역할 : 쿼리(insert, update,delete) 실행 후 자동 커밋되게 해줌
			SqlSession session = sf.openSession(true);
			memberDao = session.getMapper(MemberDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insertMember(MemberVO member) {
		if(member == null || member.getEp_name() == null || member.getEp_phone_num() == null) {
			return false;
		}
		MemberVO dbMember = memberDao.selectMember(member.getEp_phone_num());
		if(dbMember == null) {
			memberDao.insertMember(member);			
			return true;
		}
		return false;
		
	}

	@Override
	public MemberVO selectMember(String phone) {
		if(phone == null) {
			return null;
		}
		MemberVO dbMember = memberDao.selectMember(phone);
		if(dbMember == null) {
			return null;
		}
		dbMember.setEp_dept(memberDao.selectDept(dbMember.getEp_dm_num()));
		dbMember.setEp_pos(memberDao.selectPos(dbMember.getEp_po_num()));
		dbMember.setEp_st(memberDao.selectSt(dbMember.getEp_st_num()));
		return dbMember;
	}

	@Override
	public boolean updatePhone(String phone, String newPhone) {
		if(phone == null || newPhone == null) {
			return false;
		}
		MemberVO dbMember = memberDao.selectMember(phone);
		if(dbMember == null) {
			return false;
		}
		memberDao.updatePhone(dbMember.getEp_id(), newPhone);
		return true;
	}

	@Override
	public List<DeptVO> selectAllDept() {
		return memberDao.selectAllDept();
		
	}

	@Override
	public boolean updateDept(String phone, int dept, String reason, String today, int ep_id) {
		if(phone == null || today == null || ep_id == 0) {
			return false;
		}
		if(dept == 0) {
			return false;
		}
		memberDao.updateDept(phone, dept);
		memberDao.insertTransfer(today, reason, ep_id, dept);
		return true;
	}

	@Override
	public boolean updateSalary(String phone, int salary) {
		if(phone == null) {
			return false;
		}
		memberDao.updateSalary(phone, salary);
		return true;
	}

	@Override
	public List<SalaryVO> selectSalaryType() {
		return memberDao.selectSalaryType();
	}

	@Override
	public boolean insertSalaryHistory(int ep_id, String salaryType, String salaryDetail, String today, int salary) {
		if(ep_id == 0) {
			return false;
		}
		if(salaryType == null || today == null) {
			return false;
		}
		memberDao.insertSalaryHistory(ep_id, salaryType, salaryDetail, today, salary);
		return true;
	}

	@Override
	public List<SalaryHistoryVO> SearchSalaryHistory(int ep_id) {
		if(ep_id == 0) {
			return null;
		}
		return memberDao.selectSalaryHistory(ep_id);
	}

	@Override
	public List<TransferVO> SearchTransfer(int ep_id) {
		if(ep_id == 0) {
			return null;
		}
		List<TransferVO> transfer = memberDao.selectTransfer(ep_id);
		if(transfer == null) {
			return null;
		}
		for(TransferVO tmp : transfer) {
			transfer.get(transfer.indexOf(tmp)).setTr_dept(memberDao.selectDept(transfer.get(transfer.indexOf(tmp)).getTr_dm_num()));
		}
		return transfer;
	}

}
